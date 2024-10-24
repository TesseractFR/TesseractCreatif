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

public class MenuMenu extends InventoryMenu {
    CreativePlayer player;

    public MenuMenu() {
        super(27, Component.text("Menu du Créatif").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true));
        this.player = player;
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(12, Material.AMETHYST_CLUSTER, ChatColor.LIGHT_PURPLE + "Téléportations dans les mondes",
                ChatColor.GRAY + "Cliquez pour afficher les différents mondes disponible et vous y téléporter.",
                event -> new TPWorldMenu(this).open(viewer));

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
