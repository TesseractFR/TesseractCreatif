package onl.tesseract.home.entity

import jakarta.persistence.*
import org.bukkit.Location
import java.util.*

@Entity
@Table(name = "t_homes")
class Home(
    @EmbeddedId
    var homePK: HomePK = HomePK(),

    @Embedded
    var homeLocation: HomeLocation = HomeLocation()

) {

    var location: Location?
        get() = homeLocation.location
        set(value) {
            homeLocation.location = value
        }

    constructor(uuid: UUID, name: String, location: Location) : this() {
        homePK = HomePK(uuid, name)
        homeLocation = HomeLocation(location)
    }

    val name: String
        get() = homePK.name
}
