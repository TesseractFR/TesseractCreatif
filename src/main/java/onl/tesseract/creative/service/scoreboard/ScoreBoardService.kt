package onl.tesseract.creative.service.scoreboard

import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.scoreBoard.Board
import onl.tesseract.creative.domain.scoreBoard.PlayerRankBoard
import onl.tesseract.creative.domain.scoreBoard.PlayerTimeMoneyBoard
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class ScoreBoardService(
    private val playerRankService: PlayerRankService,
    private val tplayerInfoService: TPlayerInfoService,
    private val playerTimePlayedService: PlayerTimePlayedService,
) {
    private val playerScoreboardStatus: MutableMap<UUID, Boolean> = mutableMapOf()
    private val boards = mutableMapOf<Player, Board>()

    fun setupPlayer(player: Player) {
        val board = createBoard(player, select(player))
        boards[player] = board
    }

    fun removePlayer(player: Player) {
        boards.remove(player)
    }

    private fun createBoard(player: Player, clazz: Class<out Board>): Board {
        when (clazz) {
            PlayerRankBoard::class.java -> {
                return PlayerRankBoard(player, playerRankService, playerTimePlayedService, tplayerInfoService).apply {
                    this.setup()
                }
            }

            PlayerTimeMoneyBoard::class.java -> {
                return PlayerTimeMoneyBoard(player, playerTimePlayedService).apply {
                    this.setup()
                }
            }

        }
        throw error("Board Class Not Found")
    }

    @Scheduled(fixedRate = 1L, timeUnit = TimeUnit.SECONDS, scheduler = "bukkitScheduler")
    fun run() {
        for (player in Bukkit.getOnlinePlayers()) {
            val desiredClass = select(player)
            val currentBoard = boards[player]
            if (playerScoreboardStatus[player.uniqueId] == false) {
                continue
            }
            when {
                currentBoard == null || currentBoard::class.java != desiredClass -> {
                    val newBoard = createBoard(player, desiredClass)
                    boards[player] = newBoard
                }

                else -> {
                    currentBoard.update()
                }
            }
        }

        // Nettoyage des joueurs déconnectés
        boards.keys.retainAll(Bukkit.getOnlinePlayers())
    }



    fun hideScoreboard(player: Player) {
        player.scoreboard = Bukkit.getScoreboardManager().newScoreboard
        playerScoreboardStatus[player.uniqueId] = false
    }

    fun showScoreboard(player: Player) {
        player.scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        playerScoreboardStatus[player.uniqueId] = true
        setupPlayer(player)
    }

    fun select(player: Player): Class<out Board> {
        return if (playerRankService.getPlayerRank(player.uniqueId) < PlayerRank.BATISSEUR) {
            PlayerRankBoard::class.java
        } else {
            PlayerTimeMoneyBoard::class.java
        }
    }
}