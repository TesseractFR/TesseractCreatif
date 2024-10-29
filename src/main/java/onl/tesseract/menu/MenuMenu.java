package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.CommandsBook;
import onl.tesseract.CreativePlayer;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MenuMenu extends InventoryMenu {
    CreativePlayer player;

    public MenuMenu(CreativePlayer player) {
        super(54, Component.text("Menu du Créatif").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true));
        this.player = player;
    }

    static final ItemStack teteTPWorldMenu = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=",
            "b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25");
    static final ItemStack teteSocialsMenu = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5NDg0M2QzNDBhYmFkYmQ2NDAxZWY0ZWM3NGRjZWM0YjY2OTY2MTA2NWJkMWEwMWY5YTU5MDVhODkxOWM3MiJ9fX0=",
            "3b94843d340abadbd6401ef4ec74dcec4b669661065bd1a01f9a5905a8919c72");
    static final ItemStack teteGrades = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=",
            "97e782b408f5456cfad0c43e8c501ef9efd01286297360be3b83fb16dc9cd2a5");

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(11, teteTPWorldMenu, ChatColor.BLUE + "Téléportations dans les mondes",
                ChatColor.GRAY + "Cliquez pour afficher les différents mondes disponible et vous y téléporter.",
                event -> new TPWorldMenu(this).open(viewer));

        addButton(12, teteSocialsMenu, ChatColor.LIGHT_PURPLE + "Réseaux sociaux",
                ChatColor.GRAY + "Cliquez pour afficher les différents réseaux sociaux.",
                event -> new SocialsMenu(this).open(viewer));

        addButton(13, teteGrades, ChatColor.DARK_GREEN + "Grades",
                ChatColor.GRAY + "Cliquez pour afficher les différents grades du serveur.",
                event -> new RankMenu(this).open(viewer));

        addButton(14, Material.LIGHT, ChatColor.DARK_AQUA + "Blocs spéciaux",
                ChatColor.GRAY + "Cliquez pour afficher les différents blocs spéciaux du serveur (hors inventaire).",
                event -> new SpecialBlockMenu(player, this).open(viewer));

        addButton(15, Material.WOODEN_AXE, ChatColor.DARK_PURPLE + "Outils/Plugins du serveur",
                ChatColor.GRAY + "Cliquez pour afficher les différents outils et plugins utilisés sur le serveur pour construire.",
                event -> new PluginsToolsMenu(this).open(viewer));

        addButton(16, Material.BOOK, ChatColor.RED + "Le Build pour les Nuls",
                ChatColor.GRAY + "Cliquez pour recevoir le guide des commandes de build essentielles pour bien démarrer votre construction !",
                event -> {
                    close();
                    player.getBukkitPlayer().getInventory().addItem(CommandsBook.createGuideBook(player));
                });

        //TODO bouton affichage Info perso (plot/temps de jeu ...)
        //TODO bouton menu boutique
        //TODO bouton menu cmd build

        addQuitButton();
        super.open(viewer);
    }
}
