package onl.tesseract.scoreBoard

import onl.tesseract.Creatif
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

object ScoreBoardCore {
    private val playerScoreboardStatus: MutableMap<UUID, Boolean> = mutableMapOf()

    init {
        Creatif.instance?.let {
            object : BukkitRunnable() {
                override fun run() {
                    for (player in Bukkit.getOnlinePlayers()) {
                        if (playerScoreboardStatus.getOrDefault(player.uniqueId, false)) {
                            updatePlayerBoard(player)
                        }
                    }
                }
            }.runTaskTimer(it, 0L, 20L)
        }
    }


    fun updatePlayerBoard(player: Player) {
        require(player.isOnline) { "Player ${player.name} is not online" }
        PlayerBoard(player)

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