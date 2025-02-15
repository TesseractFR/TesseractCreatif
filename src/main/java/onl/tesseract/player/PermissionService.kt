package onl.tesseract.player

import net.milkbowl.vault.permission.Permission
import onl.tesseract.Creatif
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.permpack.PlayerPermPackService
import onl.tesseract.plot.PlayerPlotService
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.rank.entity.Rank
import onl.tesseract.rank.entity.StaffRank
import org.bukkit.Bukkit
import java.util.*
import java.util.function.Consumer

class PermissionService {
    fun updatePermission(uuid: UUID) {
        val player = Bukkit.getOfflinePlayer(uuid)
        val permissions: Permission = Creatif.instance?.permissions!!
        //Unset Perm
        val playerUnrankConsumer = Consumer { rank: Rank ->
            permissions.playerRemoveGroup(
                null,
                player, rank.permGroup
            )
        }
        val playerRankService = ServiceContainer[PlayerRankService::class.java]
        val playerRank = playerRankService.getPlayerRank(uuid)
        val staffRank = playerRankService.getStaffRank(uuid)!!
        permissions.playerAddGroup(null, player, playerRank.permGroup)
        permissions.playerAddGroup(null, player, staffRank.permGroup)
        Arrays.stream(PlayerRank.entries.toTypedArray())
                .filter { it: Rank -> it != playerRank }
                .forEach(playerUnrankConsumer)
        Arrays.stream(StaffRank.entries.toTypedArray())
                .filter { it: Rank -> it != staffRank }
                .forEach(playerUnrankConsumer)
        ServiceContainer[PlayerPlotService::class.java].resetPermission(permissions, player)
        if (player.isOnline)
            ServiceContainer[PlayerPermPackService::class.java].updatePermission(permissions, player.player!!)
    }
}