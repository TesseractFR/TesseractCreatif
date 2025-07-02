package onl.tesseract.creative.domain.scoreBoard


import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.util.DurationFormat
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.time.Duration

class PlayerTimeMoneyBoard(
    player: Player,
    private val timePlayedService: PlayerTimePlayedService,
) : Board(player) {

    override fun initScoreBoard() {
        displayPlayerBoard()
        super.initScoreBoard()
    }

    private fun displayPlayerBoard() {
        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
        val nextTemporalLys = timePlayedService.getNextMoneyDuration(player.uniqueId)
        val temporalLys = timePlayedService.getTemporalLys(player.uniqueId)
        var score = 0

        score = displayCurrentWorld(score)
        addOrUpdateScore(" ", score++)
        score = displayLysTemporel(temporalLys, nextTemporalLys, score)
        addOrUpdateScore("  ", score++)
        score = displayTimePlayed(timePlayed, score)
        addOrUpdateScore("   ", score++)
    }

    private fun displayCurrentWorld(score: Int): Int {
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
