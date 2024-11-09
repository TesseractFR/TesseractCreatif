package onl.tesseract.event


import org.bukkit.Material
import org.bukkit.block.data.type.TrapDoor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class IronTrapDoorEventHandler : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onClickTrapDoor(event: PlayerInteractEvent) {
        val block = event.clickedBlock
        if (block == null || block.type != Material.IRON_TRAPDOOR || event.hand != EquipmentSlot.HAND) return

        if (event.player.isSneaking) {
            val trapDoor = block.blockData as TrapDoor
            trapDoor.isOpen = !trapDoor.isOpen
            block.blockData = trapDoor
        }
    }
}