package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.CreativePlayer;
import onl.tesseract.entity.PlotWorld;
import onl.tesseract.tesseractlib.menu.boutique.global.GlobalBoutiqueMenu;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import onl.tesseract.world.WorldManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpecialBlockMenu extends InventoryMenu {

    public SpecialBlockMenu(InventoryMenu previous) {
        super(27, Component.text("Blocs spéciaux").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true), previous);
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addBackButton();
        addQuitButton();

        super.open(viewer);
    }
}
