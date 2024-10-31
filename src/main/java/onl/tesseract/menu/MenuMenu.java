package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.CommandsBookFactory;
import onl.tesseract.CreativePlayer;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MenuMenu extends InventoryMenu {

    CreativePlayer player;

    static final ItemStack teteTPWorldMenu = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=",
            "b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25");
    static final ItemStack teteSocialsMenu = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5NDg0M2QzNDBhYmFkYmQ2NDAxZWY0ZWM3NGRjZWM0YjY2OTY2MTA2NWJkMWEwMWY5YTU5MDVhODkxOWM3MiJ9fX0=",
            "3b94843d340abadbd6401ef4ec74dcec4b669661065bd1a01f9a5905a8919c72");
    static final ItemStack teteGrades = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=",
            "97e782b408f5456cfad0c43e8c501ef9efd01286297360be3b83fb16dc9cd2a5");

    public MenuMenu(CreativePlayer player) {
        super(54, Component.text("Menu du Créatif", NamedTextColor.RED, TextDecoration.BOLD));
        this.player = player;
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(11, teteTPWorldMenu,
                Component.text("Téléportations dans les mondes", NamedTextColor.BLUE),
                Component.text("Cliquez pour afficher les différents mondes disponibles et vous y téléporter.", NamedTextColor.GRAY),
                event -> new TPWorldMenu(this).open(viewer));

        addButton(12, teteSocialsMenu,
                Component.text("Réseaux sociaux", NamedTextColor.LIGHT_PURPLE),
                Component.text("Cliquez pour afficher les différents réseaux sociaux.", NamedTextColor.GRAY),
                event -> new SocialsMenu(this).open(viewer));

        addButton(13, teteGrades,
                Component.text("Grades", NamedTextColor.DARK_GREEN),
                Component.text("Cliquez pour afficher les différents grades du serveur.", NamedTextColor.GRAY),
                event -> new RankMenu(this).open(viewer));

        addButton(14, Material.LIGHT,
                Component.text("Blocs spéciaux", NamedTextColor.DARK_AQUA),
                Component.text("Cliquez pour afficher les différents blocs spéciaux du serveur (hors inventaire).", NamedTextColor.GRAY),
                event -> new SpecialBlockMenu(player, this).open(viewer));

        addButton(15, Material.WOODEN_AXE,
                Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE),
                Component.text("Cliquez pour afficher les différents outils et plugins utilisés sur le serveur pour construire.", NamedTextColor.GRAY),
                event -> new PluginsToolsMenu(this).open(viewer));

        addButton(16, Material.BOOK,
                Component.text("Le Build pour les Nuls", NamedTextColor.RED),
                Component.text("Cliquez pour recevoir le guide des commandes de build essentielles pour bien démarrer votre construction !", NamedTextColor.GRAY),
                event -> {
                    close();
                    CommandsBookFactory.getInstance().giveGuideBook(player.getBukkitPlayer());
                });

        addQuitButton();
        super.open(viewer);
    }
}


//TODO bouton affichage Info perso (plot/temps de jeu ...)
//TODO bouton menu boutique