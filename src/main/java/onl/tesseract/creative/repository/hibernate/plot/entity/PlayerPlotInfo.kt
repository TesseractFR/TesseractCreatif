package onl.tesseract.creative.repository.hibernate.plot.entity

import jakarta.persistence.*
import lombok.Data
import onl.tesseract.creative.domain.plot.PlayerPlotInfo
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.*

@Entity
@Table(name = "t_player_plot")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Data
class PlayerPlotInfoEntity(
    @Id
    @Column(updatable = false, nullable = false, columnDefinition = "VARCHAR(36)", unique = true)
    @JdbcTypeCode(
        Types.VARCHAR
    )
    val uuid: UUID = UUID.randomUUID(),

    @Column
    var nbPlot100: Int = 0,

    @Column
    var nbPlot250: Int = 0,

    @Column
    var nbPlot500: Int = 0,

    @Column
    var nbPlot1000: Int = 0,
){

    fun toDomain(): PlayerPlotInfo {
        return PlayerPlotInfo(uuid, nbPlot100, nbPlot250, nbPlot500, nbPlot1000)
    }
}

fun PlayerPlotInfo.toEntity(): PlayerPlotInfoEntity {
    return PlayerPlotInfoEntity(uuid, nbPlot100, nbPlot250, nbPlot500, nbPlot1000)
}
