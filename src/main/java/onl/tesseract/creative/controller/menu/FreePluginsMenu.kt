package onl.tesseract.creative.controller.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.lib.util.append
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

private val axiomLink: Component = Component.text("---------------\n", NamedTextColor.AQUA)
    .append("Lien pour Axiom", NamedTextColor.AQUA, TextDecoration.BOLD)
    .append("\n---------------", NamedTextColor.AQUA)
    .clickEvent(ClickEvent.openUrl("https://modrinth.com/mod/axiom"))

private val voiceChatLink: Component = Component.text("---------------\n", NamedTextColor.LIGHT_PURPLE)
    .append("Lien Chat Vocal", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
    .append("\n---------------", NamedTextColor.LIGHT_PURPLE)
    .clickEvent(ClickEvent.openUrl("https://modrinth.com/plugin/simple-voice-chat"))

private val schematicImportLink: Component = Component.text("---------------------\n", NamedTextColor.RED)
    .append("FAWE Schematic Import", NamedTextColor.RED, TextDecoration.BOLD)
    .append("\n---------------------", NamedTextColor.RED)
    .clickEvent(ClickEvent.openUrl("https://schem.intellectualsites.com/fawe/"))

class FreePluginsMenu(previous: Menu? = null) :

    Menu(MenuSize.Three,
        Component.text("Plugins gratuits", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
        previous) {

    override fun placeButtons(viewer: Player) {
        fill(ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ").build())
        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", NamedTextColor.WHITE)
                    .build()) {}
        }
        addButton(0, createSchematicImportItem()) {
            viewer.closeInventory()
            viewer.sendMessage(schematicImportLink)
        }
        addButton(10, createBuilderUtilitiesItem()) {}
        addButton(2, createHdbItem()) {}
        addButton(12, createGoPaintItem()) {}
        addButton(4, createFaweItem()) {}
        addButton(14, createEasyArmorStandsItem()) {}
        addButton(6, createAxiomItem()) {
            viewer.closeInventory()
            viewer.sendMessage(axiomLink)
        }
        addButton(16, createGoBrushItem()) {}
        addButton(8, createSimpleVoiceChatItem()) {
            viewer.closeInventory()
            viewer.sendMessage(voiceChatLink)
        }

        addButton(22, createVoxelSniperItem()) {}

        addBackButton()
        addCloseButton()
    }

    private fun createSchematicImportItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Ce plugin vous permet de copier des constructions d'un monde à un autre facilement.", NamedTextColor.GRAY)
            .append(" Cliquez", NamedTextColor.RED)
            .append(" pour vous rediriger vers le site permettant l'import de schématics.", NamedTextColor.GRAY)

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

    private fun createEasyArmorStandsItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Placez, positionnez et habillez des porte-armures avec une interface facile à utiliser. Faites", NamedTextColor.GRAY)
            .append(" /eas give", NamedTextColor.RED)
            .append(" pour recevoir le bâton, puis", NamedTextColor.GRAY)
            .append(" clic gauche", NamedTextColor.RED)
            .append(" pour ouvrir l'interface.", NamedTextColor.GRAY)
        return ItemBuilder(Material.ARMOR_STAND)
            .name("Easy Armor Stands (EAS)", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
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

    private fun createAxiomItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Nécessite le mod client Axiom via Fabric (GRATUIT) !", NamedTextColor.GOLD, TextDecoration.BOLD)
            .newline()
            .append("(Cliquez pour obtenir le lien d'installation)", NamedTextColor.YELLOW, TextDecoration.ITALIC)
            .newline().newline()
            .append("Axiom, nouveau plugin révolutionnaire de construction ! En maintenant la touche", NamedTextColor.GRAY)
            .append(" Alt ", NamedTextColor.RED, TextDecoration.BOLD)
            .append("de votre clavier, l'avenir du build est entre vos mains.", NamedTextColor.GRAY)
        return ItemBuilder(Material.ENDER_PEARL)
            .name("Axiom", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createSimpleVoiceChatItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Nécessite le mod client Simple Voice Chat via Fabric/Forge (GRATUIT) !", NamedTextColor.GOLD, TextDecoration.BOLD)
            .newline()
            .append("(Cliquez pour obtenir le lien d'installation)", NamedTextColor.YELLOW, TextDecoration.ITALIC)
            .newline().newline()
            .append("Avec ce chat de proximité intégré, jouez avec les autres joueurs ou créez des groupes entre " +
                    "amis pour plus de fun sur le serveur !", NamedTextColor.GRAY)
            .append(" Pour ouvrir le menu, cliquer sur la touche", NamedTextColor.GRAY)
            .append(" V ", NamedTextColor.RED, TextDecoration.BOLD)
            .append("de votre clavier, rien de plus simple.", NamedTextColor.GRAY)
        return ItemBuilder(Material.GOAT_HORN)
            .name("Simple Voice Chat", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }
}