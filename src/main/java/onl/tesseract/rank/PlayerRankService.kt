package onl.tesseract.rank

import onl.tesseract.rank.persistence.PlayerRankInfoRepository
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.rank.entity.PlayerRankInfo
import java.util.*

/**
 * Service to interact with player's rank.
 */
class PlayerRankService(private val repository: PlayerRankInfoRepository){

    fun setPlayerRank(player : UUID, playerRank: PlayerRank){
        val rankInfo = getOrCreatePlayerRankInfo(player)
        rankInfo.playerRank = playerRank
        repository.save(rankInfo);
    }

    fun getPlayerRank(player: UUID) : PlayerRank {
        return getOrCreatePlayerRankInfo(player).playerRank;
    }

    private fun getOrCreatePlayerRankInfo(player: UUID) : PlayerRankInfo {
        return repository.getById(player)?: PlayerRankInfo(player);
    }

}