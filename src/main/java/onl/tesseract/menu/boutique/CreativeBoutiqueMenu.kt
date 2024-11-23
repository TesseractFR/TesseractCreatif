package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import org.bukkit.Material
import org.bukkit.entity.Player

class CreativeBoutiqueMenu(val player: Player, previous: Menu) :
        Menu(MenuSize.Three, Component.text("Boutique du Créatif", NamedTextColor.RED, TextDecoration.BOLD), previous) {

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())

        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", NamedTextColor.WHITE)
                        .build()) {}
        }

        addButton(
            10, teteAchatPlots
                    .name("Achat de plots", NamedTextColor.GOLD, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .build()
        ) { }

        addButton(
            3, teteVirtuose
                    .name("Grade VIRTUOSE", NamedTextColor.AQUA, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .build()
        ) { }

        addButton(
            5, teteRankUp
                    .name("Monter en grade", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .build()
        ) { }

        addButton(
            16, ItemBuilder(Material.WOODEN_AXE)
                    .name("Location de plugins et outils", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("Louer l'accès à des plugins sur-puissant", NamedTextColor.GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .build()
        ) {
            RentPluginsMenu(player, this).open(viewer)
        }

        addBackButton()
        addCloseButton()
    }

    companion object {
        val teteAchatPlots = ItemBuilder(Material.PLAYER_HEAD).customHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdmODJhY2ViOThmZTA2OWU4YzE2NmNlZDAwMjQyYTc2NjYwYmJlMDcwOTFjOTJjZGRlNTRjNmVkMTBkY2ZmOSJ9fX0=",
            "97f82aceb98fe069e8c166ced00242a76660bbe07091c92cdde54c6ed10dcff9"
        )
        val teteRankUp = ItemBuilder(Material.PLAYER_HEAD).customHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVhODRlNjM5NGJhZjhiZDc5NWZlNzQ3ZWZjNTgyY2RlOTQxNGZjY2YyZjFjODYwOGYxYmUxOGMwZTA3OTEzOCJ9fX0=",
            "65a84e6394baf8bd795fe747efc582cde9414fccf2f1c8608f1be18c0e079138"
        )
        val teteVirtuose = ItemBuilder(Material.PLAYER_HEAD).customHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdiYzI1MWE2Y2IwZDZkOWYwNWM1NzExOTExYTZlYzI0YjIwOWRiZTY0MjY3OTAxYTRiMDM3NjFkZWJjZjczOCJ9fX0=",
            "e7bc251a6cb0d6d9f05c5711911a6ec24b209dbe64267901a4b03761debcf738"
        )
    }
}
