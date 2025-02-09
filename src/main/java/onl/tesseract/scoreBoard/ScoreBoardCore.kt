package onl.tesseract.scoreBoard

import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

object ScoreBoardCore {
    val playerScoreboardStatus: MutableMap<UUID, Boolean> = mutableMapOf()
    private val task : BukkitRunnable = object : BukkitRunnable(){
        override fun run() {
            for (player in Bukkit.getOnlinePlayers()) {
                if (playerScoreboardStatus.getOrDefault(player.uniqueId, true)) {
                    updatePlayerBoard(player)
                }
            }
        }
    };

    fun updatePlayerBoard(player: Player) {
        require(player.isOnline) { "Player ${player.name} is not online" }
        if (ServiceContainer[PlayerRankService::class.java].getPlayerRank(player.uniqueId) < PlayerRank.BATISSEUR) {
            PlayerRankBoard(player)
        } else {
            PlayerTimeMoneyBoard(player)
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

    fun startScoreboard(plugin: JavaPlugin) {
        task.runTaskTimer(plugin, 0L, 20L)
    }

    fun stopScoreboard() {
        task.cancel();
    }


}