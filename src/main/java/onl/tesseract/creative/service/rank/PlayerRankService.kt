package onl.tesseract.creative.service.rank

import onl.tesseract.creative.service.player.PermissionService

import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.rank.PlayerRankInfo
import onl.tesseract.creative.domain.rank.StaffRank
import onl.tesseract.creative.repository.generic.rank.PlayerRankInfoRepository
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Service to interact with player's rank.
 */
@Service
open class PlayerRankService(
    private val repository: PlayerRankInfoRepository,
    private val permissionService: PermissionService,
) {

    fun setPlayerRank(player: UUID, playerRank: PlayerRank) {
        val rankInfo = getOrCreatePlayerRankInfo(player)
        rankInfo.playerRank = playerRank
        permissionService.updatePermission(player)
        repository.save(rankInfo);
        permissionService.updatePermission(player)
    }

    fun getPlayerRank(player: UUID): PlayerRank {
        return getOrCreatePlayerRankInfo(player).playerRank;
    }

    fun getNextPlayerRank(player: UUID): PlayerRank {
        return PlayerRank.entries[getPlayerRank(player).ordinal + 1]
    }

    fun setStaffRank(player: UUID, staffRank: StaffRank?) {
        val rankInfo = getOrCreatePlayerRankInfo(player)
        rankInfo.staffRank = staffRank
        repository.save(rankInfo);
    }

    fun getStaffRank(player: UUID): StaffRank? {
        return getOrCreatePlayerRankInfo(player).staffRank
    }


    private fun getOrCreatePlayerRankInfo(player: UUID): PlayerRankInfo {
        return repository.getById(player) ?: PlayerRankInfo(player);
    }

}