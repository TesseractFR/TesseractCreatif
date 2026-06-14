package onl.tesseract.creative.controller.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor.*
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.util.playerRankService
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.lib.util.append
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

private const val BUY_WITH_LYS_MESSAGE = "Acheter en lys d'or"
private const val PRICE_MESSAGE = "Prix : "
private const val LEFT_CLICK_MESSAGE = "--- Clic gauche ---"

class PrestigeMenu(
    player: Player, previous: Menu,
) :
        BoutiqueCoreMenu(
            MenuSize.Three,
            Component.text("Acheter le grade Prestige", BLUE, TextDecoration.BOLD),
            previous, player
        ) {

    private val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())

        addButton(0, createPrestigeStatusItem(viewer)) {}

        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", WHITE)
                    .build()) {}
        }

        val item1 = ItemBuilder(Material.NETHER_STAR)
            .name("1 mois Prestige", GOLD, TextDecoration.BOLD)
            .lore(lore(250, 250))
            .build()
        addButton(
            11, item1
        ) {
            confirmBuyLysDor(player, 250, "Confirmer l'achat d'un mois de Prestige pour 250 lys d'or")
            {
                playerRankService().addPrestige(player.uniqueId, 30)
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter un mois de Prestige", GOLD))
                this.close()
            }
        }
        val item2 = ItemBuilder(Material.NETHER_STAR)
            .name("3 mois Prestige", GOLD, TextDecoration.BOLD)
            .lore(lore(675, 225))
            .build()
        addButton(
            13, item2
        ) {
            confirmBuyLysDor(player, 225 * 3, "Confirmer l'achat de trois mois de Prestige pour ${225 * 3} lys d'or")
            {
                playerRankService().addPrestige(player.uniqueId, 30 * 3)
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter trois mois de Prestige", GOLD))
                this.close()
            }
        }
        val item3 = ItemBuilder(Material.NETHER_STAR)
            .name("6 mois Prestige", GOLD, TextDecoration.BOLD)
            .lore(lore(1200, 200, "⭐ Le plus rentable !"))
            .build()
        addButton(
            15, item3
        ) {
            confirmBuyLysDor(player, 200 * 6, "Confirmer l'achat de six mois de Prestige pour ${200 * 6} lys d'or")
            {
                playerRankService().addPrestige(player.uniqueId, 30 * 6)
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter six mois de Prestige", GOLD))
                this.close()
            }
        }

        addBackButton()
        addCloseButton()
    }

    private fun lore(totalPrice: Int, monthlyPrice: Int, bonus: String? = null): List<Component> {
        return ItemBuilder(Material.STONE).lore()
            .newline()
            .append(PRICE_MESSAGE, GRAY)
            .newline()
            .append("$totalPrice ", YELLOW, setOf(TextDecoration.BOLD, TextDecoration.ITALIC))
            .append("lys d'or", YELLOW, TextDecoration.ITALIC)
            .newline()
            .append("($monthlyPrice lys d'or/mois)", DARK_GRAY, TextDecoration.ITALIC)
            .also { if (bonus != null) it.newline().append(bonus, GREEN, TextDecoration.BOLD) }
            .newline()
            .newline()
            .append("Plugins inclus :", GRAY)
            .newline()
            .append("- ", GRAY)
            .append("Metabrush", RED, TextDecoration.BOLD)
            .newline()
            .append("- ", GRAY)
            .append("EzEdits", GOLD, TextDecoration.BOLD)
            .newline()
            .newline()
            .append(LEFT_CLICK_MESSAGE, LIGHT_PURPLE)
            .newline()
            .append(BUY_WITH_LYS_MESSAGE, AQUA)
            .get()
    }

    private fun createPrestigeStatusItem(viewer: Player): ItemStack {
        val isPrestige = playerRankService().isPrestige(viewer.uniqueId)
        val ilb = ItemLoreBuilder()
            .newline()
        if (isPrestige) {
            ilb.append("Vous possédez le grade ", GRAY)
                .append("PRESTIGE", GOLD, TextDecoration.BOLD)
                .append(".", GRAY)
            val expiration = playerRankService().getPrestige(viewer.uniqueId)
            val timeLeft = Duration.between(LocalDateTime.now(), expiration)
            addExpirationDate(timeLeft, ilb)
        } else {
            ilb.append("Vous ne possédez pas le grade ", GRAY)
                .append("Prestige", GOLD, TextDecoration.BOLD)
                .append(".", GRAY)
        }
        return ItemBuilder(playerProfileService.getPlayerHead(viewer.uniqueId))
            .name(viewer.name, AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun addExpirationDate(timeLeft: Duration, ilb: ItemLoreBuilder) {
        if (timeLeft.isPositive) {
            val expirationDate = Instant.now()
                    .plus(timeLeft)
                    .atZone(ZoneId.systemDefault())
            ilb.newline()
                    .append("Possédé jusqu'au ", GRAY)
                    .append(expirationDate.format(dateFormatter), GREEN, TextDecoration.BOLD)
        }
    }

}
