package onl.tesseract.timeplayed

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.afk.AfkManager
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.append
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration

/**
 * Task to update the player's time played, and auto-rank.
 */
class PlayerTimePlayedTask(val period: Long) : BukkitRunnable() {
    private val afkManager: AfkManager = ServiceContainer[AfkManager::class.java]
    private val playerTimePlayedService = ServiceContainer[PlayerTimePlayedService::class.java]
    private val playerRankService = ServiceContainer[PlayerRankService::class.java]

    private val TIME_TEMPORAL_LYS = 10L;

    override fun run() {
        for(player in Bukkit.getOnlinePlayers()){
            if(!afkManager.isAfk(player)){
                playerTimePlayedService.addPlayerTimePlayed(player.uniqueId, period)
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
        nextDuration = nextDuration.minusSeconds(period)
        if (!nextDuration.isPositive) {
            nextDuration = Duration.ofMinutes(TIME_TEMPORAL_LYS);
            playerTimePlayedService.addTemporalLys(player.uniqueId, 1)
        }
        playerTimePlayedService.setNextMoneyDuration(player.uniqueId, nextDuration)
    }

    private fun checkUpdateRank(player: Player){
        val uuid = player.uniqueId
        val playerRank = playerRankService.getPlayerRank(uuid)
        if(playerRank < PlayerRank.BATISSEUR){
            val nextRank = playerRankService.getNextPlayerRank(uuid)
            val timeForRankUp = playerTimePlayedService.getTimeBeforeRankUp(uuid, nextRank)
            if(!timeForRankUp.isPositive){
                playerRankService.setPlayerRank(uuid,nextRank)
                val rankUpMessage = getRankUpMessage(player, nextRank)
                Bukkit.broadcast(rankUpMessage)
            }
        }
    }

    private fun getRankUpMessage(player: Player,nextRank: PlayerRank): Component {
        val gender = ServiceContainer[TPlayerInfoService::class.java][player.uniqueId].genre
        val rankUpMessage = Component.text("---------------------------------------------", NamedTextColor.GOLD)
            .append("\nFélicitations à ", NamedTextColor.GOLD)
            .append(player.name, NamedTextColor.RED, TextDecoration.BOLD)
            .append(" qui vient d'obtenir le grade ", NamedTextColor.GOLD)
            .append(nextRank.title.getDisplayName(gender), nextRank.color, TextDecoration.BOLD)
            .append(" !", NamedTextColor.GOLD)
            .append("\n---------------------------------------------", NamedTextColor.GOLD)
        return rankUpMessage
    }

    companion object{
        /**
         * Start the task, period in seconds.
         */
        fun start(plugin: JavaPlugin,period: Long) {
            PlayerTimePlayedTask(period).runTaskTimerAsynchronously(plugin, 0, period * 20L)
        }
    }
}