package onl.tesseract.event;


import onl.tesseract.CreativePlayer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Door;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class IronDoorEventHandler implements Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onClickTrapDoor(final PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null || block.getType() != Material.IRON_DOOR || event.getHand() != EquipmentSlot.HAND)
            return;

        if (event.getPlayer().isSneaking()) {
            Door door = (Door) block.getBlockData();
            door.setOpen(!door.isOpen());
            block.setBlockData(door);
        }
    }
}