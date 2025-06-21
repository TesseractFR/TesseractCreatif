package onl.tesseract.creative.repository.hibernate.permpack.entity

import jakarta.persistence.Cacheable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import onl.tesseract.creative.domain.home.Home
import onl.tesseract.creative.domain.permpack.PlayerPermPackInfo
import onl.tesseract.creative.repository.hibernate.home.entity.HomeEntity
import onl.tesseract.creative.repository.hibernate.home.entity.HomeEntityPK
import onl.tesseract.creative.repository.hibernate.home.entity.toEntity
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.Instant
import java.util.*

@Entity
@Table(name = "t_player_perm_packs")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class PlayerPermPackInfoEntity(
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    val uuid: UUID,
    var arceonExpirationDate: Instant? = null,
    var metaBrushExpirationDate: Instant? = null,
    var ezeditExpirationDate: Instant? = null,
) {

    fun toDomain(): PlayerPermPackInfo {
        return PlayerPermPackInfo(uuid, arceonExpirationDate, metaBrushExpirationDate, ezeditExpirationDate)
    }
}

fun PlayerPermPackInfo.toEntity(): PlayerPermPackInfoEntity {
    return PlayerPermPackInfoEntity(uuid, arceonExpirationDate, metaBrushExpirationDate, ezeditExpirationDate)
}