package onl.tesseract.creative.repository.hibernate.home.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.bukkit.Bukkit
import org.bukkit.Location

@Embeddable
data class HomeLocationEntity(
    @Column(updatable = false, nullable = false)
    val x: Double,
    @Column(updatable = false, nullable = false)
    val y: Double,
    @Column(updatable = false, nullable = false)
    val z: Double,
    @Column(updatable = false, nullable = false)
    val world: String,
) {
    fun toDomain(): Location {
        return Location(Bukkit.getWorld(world), x, y, z)
    }

}

fun Location.toEntity(): HomeLocationEntity {
    return HomeLocationEntity(x, y, z, world.name)
}
