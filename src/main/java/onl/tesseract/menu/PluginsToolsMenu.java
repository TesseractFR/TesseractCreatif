package onl.tesseract.menu;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import onl.tesseract.tesseractlib.util.menu.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class PluginsToolsMenu extends InventoryMenu {

    public PluginsToolsMenu(InventoryMenu previous) {
        super(27, Component.text("Outils/Plugins du serveur").color(NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, true), previous);
    }

    @Override
    public void open(Player viewer){
        fill(Material.GRAY_STAINED_GLASS_PANE, " ");

        addButton(0, Material.BOOK, Component.text("Schematic Import").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true),
                Component.text("Ce plugin vous permet de copier des constructions d'un monde à un autre facilement.").color(NamedTextColor.GRAY)
                        .append(Component.text(" Cliquez pour vous rediriger vers le site permettant l'import de schématics.").color(NamedTextColor.GRAY)),
                event -> { /* Mettre le lien du site (cassé pour le moment) */ });

        addButton(10, Material.WOODEN_AXE, Component.text("FastAsyncWorldEdit (FAWE)").color(NamedTextColor.LIGHT_PURPLE).decoration(TextDecoration.BOLD, true),
                Component.text("Avec FAWE, vous pouvez copier et coller de grandes zones de blocs en un clin d'œil, et bien plus encore !").color(NamedTextColor.GRAY)
                        .append(Component.text("Découvrez les commandes de base dans ").color(NamedTextColor.GRAY))
                        .append(Component.text("Le Build pour les Nuls").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, true))
                        .append(Component.text(", faites ").color(NamedTextColor.GRAY))
                        .append(Component.text("/commandes ").color(NamedTextColor.RED))
                        .append(Component.text("pour le recevoir.").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(2, Material.DIAMOND_BOOTS, Component.text("Builder Utilities").color(NamedTextColor.AQUA).decoration(TextDecoration.BOLD, true),
                Component.text("Ce plugin vous donne des outils géniaux comme le speed et le no-clip pour construire sans effort.").color(NamedTextColor.GRAY)
                        .append(Component.text(" Vous pouvez aussi changer les couleurs des blocs en un instant.").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(12, Material.ARMOR_STAND, Component.text("ArmorStand Tools (AST)").color(NamedTextColor.DARK_GREEN).decoration(TextDecoration.BOLD, true),
                Component.text("AST est là pour vous aider à personnaliser vos armorstands.").color(NamedTextColor.GRAY)
                        .append(Component.text(" Amusez-vous à les déplacer et les manipuler comme vous le souhaitez !. Pour ouvrir le menu, faites ").color(NamedTextColor.GRAY))
                        .append(Component.text("/ast").color(NamedTextColor.RED))
                        .append(Component.text(".").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(4, Material.PLAYER_HEAD, Component.text("Head DataBase (HDB)").color(NamedTextColor.GOLD).decoration(TextDecoration.BOLD, true),
                Component.text("HDB vous permet d'ajouter des têtes décoratives à vos constructions, les rendant plus colorés et uniques. Faites la commande ").color(NamedTextColor.GRAY)
                        .append(Component.text("/hdb ").color(NamedTextColor.RED))
                        .append(Component.text("pour ouvrir le menu. ").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(14, Material.ARROW, Component.text("VoxelSniper").color(NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, true),
                Component.text("Libérez votre créativité avec les outils et pinceaux puissants de VoxelSniper. Façonnez des montagnes, sculptez des grottes et peignez des mondes avec une grande finesse. ").color(NamedTextColor.GRAY)
                        .append(Component.text("Transformez des blocs en chefs-d'œuvre impressionnants qui émerveilleront les autres !").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(6, Material.PAPER, Component.text("Arceon").color(NamedTextColor.GREEN).decoration(TextDecoration.BOLD, true),
                Component.text("Arceon ajoute des options intéressantes à WorldEdit, ").color(NamedTextColor.GRAY)
                        .append(Component.text("en incluant des fonctionnalités plus complexes pour créer des structures précises en quelques commandes assez simples.").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(16, Material.FLINT, Component.text("GoBrush").color(NamedTextColor.YELLOW).decoration(TextDecoration.BOLD, true),
                Component.text("GoBrush vous permet de sculpter le terrain comme un artiste ! ").color(NamedTextColor.GRAY)
                        .append(Component.text("Cliquez avec un silex en main ").color(NamedTextColor.RED))
                        .append(Component.text("pour ouvrir le menu et créez des collines, des vallées et d'autres formes impressionnantes avec facilité.").color(NamedTextColor.GRAY)),
                event -> {});

        addButton(8, Material.FEATHER, Component.text("GoPaint").color(NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true),
                Component.text("GoPaint est l'outil parfait pour modifier la texture du terrain. ").color(NamedTextColor.GRAY)
                        .append(Component.text("Cliquez avec une plume en main ").color(NamedTextColor.RED))
                        .append(Component.text("pour ouvrir le menu, afin de changer la couleur de votre sol et donner vie à vos créations.").color(NamedTextColor.GRAY)),
                event -> {});

        addBackButton();
        addQuitButton();
        super.open(viewer);
    }
}
