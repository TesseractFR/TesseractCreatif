package onl.tesseract.world

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import onl.tesseract.plot.entity.PlotWorld
import org.bukkit.Bukkit
import org.bukkit.Location

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class WorldManager {
    fun getWorldSpawn(plotWorld: PlotWorld): Location {
        val world = Bukkit.getWorld(plotWorld.world)
        requireNotNull(world) { "Le monde " + plotWorld.world + " n'existe pas" }
        return world.spawnLocation
    }

    companion object {
        @Getter
        private val instance = WorldManager()
    }
}
