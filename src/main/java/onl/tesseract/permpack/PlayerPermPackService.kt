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

    fun addTimeLeftPlayerParticule(uuid: UUID, duration: Duration) {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var playerParticuleInstant = playerPermPackInfo.playerParticlesExpirationDate
        if(playerParticuleInstant == null || playerParticuleInstant <= Instant.now()){
            playerParticuleInstant = Instant.now()
        }
        playerPermPackInfo.playerParticlesExpirationDate = playerParticuleInstant!!.plus(duration)
        repository.save(playerPermPackInfo)
    }

    fun getTimeLeftPlayerParticule(uuid: UUID): Duration {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var playerParticuleInstant = playerPermPackInfo.playerParticlesExpirationDate
        if(playerParticuleInstant == null || playerParticuleInstant <= Instant.now()){
            playerParticuleInstant = Instant.now()
        }
        return Duration.between(Instant.now(),playerParticuleInstant)
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

    fun addTimeLeftDisplayEntity(uuid: UUID, duration: Duration) {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var displayEntityInstant = playerPermPackInfo.displayEntitiesExpirationDate
        if(displayEntityInstant == null || displayEntityInstant <= Instant.now()){
            displayEntityInstant = Instant.now()
        }
        playerPermPackInfo.displayEntitiesExpirationDate = displayEntityInstant!!.plus(duration)
        repository.save(playerPermPackInfo)
    }

    fun getTimeLeftDisplayEntity(uuid: UUID): Duration {
        val playerPermPackInfo = getOrCreatePlayerPermPackInfo(uuid)
        var displayEntityInstant = playerPermPackInfo.displayEntitiesExpirationDate
        if(displayEntityInstant == null || displayEntityInstant <= Instant.now()){
           displayEntityInstant = Instant.now()
        }
        return Duration.between(Instant.now(),displayEntityInstant)
    }
    private fun getOrCreatePlayerPermPackInfo(player: UUID) : PlayerPermPackInfo {
        return repository.getById(player)?: PlayerPermPackInfo(player);
    }



}