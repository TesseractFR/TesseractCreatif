package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.GOLD
import net.kyori.adventure.text.format.NamedTextColor.GRAY
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.append
import onl.tesseract.player.PermissionService
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.entity.PlayerRank
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag

class CreativeBoutiqueMenu(player: Player, previous: Menu) :
        BoutiqueCoreMenu(
            MenuSize.Three,
            Component.text("Boutique du Créatif", NamedTextColor.RED, TextDecoration.BOLD),
            previous,
            player) {

    override fun placeButtons(viewer: Player) {
        super.placeButtons(viewer)


        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", NamedTextColor.WHITE)
                        .build()) {}
        }

        addButton(
            10, teteAchatPlots
                    .name("Achat de plots", GOLD, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("Acquérez des plots supplémentaires !", GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .build()
        ) {
            PlotMenu(player, this).open(viewer);
        }
        addVirtuose()


        addButton(
            5, teteRankUp
                    .name("Monter en grade", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("Accélérez votre progression !", GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .build()
        ) {
            RankMenu(player, this).open(viewer)
        }

        addButton(
            16, ItemBuilder(Material.WOODEN_AXE)
                    .name("Location de plugins et outils", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("Louer l'accès à des plugins sur-puissants !", GRAY, TextDecoration.ITALIC)
                    .buildLore()
                    .flags(ItemFlag.HIDE_ATTRIBUTES)
                    .build()
        ) {
            RentPluginsMenu(player, this).open(viewer)
        }

        addBackButton()
        addCloseButton()
    }

    private fun addVirtuose() {
        val rankService = ServiceContainer[PlayerRankService::class.java]
        if (rankService.getPlayerRank(player.uniqueId) == PlayerRank.VIRTUOSE) {
            addButton(
                3, teteVirtuose
                        .name("Grade VIRTUOSE", NamedTextColor.AQUA, TextDecoration.BOLD)
                        .lore()
                        .newline()
                        .newline()
                        .append("Déjà possédé", GRAY)
                        .buildLore()
                        .build()) {}
        } else {
            addButton(
                3, virtuoseItem
            ) {
                confirmBuyLysDor(player, 2400, "Confirmer l'achat du grade Virtuose pour 2400 lys d'or")
                {
                    ServiceContainer[PlayerRankService::class.java].setPlayerRank(player.uniqueId, PlayerRank.VIRTUOSE)
                    player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter le grade Virtuose", GOLD))
                    ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
                    this.close()
                }
            }
        }

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
