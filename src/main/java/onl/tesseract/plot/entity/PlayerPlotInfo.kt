package onl.tesseract.plot.entity

import jakarta.persistence.*
import lombok.Data
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.*

@Entity
@Table(name = "t_player_plot")
@Data
class PlayerPlotInfo(
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
    var nbPlot1000: Int = 0){
}
