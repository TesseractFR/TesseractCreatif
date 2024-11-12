package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.util.ItemBuilder
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player

class PluginsToolsMenu(previous: InventoryMenu? = null) :

    InventoryMenu(27, Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD), previous) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(0, schematicImportItem) {}
        addButton(10, faweItem) {}
        addButton(2, builderUtilitiesItem) {}
        addButton(12, armorStandToolsItem) {}
        addButton(4, hdbItem) {}
        addButton(14, voxelSniperItem) {}
        addButton(6, arceonItem) {}
        addButton(16, goBrushItem) {}
        addButton(8, goPaintItem) {}

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    companion object {
        val schematicImportItem = ItemBuilder(Material.BOOK)
            .name(Component.text("Schematic Import", NamedTextColor.RED, TextDecoration.BOLD))
            .lore(Component.text("Ce plugin vous permet de copier des constructions d'un monde à un autre facilement.", NamedTextColor.GRAY)
                .append(" Cliquez pour vous rediriger vers le site permettant l'import de schématics.", NamedTextColor.GRAY))
            .build()

        val faweItem = ItemBuilder(Material.WOODEN_AXE)
            .name(Component.text("FastAsyncWorldEdit (FAWE)", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD))
            .lore(Component.text("Avec FAWE, vous pouvez copier et coller de grandes zones de blocs en un clin d'œil, et bien plus encore !", NamedTextColor.GRAY)
                .append("Découvrez les commandes de base dans ", NamedTextColor.GRAY)
                .append("Le Build pour les Nuls", NamedTextColor.GOLD, TextDecoration.ITALIC)
                .append(", faites ", NamedTextColor.GRAY)
                .append("/commandes ", NamedTextColor.RED)
                .append("pour le recevoir.", NamedTextColor.GRAY))
            .build()

        val builderUtilitiesItem = ItemBuilder(Material.DIAMOND_BOOTS)
            .name(Component.text("Builder Utilities", NamedTextColor.AQUA, TextDecoration.BOLD))
            .lore(Component.text("Ce plugin vous donne des outils géniaux comme le speed et le no-clip pour construire sans effort.", NamedTextColor.GRAY)
                .append(" Vous pouvez aussi changer les couleurs des blocs en un instant.", NamedTextColor.GRAY))
            .build()

        val armorStandToolsItem = ItemBuilder(Material.ARMOR_STAND)
            .name(Component.text("ArmorStand Tools (AST)", NamedTextColor.DARK_GREEN, TextDecoration.BOLD))
            .lore(Component.text("AST est là pour vous aider à personnaliser vos armorstands.", NamedTextColor.GRAY)
                .append(" Amusez-vous à les déplacer et les manipuler comme vous le souhaitez ! Pour ouvrir le menu, faites ", NamedTextColor.GRAY)
                .append("/ast", NamedTextColor.RED)
                .append(".", NamedTextColor.GRAY))
            .build()

        val voxelSniperItem = ItemBuilder(Material.ARROW)
            .name(Component.text("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD))
            .lore(Component.text("Libérez votre créativité avec les outils et pinceaux puissants de VoxelSniper. Façonnez des montagnes, sculptez des grottes et peignez des mondes avec une grande finesse.", NamedTextColor.GRAY)
                .append(" Transformez des blocs en chefs-d'œuvre impressionnants qui émerveilleront les autres !", NamedTextColor.GRAY))
            .build()

        val arceonItem = ItemBuilder(Material.PAPER)
            .name(Component.text("Arceon", NamedTextColor.GREEN, TextDecoration.BOLD))
            .lore(Component.text("Arceon ajoute des options intéressantes à WorldEdit, ", NamedTextColor.GRAY)
                .append("en incluant des fonctionnalités plus complexes pour créer des structures précises en quelques commandes assez simples.", NamedTextColor.GRAY))
            .build()

        val goBrushItem = ItemBuilder(Material.FLINT)
            .name(Component.text("GoBrush", NamedTextColor.YELLOW, TextDecoration.BOLD))
            .lore(Component.text("GoBrush vous permet de sculpter le terrain comme un artiste ! ", NamedTextColor.GRAY)
                .append("Cliquez avec un silex en main ", NamedTextColor.RED)
                .append("pour ouvrir le menu et créez des collines, des vallées et d'autres formes impressionnantes avec facilité.", NamedTextColor.GRAY))
            .build()

        val goPaintItem = ItemBuilder(Material.FEATHER)
            .name(Component.text("GoPaint", NamedTextColor.DARK_RED, TextDecoration.BOLD))
            .lore(Component.text("GoPaint est l'outil parfait pour modifier la texture du terrain. ", NamedTextColor.GRAY)
                .append("Cliquez avec une plume en main ", NamedTextColor.RED)
                .append("pour ouvrir le menu, afin de changer la couleur de votre sol et donner vie à vos créations.", NamedTextColor.GRAY))
            .build()

        val hdbItem = ItemBuilder(Material.PLAYER_HEAD)
            .name(Component.text("Head DataBase (HDB)", NamedTextColor.GOLD, TextDecoration.BOLD))
            .lore(Component.text("HDB vous permet d'ajouter des têtes décoratives à vos constructions, les rendant plus colorés et uniques. Faites la commande ", NamedTextColor.GRAY)
                .append("/hdb ", NamedTextColor.RED)
                .append("pour ouvrir le menu. ", NamedTextColor.GRAY))
            .build()
    }
}
