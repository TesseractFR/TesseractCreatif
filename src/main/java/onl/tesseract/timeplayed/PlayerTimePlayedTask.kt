package onl.tesseract.timeplayed

import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.afk.AfkManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration
import java.util.UUID

class PlayerTimePlayedTask(val period: Long) : BukkitRunnable() {
    val afkManager: AfkManager = AfkManager.getINSTANCE();
    override fun run() {
        for(player in Bukkit.getOnlinePlayers()){
            if(!afkManager.isAfk(player)){
                CreativeServices[PlayerTimePlayedService::class.java].addPlayerTimePlayed(player.uniqueId,period);
                checkUpdateRank(player.uniqueId)
            }
        }
    }
    private fun checkUpdateRank(uuid: UUID){
        val playerRank = CreativeServices[PlayerRankService::class.java].getPlayerRank(uuid)
        val playedTime = CreativeServices[PlayerTimePlayedService::class.java].getPlayerTimePlayed(uuid)
        if(playerRank < PlayerRank.BATISSEUR){
            val duration = Duration.ofSeconds(playedTime.seconds)
            val duration2 = Duration.ofHours(PlayerRank.entries[playerRank.ordinal+1].hoursRequired)
            if(duration > duration2){
                CreativeServices[PlayerRankService::class.java].setPlayerRank(uuid,PlayerRank.entries[playerRank.ordinal+1])
            }
        }
    }

    companion object{
        /**
         * Start the task, period in seconds.
         */
        fun start(plugin: JavaPlugin,period: Long) {
            PlayerTimePlayedTask(period).runTaskTimerAsynchronously(plugin,0,period*20L);
        }
    }
}