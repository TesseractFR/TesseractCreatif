package onl.tesseract.timeplayed.entity

import jakarta.persistence.*
import onl.tesseract.entityConverter.DurationConverter
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.time.Duration
import java.util.*

@Table(name = "t_player_time_played")
@Entity
class PlayerTimePlayedInfo (
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    var uuid: UUID = UUID.randomUUID(),
    @Convert(converter = DurationConverter::class)
    var timePlayed: Duration = Duration.ZERO,


    @Convert(converter = DurationConverter::class)
    var timeBougth: Duration = Duration.ZERO

    )


{
}