package onl.tesseract.creative.service.scoreboard

import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.scoreBoard.PlayerRankBoard
import onl.tesseract.creative.domain.scoreBoard.PlayerTimeMoneyBoard
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.UUID
import java.util.concurrent.TimeUnit

@Service
class ScoreBoardService(
    private val playerRankService: PlayerRankService,
    private val tplayerInfoService: TPlayerInfoService,
    private val playerTimePlayedService: PlayerTimePlayedService,
) {
    val playerScoreboardStatus: MutableMap<UUID, Boolean> = mutableMapOf()

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.SECONDS, scheduler = "bukkitScheduler")
    fun run() {
        for (player in Bukkit.getOnlinePlayers()) {
            if (playerScoreboardStatus.getOrDefault(player.uniqueId, true)) {
                updatePlayerBoard(player)
            }
        }
    }


    fun updatePlayerBoard(player: Player) {
        require(player.isOnline) { "Player ${player.name} is not online" }
        if (playerRankService.getPlayerRank(player.uniqueId) < PlayerRank.BATISSEUR) {
            PlayerRankBoard(player, playerRankService, playerTimePlayedService, tplayerInfoService).update()
        } else {
            PlayerTimeMoneyBoard(player, playerTimePlayedService).update()
        }


        //TODO : AJOUTER LES IF/ELSE AVEC MONDES CLASSIQUES ET EVENT
    }

    fun hideScoreboard(player: Player) {
        player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        playerScoreboardStatus[player.uniqueId] = false
    }

    fun showScoreboard(player: Player) {
        player.scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        playerScoreboardStatus[player.uniqueId] = true
        updatePlayerBoard(player)
    }
}