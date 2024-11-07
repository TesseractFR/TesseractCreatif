package onl.tesseract.world

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import onl.tesseract.CommandsBookFactory
import onl.tesseract.entity.PlotWorld
import org.bukkit.Bukkit
import org.bukkit.Location

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class WorldManager {
    fun getWorldSpawn(plotWorld: PlotWorld): Location {
        val world = Bukkit.getWorld(plotWorld.getWorld())
            ?: throw IllegalArgumentException("Le monde " + plotWorld.getWorld() + " n'existe pas")
        return world.spawnLocation
    }

    companion object {
        @Getter
        private var INSTANCE: WorldManager? = null

        @JvmStatic
        fun getInstance(): WorldManager {
            if (INSTANCE == null) {
                INSTANCE = WorldManager()
            }
            return INSTANCE!!
        }
    }
}
