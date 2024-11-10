package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CreativeBoutiqueMenu(val player: TPlayer, previous: InventoryMenu) :

    InventoryMenu(27, Component.text("Boutique du Créatif", NamedTextColor.RED, TextDecoration.BOLD), previous) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(slot, Material.BLUE_STAINED_GLASS_PANE, Component.text(" ", NamedTextColor.WHITE), Component.text(" ", NamedTextColor.WHITE)) {}
        }

        addButton(
            10, teteAchatPlots,
            Component.text("Achat de plots", NamedTextColor.GOLD, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            3, teteVirtuose,
            Component.text("Grade VIRTUOSE", NamedTextColor.AQUA, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            5, teteRankUp,
            Component.text("Monter en grade", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            16, Material.WOODEN_AXE,
            Component.text("Location de plugins et outils", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) {
            RentPluginsMenu(player, this).open(viewer)
        }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    companion object {
        val teteAchatPlots: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdmODJhY2ViOThmZTA2OWU4YzE2NmNlZDAwMjQyYTc2NjYwYmJlMDcwOTFjOTJjZGRlNTRjNmVkMTBkY2ZmOSJ9fX0=",
            "97f82aceb98fe069e8c166ced00242a76660bbe07091c92cdde54c6ed10dcff9"
        )
        val teteRankUp: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVhODRlNjM5NGJhZjhiZDc5NWZlNzQ3ZWZjNTgyY2RlOTQxNGZjY2YyZjFjODYwOGYxYmUxOGMwZTA3OTEzOCJ9fX0=",
            "65a84e6394baf8bd795fe747efc582cde9414fccf2f1c8608f1be18c0e079138"
        )
        val teteVirtuose: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdiYzI1MWE2Y2IwZDZkOWYwNWM1NzExOTExYTZlYzI0YjIwOWRiZTY0MjY3OTAxYTRiMDM3NjFkZWJjZjczOCJ9fX0=",
            "e7bc251a6cb0d6d9f05c5711911a6ec24b209dbe64267901a4b03761debcf738"
        )
    }
}
