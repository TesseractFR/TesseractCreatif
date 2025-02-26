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
        if (getTimeLeftArceon(player.uniqueId).isPositive) {
            permission.playerAddGroup(null, player, PermPack.ARCEON.groupPerm)
        }
        if (getTimeLeftMetaBrush(player.uniqueId).isPositive) {
            permission.playerAddGroup(null, player, PermPack.META_BRUSH.groupPerm)
        }
        if (getTimeLeftEzedit(player.uniqueId).isPositive) {
            permission.playerAddGroup(null, player, PermPack.EZEDIT.groupPerm)
        }
    }

    fun addTimeLeftArceon(uuid: UUID, duration: Duration) {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var arceonInstant = playerPermPackInfo.arceonExpirationDate
        if(arceonInstant == null || arceonInstant <= Instant.now()){
            arceonInstant = Instant.now()
        }
        playerPermPackInfo.arceonExpirationDate = arceonInstant!!.plus(duration)
        repository.save(playerPermPackInfo)
    }

    fun getTimeLeftArceon(uuid: UUID): Duration {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var arceonInstant = playerPermPackInfo.arceonExpirationDate
        if(arceonInstant == null || arceonInstant <= Instant.now()){
            arceonInstant = Instant.now()
        }
        return Duration.between(Instant.now(),arceonInstant)
    }

    fun addTimeLeftEzedit(uuid: UUID, duration: Duration) {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var ezeditInstant = playerPermPackInfo.ezeditExpirationDate
        if (ezeditInstant == null || ezeditInstant <= Instant.now()) {
            ezeditInstant = Instant.now()
        }
        playerPermPackInfo.ezeditExpirationDate = ezeditInstant!!.plus(duration)
        repository.save(playerPermPackInfo)
    }

    fun getTimeLeftEzedit(uuid: UUID): Duration {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var ezeditInstant = playerPermPackInfo.ezeditExpirationDate
        if (ezeditInstant == null || ezeditInstant <= Instant.now()) {
            ezeditInstant = Instant.now()
        }
        return Duration.between(Instant.now(), ezeditInstant)
    }


    fun addTimeLeftMetaBrush(uuid: UUID, duration: Duration) {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var metaBrushInstant = playerPermPackInfo.metaBrushExpirationDate
        if(metaBrushInstant == null || metaBrushInstant <= Instant.now()){
            metaBrushInstant = Instant.now()
        }
        playerPermPackInfo.metaBrushExpirationDate = metaBrushInstant!!.plus(duration)
        repository.save(playerPermPackInfo)
    }
    fun getTimeLeftMetaBrush(uuid: UUID): Duration {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var metaBrushInstant = playerPermPackInfo.metaBrushExpirationDate
        if(metaBrushInstant == null || metaBrushInstant <= Instant.now()){
            metaBrushInstant = Instant.now()
        }
        return Duration.between(Instant.now(),metaBrushInstant)
    }

    private fun getOrCreatePlayerPermPackInfo(player: UUID) : PlayerPermPackInfo {
        return repository.getById(player)?: PlayerPermPackInfo(player);
    }



}