package onl.tesseract.home.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.PostLoad
import jakarta.persistence.PrePersist
import onl.tesseract.plot.entity.PlotWorld
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

@Embeddable
data class HomeLocation(
    @Column(updatable = false, nullable = false)
    var x: Double = 0.0,
    @Column(updatable = false, nullable = false)
    var y: Double = 0.0,
    @Column(updatable = false, nullable = false)
    var z: Double = 0.0,
    @Column(updatable = false, nullable = false)
    var world: String = " "
) {

    @Transient
    var location: Location? = null

    @PrePersist
    private fun prePersist() {
        location?.let {
            world = it.world.name
            x = it.x
            y = it.y
            z = it.z
        }
    }

    @PostLoad
    private fun postLoad() {
        location = Location(Bukkit.getWorld(world), x, y, z)
    }
}