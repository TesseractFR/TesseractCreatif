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
import onl.tesseract.timeplayed.PlayerTimePlayedService
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*
import java.util.function.Consumer

abstract class BoutiqueCoreMenu(size: MenuSize, name: Component, previous: Menu?, val player: Player) :
        Menu(size, name, previous) {

    val boutiqueService: BoutiqueService = ServiceContainer[BoutiqueService::class.java]

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())
        addBoutiqueButton(player, size.size - 5)
    }

    fun addBoutiqueButton(player: Player, slot: Int) {
        val uuid = player.uniqueId
        val playerBoutiqueInfo = boutiqueService.getPlayerBoutiqueInfo(uuid)
        addButton(
            slot, ItemBuilder(Material.RAW_GOLD)
                    .name("Monnaies", GOLD)
                    .lore()
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
        return boutiqueService.getPlayerBoutiqueInfo(uuid).marketCurrency
    }

    protected fun withdrawMarketCurrency(uuid: UUID, amount: Int) {
        require(amount > 0) {
            "Amount must be greater than zero!"
        }
        require(getMarketCurrency(uuid) > amount) {
            "Player don't have enough MarketCurrency!"
        }
        boutiqueService.addMarketCurrency(uuid, -amount);
    }

    protected fun confirmBuyLysTemporel(player: Player, price: Int, message: Component, consumer: Consumer<Int>) {
        if (getTemporalLys(player.uniqueId) < price) {
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous n'avez pas suffisamment de lys temporel. Nécessite : $price"))
            return
        }
        ServiceContainer[MenuService::class.java].openConfirmationMenu(
            player, message, this
        ) {
            withdrawLysTemporel(player.uniqueId, price)
            consumer.accept(price)
        }
    }

    protected fun confirmBuyShopPoint(player: Player, price: Int, message: Component, consumer: Consumer<Int>) {
        if (getShopPoint(player.uniqueId) < price) {
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous n'avez pas suffisamment de point boutique. Nécessite : $price"))
            return
        }
        ServiceContainer[MenuService::class.java].openConfirmationMenu(
            player, message, this
        ) {
            withdrawShopPoint(player.uniqueId, price)
            consumer.accept(price)
        }
    }

    private fun getTemporalLys(uniqueId: UUID): Int {
        return ServiceContainer[PlayerTimePlayedService::class.java].getTemporalLys(uniqueId);
    }

    private fun getShopPoint(uniqueId: UUID): Int {
        return ServiceContainer[BoutiqueService::class.java].getPlayerBoutiqueInfo(uniqueId).shopPoints;
    }

    private fun withdrawLysTemporel(uniqueId: UUID, price: Int) {
        return ServiceContainer[PlayerTimePlayedService::class.java].addTemporalLys(uniqueId, -price)
    }

    private fun withdrawShopPoint(uniqueId: UUID, price: Int) {
        ServiceContainer[BoutiqueService::class.java].addShopPoint(uniqueId, -price)
    }
}

