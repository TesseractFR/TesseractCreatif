package onl.tesseract.scoreBoard

import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.timeplayed.PlayerTimePlayedService
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class PlayerBoard(player: Player?) : Board(player) {

    override fun initScoreBoard(player: Player) {
        displayPlayerBoard(player)
        super.initScoreBoard(player)
    }

    private fun displayPlayerBoard(player: Player) {
        val rankService = CreativeServices[PlayerRankService::class.java]
        val rank = rankService.getPlayerRank(player.uniqueId)

        val timePlayedService = CreativeServices[PlayerTimePlayedService::class.java]
        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
        val timePlayedFormatted = timePlayedService.formatTime(timePlayed)

        addOrUpdateScore(" ", 16)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade actuel -", 15)
        addOrUpdateScore("${rank.color.toChatColor()}${rank}", 14)
        addOrUpdateScore("  ", 13)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Temps de jeu -", 12)
        addOrUpdateScore("${ChatColor.YELLOW}$timePlayedFormatted", 11)
        addOrUpdateScore("   ", 10)

        val nextRank = rankService.getNextPlayerRank(player.uniqueId)

        if(rank != PlayerRank.BATISSEUR && rank != PlayerRank.VIRTUOSE) {
            val remainingTime = timePlayedService.getTimeBeforeRankUp(player.uniqueId, nextRank)
            val remainingTimeFormatted = timePlayedService.formatTime(remainingTime)

            addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -", 9)
            addOrUpdateScore("${nextRank.color.toChatColor()}${nextRank}", 8)
            addOrUpdateScore("${ChatColor.YELLOW}${remainingTimeFormatted}", 7)
            addOrUpdateScore("    ", 6)
        }
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Monde actuel -", 5)
        addOrUpdateScore("${ChatColor.YELLOW}${player.world.name}", 4)
        player.scoreboard = scoreboard
    }
}
