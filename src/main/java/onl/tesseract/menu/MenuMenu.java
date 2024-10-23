package onl.tesseract.menu;

import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.entity.Player;

public class MenuMenu extends InventoryMenu {
    public MenuMenu() {
        super(9, "Menu");
    }

    @Override
    public void open(Player viewer){
        //TODO bouton menu de block spéciaux
        //TODO bouton affichage réseaux sociaux
        //TODO bouton affichage Info perso (plot/temps de jeu ...)
        //TODO bouton menu boutique
        //TODO bouton menu grade
        //TODO bouton menu tp Monde
        //TODO bouton menu cmd
        super.open(viewer);
    }
}
