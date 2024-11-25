package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor.*
import onl.tesseract.core.boutique.BoutiqueService
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuService
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.append
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*
import java.util.function.Consumer

abstract class BoutiqueCoreMenu(size: MenuSize, name: Component, previous: Menu?) : Menu(size, name, previous) {

    fun addBoutiqueButton(player: Player, slot: Int) {
        val uuid = player.uniqueId
        val playerBoutiqueInfo = ServiceContainer[BoutiqueService::class.java].getPlayerBoutiqueInfo(uuid)
        addButton(
            slot, ItemBuilder(Material.RAW_GOLD)
                    .lore()
                    .newline()
                    .append("Vous avez ", GRAY)
                    .append(playerBoutiqueInfo.shopPoints.toString(), DARK_AQUA)
                    .append(" Points boutique.", GRAY)
                    .newline()
                    .append("Vous avez ", GRAY)
                    .append(getMarketCurrency(uuid).toString(), DARK_AQUA)
                    .append(" lys d'or.", GRAY)
                    .newline()
                    .buildLore()
                    .build()
        ) {
            player.sendMessage(
                Component.text("[", GOLD)
                    .append("Cliquez ici pour acheter des lys d'or", YELLOW)
                    .append("]", GOLD)
                    .clickEvent(
                        ClickEvent.openUrl("https://tesseract.craftingstore.net/")
                    )
            )
        }
    }

    fun confirmBuyLysDor(player: Player, price: Int, message: String, consumer: Consumer<Int>) {
        confirmBuyLysDor(player, price, Component.text(message), consumer)
    }

    fun confirmBuyLysDor(player: Player, price: Int, message: Component, consumer: Consumer<Int>) {
        if (getMarketCurrency(player.uniqueId) < price) {
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous n'avez pas suffisamment de lys d'or. Nécessite : $price"))
            return
        }
        ServiceContainer[MenuService::class.java].openConfirmationMenu(
            player, message, this
        ) {
            withdrawMarketCurrency(player.uniqueId, price)
            consumer.accept(price)
        }
    }

    protected fun getMarketCurrency(uuid: UUID): Int {
        return ServiceContainer[BoutiqueService::class.java].getPlayerBoutiqueInfo(uuid).marketCurrency
    }

    protected fun withdrawMarketCurrency(uuid: UUID, amount: Int) {
        require(amount > 0) {
            "Amount must be greater than zero!"
        }
        require(getMarketCurrency(uuid) > amount) {
            "Player don't have enough MarketCurrency!"
        }
        ServiceContainer[BoutiqueService::class.java].addMarketCurrency(uuid, -amount);
    }
}