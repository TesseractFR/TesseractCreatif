package onl.tesseract.home.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.experimental.FieldDefaults
import org.bukkit.Location

@Entity
@Table(name = "t_homes")
@FieldDefaults(level = AccessLevel.PRIVATE)
data class Home(
    @EmbeddedId
    private val homePK: HomePK = HomePK(),

    @Embedded
    private var homeLocation: HomeLocation = HomeLocation()

) {
    val name: String
        get() = homePK.name

    val location : Location?
        get() = homeLocation.location
}
