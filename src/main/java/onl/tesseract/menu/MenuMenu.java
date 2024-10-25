package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.CreativePlayer;
import onl.tesseract.tesseractlib.menu.boutique.global.GlobalBoutiqueMenu;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MenuMenu extends InventoryMenu {
    CreativePlayer player;

    public MenuMenu() {
        super(27, Component.text("Menu du Créatif").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true));
        this.player = player;
    }

    static final ItemStack teteTPWorldMenu = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=",
            "b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25");
    static final ItemStack teteSocialsMenu = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ4ODU0NWQ1N2M5ZWVkNTJjM2U1NDdlOTZjNDVkYWJiYjdjZjVmOThkNGM4ZmU2MWRjNmY2OWFiYTBhZWY5NiJ9fX0=",
            "3488545d57c9eed52c3e547e96c45dabbb7cf5f98d4c8fe61dc6f69aba0aef96");

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(11, teteTPWorldMenu, ChatColor.BLUE + "Téléportations dans les mondes",
                ChatColor.GRAY + "Cliquez pour afficher les différents mondes disponible et vous y téléporter.",
                event -> new TPWorldMenu(this).open(viewer));

        addButton(12, teteSocialsMenu, ChatColor.LIGHT_PURPLE + "Réseaux sociaux",
                ChatColor.GRAY + "Cliquez pour afficher les différents réseaux sociaux.",
                event -> new SocialsMenu(this).open(viewer));

        //TODO bouton menu de block spéciaux
        //TODO bouton affichage réseaux sociaux
        //TODO bouton affichage Info perso (plot/temps de jeu ...)
        //TODO bouton menu boutique
        //TODO bouton menu grade
        //TODO bouton menu tp Monde
        //TODO bouton menu cmd build

        super.open(viewer);
    }
}
