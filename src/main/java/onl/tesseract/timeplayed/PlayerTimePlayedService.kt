package onl.tesseract.timeplayed

import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.timeplayed.entity.PlayerTimePlayedInfo
import onl.tesseract.timeplayed.persistence.PlayerTimePlayedRepository
import java.time.Duration
import java.util.*
import kotlin.math.floor

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

    fun getPlayerTotalTime(player: UUID): Duration {
        var playerTimePlayedInfo = getOrCreatePlayerTimePlayedInfo(player);
        return playerTimePlayedInfo.timePlayed.plus(playerTimePlayedInfo.timeBougth);
    }

    fun getTimeBeforeRankUp(player: UUID,nextRank: PlayerRank): Duration {
        return Duration.ofHours(nextRank.hoursRequired).minus(getPlayerTotalTime(player))
    }

    private fun getOrCreatePlayerTimePlayedInfo(player: UUID) : PlayerTimePlayedInfo {
        return repository.getById(player)?: PlayerTimePlayedInfo(player);
    }

    fun formatTime(duration: Duration): String {
        val nbDays = duration.toDays();
        val years = (nbDays/365.25).toInt();
        val months = (nbDays/30.4375).toInt() % 12;
        val days = floor(nbDays%30.4375).toInt()
        val hours = (duration.toHours() % 24);
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60
        val shours = if(hours < 10) "0$hours" else hours.toString();
        val sminutes = if(minutes < 10) "0$minutes" else minutes.toString();
        val sseconds = if(seconds < 10) "0$seconds" else seconds.toString();
        val parts = mutableListOf<String>()
        if(years > 0) parts.add("$years" + "a")
        if(months > 0) parts.add("$months" + "mo")
        if (days > 0) parts.add("$days" +"j")
        parts.add(shours+"h")
        parts.add(sminutes+"m")
        parts.add(sseconds+"s")
        return parts.joinToString(" ")
    }

}
