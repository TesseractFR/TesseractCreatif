package onl.tesseract.permpack

import net.milkbowl.vault.permission.Permission
import onl.tesseract.permpack.entity.PermPack
import onl.tesseract.permpack.entity.PlayerPermPackInfo
import onl.tesseract.permpack.persistence.PlayerPermPackInfoRepository
import org.bukkit.entity.Player
import java.time.Duration
import java.time.Instant
import java.util.*


/**
 * Service to interact with player perm pack.
 */
class PlayerPermPackService(private val repository: PlayerPermPackInfoRepository) {
    fun updatePermission(permission: Permission, player: Player) {
        for (permPack: PermPack in PermPack.entries) {
            permission.playerRemoveGroup(player, permPack.groupPerm)
        }
        if(!getTimeLeftArceon(player.uniqueId).isZero){
            permission.playerAddGroup(player,PermPack.ARCEON.groupPerm)
        }
    }

    fun addTimeLeftArceon(uuid: UUID, duration: Duration) {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        val arceonInstant = playerPermPackInfo.arceonExpirationDate?: Instant.now()
        playerPermPackInfo.arceonExpirationDate = arceonInstant.plus(duration)
        repository.save(playerPermPackInfo)
    }

    fun getTimeLeftArceon(uuid: UUID): Duration {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        return Duration.between(Instant.now(),playerPermPackInfo.arceonExpirationDate?:Instant.now())
    }




    private fun getOrCreatePlayerPermPackInfo(player: UUID) : PlayerPermPackInfo {
        return repository.getById(player)?: PlayerPermPackInfo(player);
    }



}