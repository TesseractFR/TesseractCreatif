package onl.tesseract.rank

import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.player.PermissionService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.rank.entity.PlayerRankInfo
import onl.tesseract.rank.entity.StaffRank
import onl.tesseract.rank.persistence.PlayerRankInfoRepository
import java.util.*

/**
 * Service to interact with player's rank.
 */
class PlayerRankService(private val repository: PlayerRankInfoRepository){

    fun setPlayerRank(player : UUID, playerRank: PlayerRank){
        val rankInfo = getOrCreatePlayerRankInfo(player)
        rankInfo.playerRank = playerRank
        ServiceContainer[PermissionService::class.java].updatePermission(player)
        repository.save(rankInfo);
        ServiceContainer[PermissionService::class.java].updatePermission(player)
    }

    fun getPlayerRank(player: UUID) : PlayerRank {
        return getOrCreatePlayerRankInfo(player).playerRank;
    }

    fun getNextPlayerRank(player: UUID) : PlayerRank {
        return PlayerRank.entries[getPlayerRank(player).ordinal+1]
    }

    fun setStaffRank(player: UUID, staffRank: StaffRank?){
        val rankInfo = getOrCreatePlayerRankInfo(player)
        rankInfo.staffRank = staffRank
        repository.save(rankInfo);
    }

    fun getStaffRank(player: UUID) : StaffRank? {
        return getOrCreatePlayerRankInfo(player).staffRank
    }


    private fun getOrCreatePlayerRankInfo(player: UUID) : PlayerRankInfo {
        return repository.getById(player)?: PlayerRankInfo(player);
    }

}