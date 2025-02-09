package onl.tesseract.scoreBoard

import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.timeplayed.PlayerTimePlayedService
import onl.tesseract.util.DurationFormat
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.time.Duration

class PlayerTimeMoneyBoard(player: Player?) : Board(player) {

    override fun initScoreBoard(player: Player) {
        displayPlayerBoard(player)
        super.initScoreBoard(player)
    }

    private fun displayPlayerBoard(player: Player) {
        val timePlayedService = ServiceContainer[PlayerTimePlayedService::class.java]
        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
        val nextTemporalLys = timePlayedService.getNextMoneyDuration(player.uniqueId)
        val temporalLys = timePlayedService.getTemporalLys(player.uniqueId)
        var score = 0

        score = displayCurrentWorld(player, score)
        addOrUpdateScore(" ", score++)
        score = displayLysTemporel(temporalLys, nextTemporalLys, score)
        addOrUpdateScore("  ", score++)
        score = displayTimePlayed(timePlayed, score)
        addOrUpdateScore("   ", score++)

        player.scoreboard = scoreboard
    }

    private fun displayCurrentWorld(player: Player, score: Int): Int {
        addOrUpdateScore("${ChatColor.YELLOW}${player.world.name}", score)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Monde actuel -", score + 1)
        return score + 2
    }

    private fun displayTimePlayed(timePlayed: Duration, score: Int): Int {
        val timePlayedFormatted = DurationFormat.formatTime(timePlayed)
        addOrUpdateScore("${ChatColor.YELLOW}$timePlayedFormatted", score)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Temps de jeu -", score + 1)
        return score + 2
    }

    private fun displayLysTemporel(amout: Int, remainingTime: Duration, score: Int): Int {
        val remainingTimeFormatted = DurationFormat.formatTime(remainingTime)
        addOrUpdateScore("${ChatColor.YELLOW}Prochain : ${remainingTimeFormatted}", score)
        addOrUpdateScore("    ", score + 1)
        addOrUpdateScore("${ChatColor.YELLOW}Possédés : ${amout}", score + 2)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Lys temporels -", score + 3)
        return score + 4
    }
}
