package onl.tesseract.creative.service.player

import net.milkbowl.vault.permission.Permission
import onl.tesseract.creative.PLUGIN_INSTANCE
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.domain.rank.Rank
import onl.tesseract.creative.domain.rank.StaffRank
import onl.tesseract.creative.service.permpack.PlayerPermPackService
import onl.tesseract.creative.util.playerPlotService
import onl.tesseract.creative.util.playerRankService
import org.bukkit.Bukkit
import org.springframework.stereotype.Service
import java.util.*
import java.util.function.Consumer

//TODO NE DEVRAIT PAS UTIISER LES AUTRES SERVICES
@Service
class PermissionService(
    private val permPackService: PlayerPermPackService,
) {
    fun updatePermission(uuid: UUID) {
        val player = Bukkit.getOfflinePlayer(uuid)
        val permissions: Permission = PLUGIN_INSTANCE.permissions!!
        //Unset Perm
        val playerUnrankConsumer = Consumer { rank: Rank ->
            permissions.playerRemoveGroup(
                null,
                player, rank.permGroup
            )
        }
        val playerRank = playerRankService().getPlayerRank(uuid)
        val staffRank = playerRankService().getStaffRank(uuid)
        permissions.playerAddGroup(null, player, playerRank.permGroup)
        if (staffRank != null) {
            permissions.playerAddGroup(null, player, staffRank.permGroup)
        }
        Arrays.stream(PlayerRank.entries.toTypedArray())
                .filter { it: Rank -> it != playerRank }
                .forEach(playerUnrankConsumer)
        Arrays.stream(StaffRank.entries.toTypedArray())
                .filter { it: Rank -> it != staffRank }
                .forEach(playerUnrankConsumer)
        playerPlotService().resetPermission(permissions, player)
        if (player.isOnline)
            permPackService.updatePermission(permissions, player.player!!)
    }
}