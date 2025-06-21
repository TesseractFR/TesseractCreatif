package onl.tesseract.creative.domain.rank

import java.util.UUID

data class PlayerRankInfo (
    val uuid: UUID,

    var playerRank: PlayerRank = PlayerRank.APPRENTI,

    var staffRank: StaffRank? = null
)