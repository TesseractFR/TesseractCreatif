package onl.tesseract.creative.domain.home

import org.bukkit.Location
import java.util.UUID

data class Home(
    val owner: UUID,
    val name: String,
    val location: Location,
)