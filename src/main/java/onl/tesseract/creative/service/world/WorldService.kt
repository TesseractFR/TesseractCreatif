package onl.tesseract.creative.service.world

import onl.tesseract.creative.domain.plot.PlotWorld
import org.bukkit.Bukkit
import org.bukkit.Location
import org.springframework.stereotype.Service

@Service
class WorldService {
    fun getWorldSpawn(plotWorld: PlotWorld): Location {
        val world = Bukkit.getWorld(plotWorld.world)
        requireNotNull(world) { "Le monde " + plotWorld.world + " n'existe pas" }
        return world.spawnLocation
    }
}