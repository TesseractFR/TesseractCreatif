package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.CreativePlayer;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class BuildToolsMenu extends InventoryMenu {

    public BuildToolsMenu(InventoryMenu previous) {
        super(27, Component.text("Outils de construction du serveur").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, true), previous);
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addBackButton();
        addQuitButton();
        super.open(viewer);
    }
}
