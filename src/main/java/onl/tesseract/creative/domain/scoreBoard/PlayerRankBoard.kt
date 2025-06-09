package onl.tesseract.creative.domain.scoreBoard

import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.util.DurationFormat
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.time.Duration

class PlayerRankBoard(
    player: Player,
    private val playerRankService: PlayerRankService,
    private val playerTimePlayedService: PlayerTimePlayedService,
    private val tplayerInfoService: TPlayerInfoService,
) : Board(player) {

    override fun initScoreBoard() {
        displayPlayerBoard()
        super.initScoreBoard()
    }

    private fun displayPlayerBoard() {
        val rank = playerRankService.getPlayerRank(player.uniqueId)
        val timePlayed = playerTimePlayedService.getPlayerTimePlayed(player.uniqueId)
        var score = 0

        score = displayCurrentWorld(score)
        addOrUpdateScore(" ", score++)
        score = displayTimeNextRank(rank, score)
        score = displayActualRank(rank, score)
        addOrUpdateScore("  ", score++)
        score = displayTimePlayed(timePlayed, score)
        addOrUpdateScore("   ", score++)

        player.scoreboard = scoreboard
    }

    private fun displayTimeNextRank(
        rank: PlayerRank,
        score: Int,
    ): Int {
        var score1 = score
        if (rank.ordinal < PlayerRank.BATISSEUR.ordinal) {
            val nextRank = playerRankService.getNextPlayerRank(player.uniqueId)
            val remainingTime = playerTimePlayedService.getTimeBeforeRankUp(player.uniqueId, nextRank)
            val remainingTimeFormatted = DurationFormat.formatTime(remainingTime)
            val nextRankGender = tplayerInfoService[player.uniqueId].genre

            addOrUpdateScore("${ChatColor.YELLOW}${remainingTimeFormatted}", score1++)
            addOrUpdateScore("${nextRank.color.toChatColor()}${nextRank.title.getDisplayName(nextRankGender).uppercase()}", score1++)
            addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -", score1++)
            addOrUpdateScore("    ", score1++)
        }
        return score1
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

    private fun displayActualRank(rank: PlayerRank, score: Int): Int {
        val currentGender = tplayerInfoService[player.uniqueId].genre
        addOrUpdateScore("${rank.color.toChatColor()}${rank.title.getDisplayName(currentGender).uppercase()}", score)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade actuel -", score + 1)
        return score + 2
    }
}
