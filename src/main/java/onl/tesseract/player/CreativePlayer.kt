package onl.tesseract.player

import net.milkbowl.vault.permission.Permission
import onl.tesseract.Creatif
import onl.tesseract.permpack.PlayerPermPackService
import onl.tesseract.plot.PlayerPlotService
import onl.tesseract.rank.entity.PlayerRank
import onl.tesseract.rank.entity.Rank
import onl.tesseract.rank.entity.StaffRank
import onl.tesseract.service.CreativeServices
import onl.tesseract.service.CreativeServices.Companion.get
import onl.tesseract.tesseractlib.player.TPlayer
import org.bukkit.OfflinePlayer
import java.util.*
import java.util.function.Consumer

class CreativePlayer(player: OfflinePlayer) : TPlayer(player) {
    private val uuid = player.uniqueId

    fun updatePermission() {
        val permissions: Permission = Creatif.instance?.permissions!!
        //Unset Perm
        val playerUnrankConsumer = Consumer { rank: Rank ->
            permissions.playerRemoveGroup(
                null,
                offlinePlayer, rank.permGroup
            )
        }
        Arrays.stream(PlayerRank.entries.toTypedArray()).forEach(playerUnrankConsumer)
        Arrays.stream(StaffRank.entries.toTypedArray()).forEach(playerUnrankConsumer)
        CreativeServices[PlayerPlotService::class.java].resetPermission(permissions, this.offlinePlayer)
        CreativeServices[PlayerPermPackService::class.java].updatePermission(permissions, this.bukkitPlayer)
    }

}
