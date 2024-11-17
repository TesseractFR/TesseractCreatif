package onl.tesseract.timeplayed

import onl.tesseract.rank.entity.PlayerRank
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

}
