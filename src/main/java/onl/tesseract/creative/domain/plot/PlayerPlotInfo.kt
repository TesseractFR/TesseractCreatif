package onl.tesseract.creative.domain.plot

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import org.hibernate.annotations.JdbcTypeCode
import java.sql.Types
import java.util.UUID


data class PlayerPlotInfo(

    val uuid: UUID,

    var nbPlot100: Int = 0,

    var nbPlot250: Int = 0,

    var nbPlot500: Int = 0,

    var nbPlot1000: Int = 0,
)