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

    private fun getOrCreatePlayerTimePlayedInfo(player: UUID) : PlayerTimePlayedInfo {
        return repository.getById(player)?: PlayerTimePlayedInfo(player);
    }

}
