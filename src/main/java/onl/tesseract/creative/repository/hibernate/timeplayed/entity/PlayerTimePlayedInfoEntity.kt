package onl.tesseract.creative.repository.hibernate.timeplayed.entity

import jakarta.persistence.*
import onl.tesseract.creative.domain.timeplayed.PlayerTimePlayedInfo
import onl.tesseract.creative.util.entityConverter.DurationConverter
import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.Duration
import java.util.*

@Table(name = "t_player_time_played")
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
class PlayerTimePlayedInfoEntity(
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    var uuid: UUID = UUID.randomUUID(),
    @Column(nullable = false)
    @Convert(converter = DurationConverter::class)
    var timePlayed: Duration = Duration.ZERO,


    @Column(nullable = false)
    @Convert(converter = DurationConverter::class)
    var timeBougth: Duration = Duration.ZERO,

    @Column(nullable = false)
    var temporalLys: Int = 0,

    @Convert(converter = DurationConverter::class)
    var nextMoneyDuration: Duration = Duration.ofMinutes(10),

    )


{
    fun toDomain(): PlayerTimePlayedInfo {
        return PlayerTimePlayedInfo(uuid, timePlayed, timeBougth, temporalLys, nextMoneyDuration)
    }
}

fun PlayerTimePlayedInfo.toEntity(): PlayerTimePlayedInfoEntity {
    return PlayerTimePlayedInfoEntity(uuid, timePlayed, timeBougth, temporalLys, nextMoneyDuration)
}