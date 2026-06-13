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
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

private val tetePlayerParticles = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ2MWQ5ZDA2YzBiZjRhN2FmNGIxNmZkMTI4MzFlMmJlMGNmNDJlNmU1NWU5YzBkMzExYTJhODk2NWEyM2IzNCJ9fX0=",
    "4461d9d06c0bf4a7af4b16fd12831e2be0cf42e6e55e9c0d311a2a8965a23b34"
)

private const val BUY_WITH_LYS_MESSAGE = "Acheter en lys d'or"
private const val PRICE_MESSAGE = "Prix : "
private const val LEFT_CLICK_MESSAGE = "--- Clic gauche ---"

class PrestigeMenu(
    player: Player, previous: Menu,
) :
        BoutiqueCoreMenu(
            MenuSize.Three,
            Component.text("Acheter le grade Prestige", DARK_GREEN, TextDecoration.BOLD),
            previous, player
        ) {

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())

        val item1 = tetePlayerParticles
                .name("1 mois Prestige", GOLD)
                .lore(lore("250 Lys d'or / mois"))
                .build()
        addButton(
            4, item1
        ) {
            confirmBuyLysDor(player, 250, "Confirmer l'achat d'un mois de Prestige pour 250 lys d'or")
            {
                playerRankService().addPrestige(player.uniqueId, 30)
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter un mois de Prestige", GOLD))
                this.close()
            }
        }
        val item2 = tetePlayerParticles
                .name("3 mois Prestige", GOLD)
                .lore(lore("225 Lys d'or / mois"))
                .build()
        addButton(
            6, item2
        ) {
            confirmBuyLysDor(player, 225 * 3, "Confirmer l'achat de trois mois de Prestige pour ${225 * 3} lys d'or")
            {
                playerRankService().addPrestige(player.uniqueId, 30 * 3)
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter trois mois de Prestige", GOLD))
                this.close()
            }
        }
        val item3 = tetePlayerParticles
                .name("6 mois Prestige", GOLD)
                .lore(lore("200 Lys d'or / mois"))
                .build()
        addButton(
            8, item3
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

    private fun lore(price: String): List<Component> {
        val loreBuilder = ItemBuilder(Material.STONE).lore()
                .newline()
                .append(PRICE_MESSAGE, GRAY)
                .append(price, GOLD, TextDecoration.ITALIC)
                .newline()
                .newline()
                .append(LEFT_CLICK_MESSAGE, LIGHT_PURPLE)
                .newline()
                .append(BUY_WITH_LYS_MESSAGE, AQUA)
        return loreBuilder.get()
    }

    private fun addExpirationDate(timeLeft: Duration, ilb: ItemLoreBuilder) {
        if (timeLeft.isPositive) {
            val expirationDate = Instant.now()
                    .plus(timeLeft)
                    .atZone(ZoneId.systemDefault())
            ilb.newline()
                    .append("Possédé jusqu'au ", LIGHT_PURPLE)
                    .append(
                        expirationDate.dayOfMonth.toString() + "/" + expirationDate.monthValue + "/" + expirationDate.year,
                        AQUA)
        }
    }

}
