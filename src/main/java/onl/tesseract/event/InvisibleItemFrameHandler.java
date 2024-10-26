package onl.tesseract.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.HashMap;
import java.util.Map;

public class InvisibleItemFrameHandler implements Listener {

    Map<Location, Integer> ticks = new HashMap<Location, Integer>();
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)

    public void onClickFrame(PlayerInteractEntityEvent event) {
        Bukkit.getServer().getCurrentTick();
        if (event.getPlayer().isSneaking() && event.getRightClicked() instanceof ItemFrame itemFrame) {
            int lastTick = ticks.getOrDefault(itemFrame.getLocation(),0);
            if (lastTick != Bukkit.getServer().getCurrentTick()) {
                itemFrame.setVisible(!itemFrame.isVisible());
                event.setCancelled(true);
                ticks.put(itemFrame.getLocation(), Bukkit.getServer().getCurrentTick());
            }

        }

    }
}

