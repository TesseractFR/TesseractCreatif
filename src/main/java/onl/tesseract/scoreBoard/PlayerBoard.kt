package onl.tesseract.scoreBoard

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.Creatif
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.timeplayed.PlayerTimePlayedService
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class PlayerBoard : Board() {

    fun setupBoard(player: Player) {
        initialize()
        startUpdatingBoard(player)
    }

    private fun startUpdatingBoard(player: Player) {
        val rankService = CreativeServices[PlayerRankService::class.java]
        val timePlayedService = CreativeServices[PlayerTimePlayedService::class.java]

        Creatif.instance?.let {
            object : BukkitRunnable() {
                override fun run() {
                    if (!player.isOnline) {
                        this.cancel()
                        return
                    }

                    addOrUpdateScore(" ", 16)
                    val rank = rankService.getPlayerRank(player.uniqueId)
                    addOrUpdateScore("Grade : ", 15, NamedTextColor.GOLD)
                    addOrUpdateScore(rank.toString(), 14, rank.color, TextDecoration.BOLD)
                    addOrUpdateScore(" ", 13)

                    val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
                    val timePlayedFormatted = timePlayedService.formatTime(timePlayed)
                    addOrUpdateScore("Temps de jeu :", 12, NamedTextColor.GOLD)
                    addOrUpdateScore(timePlayedFormatted, 11)
                    addOrUpdateScore(" ", 10)

                    val timeToNextRank = getTimeToNextRank(rank, timePlayed.seconds)
                    addOrUpdateScore("Grade suivant :", 9, NamedTextColor.GOLD)
                    addOrUpdateScore(timeToNextRank, 8)
                    addOrUpdateScore(" ", 7)

                    val worldName = player.world.name
                    addOrUpdateScore("Monde actuel :", 6, NamedTextColor.GOLD)
                    addOrUpdateScore(worldName, 5)

                    applyToPlayer(player)
                }
            }.runTaskTimer(it, 0L, 20L)
        }
    }

    private fun getTimeToNextRank(currentRank: PlayerRank, timePlayedInSeconds: Long): String {
        if (currentRank == PlayerRank.BATISSEUR) {
            return "Rank Maximum atteint !"
        }

        val nextRank = PlayerRank.entries
            .filter { it.hoursRequired > currentRank.hoursRequired }
            .minByOrNull { it.hoursRequired }

        if (nextRank == null || nextRank == PlayerRank.VIRTUOSE) {
            return "Aucun rang suivant"
        }

        val nextRankTimeInSeconds = nextRank.hoursRequired * 3600

        val remainingTimeInSeconds = nextRankTimeInSeconds - timePlayedInSeconds
        val daysRemaining = remainingTimeInSeconds / 86400
        val hoursRemaining = (remainingTimeInSeconds % 86400) / 3600
        val minutesRemaining = (remainingTimeInSeconds % 3600) / 60
        val secondsRemaining = remainingTimeInSeconds % 60

        return "${daysRemaining}j ${hoursRemaining}h ${minutesRemaining}min ${secondsRemaining}s"

    }
}