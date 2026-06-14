package onl.tesseract.creative.service.rank

import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.rank.PlayerRankInfo
import onl.tesseract.creative.domain.rank.StaffRank
import onl.tesseract.creative.repository.generic.rank.PlayerRankInfoRepository
import onl.tesseract.creative.service.player.PermissionService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import kotlin.math.max

/**
 * Service to interact with player's rank.
 */
@Service
open class PlayerRankService(
    private val repository: PlayerRankInfoRepository,
    private val permissionService: PermissionService,
    private val timePlayedService: PlayerTimePlayedService,
) {

    fun setPlayerRank(player: UUID, playerRank: PlayerRank) {
        val rankInfo = getOrCreatePlayerRankInfo(player)
        rankInfo.playerRank = playerRank
        val playedTimeDiff = (playerRank.hoursRequired * 3600) - timePlayedService.getPlayerTimePlayed(player)
                .toSeconds()
        timePlayedService.setPlayerTimeBought(player, max(playedTimeDiff, 0))
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

    fun isPrestige(player: UUID): Boolean {
        return getPrestige(player).isAfter(LocalDateTime.now())
    }

    fun getPrestige(player: UUID): LocalDateTime {
        return getOrCreatePlayerRankInfo(player).prestigeRank ?: LocalDateTime.now()
    }

    fun resetPrestige(player: UUID) {
        val pri = getOrCreatePlayerRankInfo(player)
        pri.prestigeRank = null
        repository.save(pri)
    }

    fun addPrestige(player: UUID, days: Long) {
        val pri = getOrCreatePlayerRankInfo(player)
        if (pri.prestigeRank == null) pri.prestigeRank = LocalDateTime.now()
        pri.prestigeRank = pri.prestigeRank?.plusDays(days)
        repository.save(pri)
    }
}