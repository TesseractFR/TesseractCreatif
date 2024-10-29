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

        addButton(0, Material.COMMAND_BLOCK, ChatColor.GOLD + "Bloc de commande",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.COMMAND_BLOCK));});
        addButton(10, Material.CHAIN_COMMAND_BLOCK, ChatColor.GREEN  + "Bloc de commande en chaîne",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.CHAIN_COMMAND_BLOCK));});
        addButton(2, Material.REPEATING_COMMAND_BLOCK, ChatColor.AQUA + "Bloc de commande a àépétition",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.REPEATING_COMMAND_BLOCK));});
        addButton(12, Material.COMMAND_BLOCK_MINECART, ChatColor.DARK_RED + "Wagonnet à bloc de commande",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.COMMAND_BLOCK_MINECART));});
        addButton(4, Material.STRUCTURE_BLOCK, ChatColor.DARK_PURPLE + "Bloc de structure",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.STRUCTURE_BLOCK));});
        addButton(14, Material.JIGSAW, ChatColor.DARK_PURPLE + "Bloc de puzzle",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.JIGSAW));});
        addButton(6, Material.STRUCTURE_VOID, ChatColor.BLUE + "Vide de structure",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.STRUCTURE_VOID));});
        addButton(16, Material.BARRIER, ChatColor.RED + "Barrière invisible",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.BARRIER));});
        addButton(8, Material.LIGHT, ChatColor.YELLOW + "Lumière",
                ChatColor.GRAY + "AJOUTER DESCRIPTION",
                event -> {player.getBukkitPlayer().getInventory().addItem(new ItemStack(Material.LIGHT));});

        addBackButton();
        addQuitButton();
        super.open(viewer);
    }
}
