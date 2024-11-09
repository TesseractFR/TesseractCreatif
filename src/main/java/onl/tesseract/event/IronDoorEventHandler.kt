package onl.tesseract.event


import org.bukkit.Material
import org.bukkit.block.data.type.Door
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class IronDoorEventHandler : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onClickTrapDoor(event: PlayerInteractEvent) {
        val block = event.clickedBlock
        if (block == null || block.type != Material.IRON_DOOR || event.hand != EquipmentSlot.HAND) return

        if (event.player.isSneaking) {
            val door = block.blockData as Door
            door.isOpen = !door.isOpen
            block.blockData = door
        }
    }
}