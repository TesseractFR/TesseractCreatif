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

    fun setPlayerTimePlayed(player: UUID, seconds: Long) {
        var playerTimePlayedInfo = getOrCreatePlayerTimePlayedInfo(player)
        playerTimePlayedInfo.timePlayed = Duration.ofSeconds(seconds)
        repository.save(playerTimePlayedInfo)
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

}
