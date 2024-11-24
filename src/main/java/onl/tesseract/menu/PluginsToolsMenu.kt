package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.util.ItemBuilder
import onl.tesseract.lib.util.ItemLoreBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class PluginsToolsMenu(previous: Menu? = null) :
private val tetePlayerParticles: ItemStack = getCustomHead(
    "",
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ2MWQ5ZDA2YzBiZjRhN2FmNGIxNmZkMTI4MzFlMmJlMGNmNDJlNmU1NWU5YzBkMzExYTJhODk2NWEyM2IzNCJ9fX0=",
    "4461d9d06c0bf4a7af4b16fd12831e2be0cf42e6e55e9c0d311a2a8965a23b34"
)

class PluginsToolsMenu(previous: InventoryMenu? = null) :

        Menu(
            MenuSize.Three,
            Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
            previous) {

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())

        addButton(0, createSchematicImportItem()) {}
        addButton(2, createBuilderUtilitiesItem()) {}
        addButton(4, createHdbItem()) {}
        addButton(6, createArceonItem()) {
            RentPluginsMenu(TPlayer.get(viewer), this).open(viewer)
        }
        addButton(8, createGoPaintItem()) {}
        addButton(10, createFaweItem()) {}
        addButton(12, createArmorStandToolsItem()) {}
        addButton(13, createMetaBrushItem()) {
            RentPluginsMenu(TPlayer.get(viewer), this).open(viewer)
        }
        addButton(14, createVoxelSniperItem()) {}
        addButton(16, createGoBrushItem()) {}
        addButton(20, createAxiomItem()) {}
        addButton(22, createPlayerParticlesItem()) {
            RentPluginsMenu(TPlayer.get(viewer), this).open(viewer)
        }
        addButton(24, createDisplayEntitiesItem()) {
            RentPluginsMenu(TPlayer.get(viewer), this).open(viewer)
        }

        addBackButton()
        addCloseButton()
    }

    private fun createSchematicImportItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Ce plugin vous permet de copier des constructions d'un monde à un autre facilement.", NamedTextColor.GRAY)
            .append(" Cliquez pour vous rediriger vers le site permettant l'import de schématics.", NamedTextColor.GRAY)
        return ItemBuilder(Material.BOOK)
            .name("Schematic Import", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createFaweItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Avec FAWE, vous pouvez copier et coller de grandes zones de blocs en un clin d'œil, et bien plus encore !", NamedTextColor.GRAY)
            .append(" Découvrez les commandes de base dans ", NamedTextColor.GRAY)
            .append("Le Build pour les Nuls", NamedTextColor.GOLD, TextDecoration.ITALIC)
            .append(", faites ", NamedTextColor.GRAY)
            .append("/commandes ", NamedTextColor.RED)
            .append("pour le recevoir.", NamedTextColor.GRAY)
        return ItemBuilder(Material.WOODEN_AXE)
            .name("FastAsyncWorldEdit (FAWE)", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createBuilderUtilitiesItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Ce plugin vous donne des outils géniaux comme le speed et le no-clip pour construire sans effort.", NamedTextColor.GRAY)
            .append(" Vous pouvez aussi changer les couleurs des blocs en un instant.", NamedTextColor.GRAY)
        return ItemBuilder(Material.DIAMOND_BOOTS)
            .name("Builder Utilities", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createArmorStandToolsItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AST est là pour vous aider à personnaliser vos armorstands.", NamedTextColor.GRAY)
            .append(" Amusez-vous à les déplacer et les manipuler comme vous le souhaitez ! Pour ouvrir le menu, faites ", NamedTextColor.GRAY)
            .append("/ast", NamedTextColor.RED)
            .append(".", NamedTextColor.GRAY)
        return ItemBuilder(Material.ARMOR_STAND)
            .name("ArmorStand Tools (AST)", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createVoxelSniperItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade disponible à partir du rang ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .append("BÂTISSEUR ", NamedTextColor.BLUE, TextDecoration.BOLD)
            .append("!", NamedTextColor.LIGHT_PURPLE)
            .newline().newline()
            .append("Libérez votre créativité avec les outils et pinceaux puissants de VoxelSniper. Façonnez des montagnes, sculptez des grottes et peignez des mondes avec une grande finesse.", NamedTextColor.GRAY)
            .append(" Transformez des blocs en chefs-d'œuvre impressionnants qui émerveilleront les autres !", NamedTextColor.GRAY)
        return ItemBuilder(Material.ARROW)
            .name("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createArceonItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade PAYANT !", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("Arceon ajoute des options intéressantes à WorldEdit, ", NamedTextColor.GRAY)
            .append("en incluant des fonctionnalités plus complexes pour créer des structures précises en quelques commandes assez simples.", NamedTextColor.GRAY)
        return ItemBuilder(Material.PAPER)
            .name("Arceon", NamedTextColor.GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createGoBrushItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("GoBrush vous permet de sculpter le terrain comme un artiste ! ", NamedTextColor.GRAY)
            .append("Cliquez avec un silex en main ", NamedTextColor.RED)
            .append("pour ouvrir le menu et créez des collines, des vallées et d'autres formes impressionnantes avec facilité.", NamedTextColor.GRAY)
        return ItemBuilder(Material.FLINT)
            .name("GoBrush", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createGoPaintItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("GoPaint est l'outil parfait pour modifier la texture du terrain. ", NamedTextColor.GRAY)
            .append("Cliquez avec une plume en main ", NamedTextColor.RED)
            .append("pour ouvrir le menu, afin de changer la couleur de votre sol et donner vie à vos créations.", NamedTextColor.GRAY)
        return ItemBuilder(Material.FEATHER)
            .name("GoPaint", NamedTextColor.DARK_RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createHdbItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("HDB vous permet d'ajouter des têtes décoratives à vos constructions, les rendant plus colorés et uniques. Faites la commande ", NamedTextColor.GRAY)
            .append("/hdb ", NamedTextColor.RED)
            .append("pour ouvrir le menu. ", NamedTextColor.GRAY)
        return ItemBuilder(Material.PLAYER_HEAD)
            .name("Head DataBase (HDB)", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createMetaBrushItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade PAYANT !", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("Metabrush permet d'ajouter des commandes puissantes pour améliorer vos capacités de construction.", NamedTextColor.GRAY)
            .append(" Ce plugin est parfait pour les constructions techniques avancées.", NamedTextColor.GRAY)
        return ItemBuilder(Material.BRUSH)
            .name("Metabrush", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createAxiomItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Axiom est un plugin d'interface facile à utiliser, conçu pour faciliter le processus de construction.", NamedTextColor.GRAY)
            .append(" Il permet de réaliser des constructions rapidement et de manière intuitive.", NamedTextColor.GRAY)
        return ItemBuilder(Material.ENDER_PEARL)
            .name("Axiom", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createPlayerParticlesItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade PAYANT !", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("PlayerParticles permet d'ajouter des particules personnalisées à vos constructions.", NamedTextColor.GRAY)
        return ItemBuilder(tetePlayerParticles)
            .name("PlayerParticles", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createDisplayEntitiesItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade PAYANT !", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("Display Entities permet de créer des détails impressionnants avec moins de lag que les ASTs.", NamedTextColor.GRAY)
        return ItemBuilder(Material.ARMOR_STAND)
            .name("Display Entities", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

}
