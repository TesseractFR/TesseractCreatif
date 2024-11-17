package onl.tesseract.scoreBoard

import onl.tesseract.Creatif
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

        val nextRankInfo = getNextRankAndTime(rank, timePlayed.seconds)
        val nextRank = nextRankInfo.nextRank

        if (nextRank == null) {
            addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -", 9)
            addOrUpdateScore("${ChatColor.YELLOW}${nextRankInfo.timeRemaining}", 8)
        } else {
            addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Grade suivant -", 9)
            addOrUpdateScore("${nextRank.color.toChatColor()}${nextRank}", 8)
            addOrUpdateScore("${ChatColor.YELLOW}${nextRankInfo.timeRemaining}", 7)
        }
        addOrUpdateScore("    ", 6)

        val worldName = player.world.name
        addOrUpdateScore("${ChatColor.GOLD}${ChatColor.ITALIC}${ChatColor.BOLD}- Monde actuel -", 5)
        addOrUpdateScore("${ChatColor.YELLOW}$worldName", 4)

        applyToPlayer(player)
    }
}

data class NextRankInfo(
    val nextRank: PlayerRank?,
    val timeRemaining: String
)

private fun getNextRankAndTime(currentRank: PlayerRank, timePlayedInSeconds: Long): NextRankInfo {
    if (currentRank == PlayerRank.BATISSEUR) {
        return NextRankInfo(null, "Rank Maximum atteint !")
    }
    val nextRank = PlayerRank.entries
        .filter { it.hoursRequired > currentRank.hoursRequired }
        .minByOrNull { it.hoursRequired }
    if (nextRank == null || nextRank == PlayerRank.VIRTUOSE) {
        return NextRankInfo(null, "Aucun rang suivant")
    }
    val nextRankTimeInSeconds = nextRank.hoursRequired * 3600
    val remainingTimeInSeconds = nextRankTimeInSeconds - timePlayedInSeconds
    val daysRemaining = remainingTimeInSeconds / 86400
    val hoursRemaining = (remainingTimeInSeconds % 86400) / 3600
    val minutesRemaining = (remainingTimeInSeconds % 3600) / 60
    val secondsRemaining = remainingTimeInSeconds % 60
    val formattedTime = "${daysRemaining}j ${hoursRemaining}h ${minutesRemaining}min ${secondsRemaining}s"

    return NextRankInfo(nextRank, formattedTime)
}
