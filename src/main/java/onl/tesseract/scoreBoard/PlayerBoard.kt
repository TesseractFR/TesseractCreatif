package onl.tesseract.scoreBoard

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

                    val rank = rankService.getPlayerRank(player.uniqueId)
                    updateScore("Grade : $rank", 15)

                    val timePlayed = timePlayedService.getPlayerTimePlayed(player.uniqueId)
                    val timePlayedFormatted = timePlayedService.formatTime(timePlayed)
                    updateScore("Temps de jeu : $timePlayedFormatted", 14)

                    val timeToNextRank = getTimeToNextRank(rank, timePlayed.seconds)
                    updateScore("Temps avant grade suivant : $timeToNextRank", 13)

                    val worldName = player.world.name
                    updateScore("Monde : $worldName", 12)

                    applyToPlayer(player)
                }
            }.runTaskTimer(it, 0L, 20L)
        }
    }

    fun updateScore(entry: String, score: Int) {
        scoreboard.entries.forEach { existingEntry ->
            if (objective.getScore(existingEntry).score == score) {
                scoreboard.resetScores(existingEntry)
            }
        }
        addScore(entry, score)
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