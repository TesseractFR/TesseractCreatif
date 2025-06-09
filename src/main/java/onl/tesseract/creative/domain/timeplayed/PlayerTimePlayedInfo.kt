package onl.tesseract.creative.domain.timeplayed

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import onl.tesseract.creative.util.entityConverter.DurationConverter
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.Duration
import java.util.UUID

@Table(name = "t_player_time_played")
@Entity
class PlayerTimePlayedInfo(
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

    ) {
}