package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RankMenu extends InventoryMenu {

    static final ItemStack teteApprenti = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWQwMzgyYjY1ZjQ0NmI3NDkxNTg2ZGE2MGE2MThlMTU3NTU2NWI5M2Q1NmIwZjAzZWVjNWQ3NjlkMmY1NmFjYSJ9fX0=",
            "ad0382b65f446b7491586da60a618e1575565b93d56b0f03eec5d769d2f56aca");
    static final ItemStack teteConcepteur = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJiZDVlYTA0OTliYjM0Y2JhMzE1OTc3ZGMwMjFjNmI0NGM0MGE1OWZhYmI4ODI1YWIxOGI0NjAyYWExOWU4YSJ9fX0=",
            "c2bd5ea0499bb34cba315977dc021c6b44c40a59fabb8825ab18b4602aa19e8a");
    static final ItemStack teteCreateur = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjRmZGJkNGJjMDYwM2EzYTVkNjhjYzRkZWI5ZmFiZjY2YzVjZTdkMTk1OTc0MjFkOTI5YjhhZGI3NDUzNzEyMCJ9fX0=",
            "24fdbd4bc0603a3a5d68cc4deb9fabf66c5ce7d19597421d929b8adb74537120");
    static final ItemStack teteIngenieur = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZkOTZkYzlmZGY4YmViMzBkNDA1NDUyNDJiNGJmNWE0NWI2NWY4MzU0MTBmZTU3Njg5OGMyYjJmMDQyMjQ0NSJ9fX0=",
            "56d96dc9fdf8beb30d40545242b4bf5a45b65f835410fe576898c2b2f0422445");
    static final ItemStack teteBatisseur = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdlNzgyYjQwOGY1NDU2Y2ZhZDBjNDNlOGM1MDFlZjllZmQwMTI4NjI5NzM2MGJlM2I4M2ZiMTZkYzljZDJhNSJ9fX0=",
            "97e782b408f5456cfad0c43e8c501ef9efd01286297360be3b83fb16dc9cd2a5");
    static final ItemStack teteVirtuose = InventoryMenu.getCustomHead("",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQ3OTgwYzZkODZlYzA2ZDcyNDZhMmUxMzMzODE5MjQzNDAyNDk2YjRlYmRhZDJkNTRkMzUzNzAzNDJjNWFlYSJ9fX0=",
            "b47980c6d86ec06d7246a2e1333819243402496b4ebdad2d54d35370342c5aea");

    public RankMenu(InventoryMenu previous) {
        super(27, Component.text("Grades", NamedTextColor.DARK_GREEN, TextDecoration.BOLD), previous);
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(0, teteApprenti,
                Component.text("Apprenti", NamedTextColor.GREEN, TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC),
                event -> {});

        addButton(11, teteConcepteur,
                Component.text("Concepteur", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION.", NamedTextColor.GRAY, TextDecoration.ITALIC),
                event -> {});

        addButton(4, teteCreateur,
                Component.text("Créateur", NamedTextColor.YELLOW, TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC),
                event -> {});

        addButton(15, teteIngenieur,
                Component.text("Ingénieur", NamedTextColor.BLUE, TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC),
                event -> {});

        addButton(8, teteBatisseur,
                Component.text("Bâtisseur", NamedTextColor.AQUA, TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC),
                event -> {});

        addButton(22, teteVirtuose,
                Component.text("Virtuose", NamedTextColor.RED, TextDecoration.BOLD),
                Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC),
                event -> {});

        addBackButton();
        addQuitButton();

        super.open(viewer);
    }
}
