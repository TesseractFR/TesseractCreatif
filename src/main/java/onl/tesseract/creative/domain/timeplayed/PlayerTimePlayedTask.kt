package onl.tesseract.creative.domain.timeplayed

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.afk.AfkManager
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.lib.util.append
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.springframework.scheduling.annotation.Scheduled
import java.time.Duration
import java.util.concurrent.TimeUnit
import org.springframework.stereotype.Component as SpringComponent

private const val PERIOD = 1L;
private const val TIME_TEMPORAL_LYS = 10L;

/**
 * Task to update the player's time played, and auto-rank.
 */
@SpringComponent
class PlayerTimePlayedTask(
    private val afkManager: AfkManager,
    private val playerTimePlayedService: PlayerTimePlayedService,
    private val playerRankService: PlayerRankService,
    private val tplayerInfoService: TPlayerInfoService,
) {

    @Scheduled(fixedRate = PERIOD, timeUnit = TimeUnit.SECONDS, scheduler = "bukkitScheduler")
    fun run() {
        for (player in Bukkit.getOnlinePlayers()) {
            if (!afkManager.isAfk(player)) {
                playerTimePlayedService.addPlayerTimePlayed(player.uniqueId, PERIOD)
                checkUpdateRank(player)
                checkUpdateTimeMoney(player);
            }
        }
    }

    private fun checkUpdateTimeMoney(player: Player) {
        val playerRank = playerRankService.getPlayerRank(player.uniqueId)
        if (playerRank < PlayerRank.BATISSEUR) {
            return
        }
        var nextDuration = playerTimePlayedService.getNextMoneyDuration(player.uniqueId)
        nextDuration = nextDuration.minusSeconds(PERIOD)
        if (!nextDuration.isPositive) {
            nextDuration = Duration.ofMinutes(TIME_TEMPORAL_LYS);
            playerTimePlayedService.addTemporalLys(player.uniqueId, 1)
        }
        playerTimePlayedService.setNextMoneyDuration(player.uniqueId, nextDuration)
    }

    private fun checkUpdateRank(player: Player) {
        val uuid = player.uniqueId
        val playerRank = playerRankService.getPlayerRank(uuid)
        if (playerRank < PlayerRank.BATISSEUR) {
            val nextRank = playerRankService.getNextPlayerRank(uuid)
            val timeForRankUp = playerTimePlayedService.getTimeBeforeRankUp(uuid, nextRank)
            if (!timeForRankUp.isPositive) {
                playerRankService.setPlayerRank(uuid, nextRank)
                val rankUpMessage = getRankUpMessage(player, nextRank)
                Bukkit.broadcast(rankUpMessage)
            }
        }
    }

    private fun getRankUpMessage(player: Player, nextRank: PlayerRank): Component {
        val gender = tplayerInfoService[player.uniqueId].genre
        val rankUpMessage = Component.text("---------------------------------------------", NamedTextColor.GOLD)
                .append("\nFélicitations à ", NamedTextColor.GOLD)
                .append(player.name, NamedTextColor.RED, TextDecoration.BOLD)
                .append(" qui vient d'obtenir le grade ", NamedTextColor.GOLD)
                .append(nextRank.title.getDisplayName(gender), nextRank.color, TextDecoration.BOLD)
                .append(" !", NamedTextColor.GOLD)
                .append("\n---------------------------------------------", NamedTextColor.GOLD)
        return rankUpMessage
    }
}
