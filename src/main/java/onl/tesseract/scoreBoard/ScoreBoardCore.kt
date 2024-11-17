package onl.tesseract.scoreBoard

import onl.tesseract.Creatif
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class ScoreBoardCore {
    init {
        Creatif.instance?.let {
            object : BukkitRunnable() {
                override fun run() {
                    for (player in Bukkit.getOnlinePlayers()) {
                        updatePlayerBoard(player)
                    }
                }
            }.runTaskTimer(it, 0L, 20L)
        }
    }

    companion object {
        @JvmStatic
        fun updatePlayerBoard(player: Player) {
            require(player.isOnline) { "Player ${player.name} is not online" }
            PlayerBoard(player)
            // AJOUTER LES IF/ELSE AVEC MONDES CLASSIQUES ET EVENT
        }
    }


}