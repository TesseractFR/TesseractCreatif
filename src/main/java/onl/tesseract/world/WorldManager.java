package onl.tesseract.world;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import onl.tesseract.entity.PlotWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WorldManager {
    @Getter
    private static WorldManager instance = new WorldManager();

    public Location getWorldSpawn(PlotWorld plotWorld) {
        World world = Bukkit.getWorld(plotWorld.getWorld());
        if (world == null) {
            throw new IllegalArgumentException("Le monde "+plotWorld.getWorld()+" n'existe pas");
        }
        return world.getSpawnLocation();
    }
}
