package onl.tesseract.event

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.ItemFrame
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class InvisibleItemFrameHandler : Listener {
    private var ticks: MutableMap<Location, Int> = HashMap()
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun onClickFrame(event: PlayerInteractEntityEvent) {
        Bukkit.getServer().currentTick
        val itemFrame = event.rightClicked as? ItemFrame
        if (event.player.isSneaking && itemFrame != null) {
            val lastTick = ticks.getOrDefault(itemFrame.location, 0)
            if (lastTick != Bukkit.getServer().currentTick) {
                itemFrame.isVisible = !itemFrame.isVisible
                event.isCancelled = true
                ticks[itemFrame.location] = Bukkit.getServer().currentTick
            }
        }
    }
}