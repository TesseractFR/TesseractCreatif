package onl.tesseract.world

import onl.tesseract.plot.entity.PlotWorld
import org.bukkit.Bukkit
import org.bukkit.Location

class WorldManager private constructor(){
    fun getWorldSpawn(plotWorld: PlotWorld): Location {
        val world = Bukkit.getWorld(plotWorld.world)
        requireNotNull(world) { "Le monde " + plotWorld.world + " n'existe pas" }
        return world.spawnLocation
    }

    companion object {
        val instance = WorldManager()
    }
}
