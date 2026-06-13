package onl.tesseract.creative.domain.rank

import java.time.LocalDateTime
import java.util.*

data class PlayerRankInfo (
    val uuid: UUID,

    var playerRank: PlayerRank = PlayerRank.APPRENTI,

    var staffRank: StaffRank? = null,

    var prestigeRank: LocalDateTime? = null,
)