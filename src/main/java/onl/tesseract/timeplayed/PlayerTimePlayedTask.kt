package onl.tesseract.timeplayed

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.afk.AfkManager
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.append
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration

/**
 * Task to update the player's time played, and auto-rank.
 */
class PlayerTimePlayedTask(val period: Long) : BukkitRunnable() {
    val afkManager: AfkManager = AfkManager.getINSTANCE();
    override fun run() {
        for(player in Bukkit.getOnlinePlayers()){
            if(!afkManager.isAfk(player)){
                CreativeServices[PlayerTimePlayedService::class.java].addPlayerTimePlayed(player.uniqueId,period);
                checkUpdateRank(player)
            }
        }
    }
    private fun checkUpdateRank(player: Player){
        val uuid = player.uniqueId
        val playerRank = CreativeServices[PlayerRankService::class.java].getPlayerRank(uuid)
        val playedTime = CreativeServices[PlayerTimePlayedService::class.java].getPlayerTimePlayed(uuid)
        if(playerRank < PlayerRank.BATISSEUR){
            val nextRank = PlayerRank.entries[playerRank.ordinal+1]
            val duration = Duration.ofSeconds(playedTime.seconds)
            val duration2 = Duration.ofHours(nextRank.hoursRequired)
            if(duration > duration2){
                CreativeServices[PlayerRankService::class.java].setPlayerRank(uuid,nextRank)
                val rankUpMessage = getRankUpMessage(player, nextRank)
                Bukkit.broadcast(rankUpMessage)
            }
        }
    }

    private fun getRankUpMessage(player: Player,nextRank: PlayerRank): Component {
        val gender = TPlayer.get(player.uniqueId).gender;
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
            PlayerTimePlayedTask(period).runTaskTimerAsynchronously(plugin,0,period*20L);
        }
    }
}