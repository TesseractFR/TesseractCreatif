package onl.tesseract.creative.domain.permpack

import java.time.Instant
import java.util.*


data class PlayerPermPackInfo(

    val uuid: UUID,
    var arceonExpirationDate : Instant? = null,
    var metaBrushExpirationDate : Instant? = null,
    var ezeditExpirationDate: Instant? = null,
)