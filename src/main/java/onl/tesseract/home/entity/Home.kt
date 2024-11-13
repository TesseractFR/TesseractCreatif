package onl.tesseract.home.entity

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.Data
import lombok.experimental.FieldDefaults
import org.bukkit.Location
import java.util.*

@Entity
@Table(name = "t_homes")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
data class Home(
    @EmbeddedId
    var homePK: HomePK = HomePK(),

    @Embedded
    var homeLocation: HomeLocation = HomeLocation()

) {
    val name: String
        get() = homePK.name
}
