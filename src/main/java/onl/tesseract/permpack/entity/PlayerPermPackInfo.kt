package onl.tesseract.permpack.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.Instant
import java.util.*

@Entity
@Table(name = "t_player_perm_packs")
class PlayerPermPackInfo (
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    val uuid: UUID = UUID.randomUUID(),
    var arceonExpirationDate : Instant? = null,
    var playerParticlesExpirationDate : Instant? = null,
    var metaBrushExpirationDate : Instant? = null,
    var displayEntitiesExpirationDate : Instant? = null,
    ){
}