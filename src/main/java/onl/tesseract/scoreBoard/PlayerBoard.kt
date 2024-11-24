package onl.tesseract.scoreBoard

import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.timeplayed.PlayerTimePlayedService
import onl.tesseract.util.DurationFormat
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import java.time.Duration

class PlayerBoard(player: Player?) : Board(player) {

    override fun initScoreBoard(player: Player) {
        displayPlayerBoard(player)
        super.initScoreBoard(player)
    }

    private fun displayPlayerBoard(player: Player) {
        val rankService = ServiceContainer[PlayerRankService::class.java]
        val timePlayedService = ServiceContainer[PlayerTimePlayedService::class.java]
        val rank = rankService.getPlayerRank(player.uniqueId)
        val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
        var score = 0

        score = displayCurrentWorld(player, score)
        addOrUpdateScore(" ", score++)
        score = displayTimeNextRank(rank, rankService, player, timePlayedService, score)
        score = displayActualRank(rank, score)
        addOrUpdateScore("  ", score++)
        score = displayTimePlayed(timePlayed, score)
        addOrUpdateScore("   ", score++)

        player.scoreboard = scoreboard
    }

    private fun displayTimeNextRank(
        rank: PlayerRank,
        rankService: PlayerRankService,
        player: Player,
        timePlayedService: PlayerTimePlayedService,
        score: Int,
    ): Int {
        var score1 = score
        if (rank.ordinal < PlayerRank.BATISSEUR.ordinal) {
            val nextRank = rankService.getNextPlayerRank(player.uniqueId)
            val remainingTime = timePlayedService.getTimeBeforeRankUp(player.uniqueId, nextRank)
            val remainingTimeFormatted = DurationFormat.formatTime(remainingTime)

            addOrUpdateScore("${ChatColor.YELLOW}${remainingTimeFormatted}", score1++)
            addOrUpdateScore("${nextRank.color.toChatColor()}${nextRank}", score1++)
            addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -", score1++)
            addOrUpdateScore("    ", score1++)
        }
        return score1
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

    private fun displayActualRank(rank: PlayerRank, score: Int): Int {
        addOrUpdateScore("${rank.color.toChatColor()}${rank}", score)
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade actuel -", score + 1)
        return score + 2
    }
}
