package onl.tesseract.scoreBoard

import onl.tesseract.Creatif
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.timeplayed.PlayerTimePlayedService
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.time.Duration

class PlayerBoard(player: Player?) : Board(player) {

    override fun initScoreBoard(player: Player) {
        displayPlayerBoard(player)
        super.initScoreBoard(player)
    }

    private fun displayPlayerBoard(player: Player) {
        val rankService = CreativeServices[PlayerRankService::class.java]
        val timePlayedService = CreativeServices[PlayerTimePlayedService::class.java]

        val rank = rankService.getPlayerRank(player.uniqueId)
        addOrUpdateScore(" ", 16)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade actuel -", 15)
        addOrUpdateScore("${rank.color.toChatColor()}${rank}", 14)
        addOrUpdateScore("  ", 13)

        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
        val timePlayedFormatted = timePlayedService.formatTime(timePlayed)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Temps de jeu -", 12)
        addOrUpdateScore("${ChatColor.YELLOW}$timePlayedFormatted", 11)
        addOrUpdateScore("   ", 10)

        val nextRank = PlayerRank.entries
            .dropWhile { it != rank }
            .drop(1)
            .firstOrNull()

        if(nextRank != null && rank != PlayerRank.BATISSEUR && rank != PlayerRank.VIRTUOSE) {
            val nextRankTimeInSeconds = nextRank.hoursRequired * 3600
            val remainingTimeInSeconds = nextRankTimeInSeconds - timePlayed.seconds

            val remainingDuration = Duration.ofSeconds(remainingTimeInSeconds)
            val formattedTime = timePlayedService.formatTime(remainingDuration)

            addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -", 9)
            addOrUpdateScore("${nextRank.color.toChatColor()}${nextRank}", 8)
            addOrUpdateScore("${ChatColor.YELLOW}${formattedTime}", 7)
            addOrUpdateScore("    ", 6)
        }

        val worldName = player.world.name
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Monde actuel -", 5)
        addOrUpdateScore("${ChatColor.YELLOW}$worldName", 4)

        player.scoreboard = scoreboard
    }
}
