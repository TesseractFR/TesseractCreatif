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

/**
 * Task to update the player's time played, and auto-rank.
 */
class PlayerTimePlayedTask(val period: Long) : BukkitRunnable() {
    private val afkManager: AfkManager = ServiceContainer[AfkManager::class.java]
    override fun run() {
        for(player in Bukkit.getOnlinePlayers()){
            if(!afkManager.isAfk(player)){
                ServiceContainer[PlayerTimePlayedService::class.java].addPlayerTimePlayed(player.uniqueId, period)
                checkUpdateRank(player)
            }
        }
    }
    private fun checkUpdateRank(player: Player){
        val uuid = player.uniqueId
        val playerRankService = ServiceContainer[PlayerRankService::class.java]
        val playerRank = playerRankService.getPlayerRank(uuid)
        if(playerRank < PlayerRank.BATISSEUR){
            val nextRank = playerRankService.getNextPlayerRank(uuid)
            val playerTimePlayedService = ServiceContainer[PlayerTimePlayedService::class.java]
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