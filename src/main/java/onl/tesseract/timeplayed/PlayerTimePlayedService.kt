package onl.tesseract.timeplayed

import onl.tesseract.timeplayed.entity.PlayerTimePlayedInfo
import onl.tesseract.timeplayed.persistence.PlayerTimePlayedRepository
import java.time.Duration
import java.util.*

/**
 * Service to interact with player's time played.
 */
class PlayerTimePlayedService(private val repository: PlayerTimePlayedRepository){

    fun getPlayerTimePlayed(player: UUID): Duration {
        return getOrCreatePlayerTimePlayedInfo(player).timePlayed;
    }

    fun addPlayerTimePlayed(player: UUID, seconds : Long) {
        var playerTimePlayedInfo = getOrCreatePlayerTimePlayedInfo(player);
        playerTimePlayedInfo.timePlayed = playerTimePlayedInfo.timePlayed.plusSeconds(seconds);
        repository.save(playerTimePlayedInfo)
    }

    fun getPlayerTimeBought(player: UUID): Duration {
        return getOrCreatePlayerTimePlayedInfo(player).timeBougth;
    }

    fun addPlayerTimeBought(player: UUID, seconds : Long) {
        var playerTimePlayedInfo = getOrCreatePlayerTimePlayedInfo(player);
        playerTimePlayedInfo.timeBougth = playerTimePlayedInfo.timeBougth.plusSeconds(seconds);
        repository.save(playerTimePlayedInfo)
    }

    private fun getOrCreatePlayerTimePlayedInfo(player: UUID) : PlayerTimePlayedInfo {
        return repository.getById(player)?: PlayerTimePlayedInfo(player);
    }

    fun formatTime(duration: Duration): String {
        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60
        val parts = mutableListOf<String>()
        if (days > 0) parts.add("$days" +"j")
        if (hours > 0) parts.add("$hours" + "h")
        if (minutes > 0) parts.add("$minutes" + "m")
        if (seconds > 0) parts.add("$seconds"+ "s")
        return parts.joinToString(" ")
    }

}
