package onl.tesseract.creative.domain.nickname

import java.util.UUID

data class Nickname(
    val player: UUID,
    var nickname: String?,
)