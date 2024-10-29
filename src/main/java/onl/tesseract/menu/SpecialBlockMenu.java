package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.Creatif;
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

    CreativePlayer player;

    public SpecialBlockMenu(CreativePlayer player, InventoryMenu previous) {
        super(27, Component.text("Blocs spéciaux").color(NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, true), previous);
        this.player = player;
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(0, Material.COMMAND_BLOCK, Component.text("Bloc de commande").color(NamedTextColor.GOLD).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.COMMAND_BLOCK));});

        addButton(10, Material.CHAIN_COMMAND_BLOCK, Component.text("Bloc de commande en chaîne").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.CHAIN_COMMAND_BLOCK));});

        addButton(2, Material.REPEATING_COMMAND_BLOCK, Component.text("Bloc de commande à répétition").color(NamedTextColor.AQUA).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.REPEATING_COMMAND_BLOCK));});

        addButton(12, Material.COMMAND_BLOCK_MINECART, Component.text("Wagonnet à bloc de commande").color(NamedTextColor.DARK_RED).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.COMMAND_BLOCK_MINECART));});

        addButton(4, Material.STRUCTURE_BLOCK, Component.text("Bloc de structure").color(NamedTextColor.DARK_PURPLE).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.STRUCTURE_BLOCK));});

        addButton(14, Material.JIGSAW, Component.text("Bloc de puzzle").color(NamedTextColor.DARK_PURPLE).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.JIGSAW));});

        addButton(6, Material.STRUCTURE_VOID, Component.text("Vide de structure").color(NamedTextColor.BLUE).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.STRUCTURE_VOID));});

        addButton(16, Material.BARRIER, Component.text("Barrière invisible").color(NamedTextColor.RED).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.BARRIER));});

        addButton(8, Material.LIGHT, Component.text("Lumière").color(NamedTextColor.YELLOW).decorate(TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION").color(NamedTextColor.GRAY),
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.LIGHT));});

        addBackButton();
        addQuitButton();
        super.open(viewer);
    }
}
