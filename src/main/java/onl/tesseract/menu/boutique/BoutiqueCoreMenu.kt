package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor.*
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.function.Consumer

abstract class BoutiqueCoreMenu(size: Int, name:Component,previous: InventoryMenu?) : InventoryMenu(size,name,previous) {

    fun addBoutiqueButton(tPlayer: TPlayer,slot:Int) {
        addButton(
            slot, Material.RAW_GOLD, Component.text("Lys d'or et Points Boutique", GOLD),
            Component.newline()
                .append("Vous avez ",GRAY)
                .append(tPlayer.shopPoint, DARK_AQUA)
                .append(" Points boutique.", GRAY)
                .appendNewline()
                .append("Vous avez ",GRAY)
                .append(tPlayer.marketCurrency, DARK_AQUA)
                .append(" lys d'or.", GRAY)
                .appendNewline()
                .append("Cliquez ici pour acheter des lys d'or", GRAY)
        ) {
            tPlayer.sendMessage(
                Component.text("[", GOLD)
                    .append("Cliquez ici pour acheter des lys d'or", YELLOW)
                    .append("]", GOLD)
                    .clickEvent(
                        ClickEvent.openUrl("https://tesseract.craftingstore.net/")
                    )
            )
        }
    }

    fun confirmBuyLysDor(player: TPlayer, price: Int,message: String, consumer: Consumer<Int>) {
        if(player.getMarketCurrency() < price) {
            player.sendMessage(ChatFormats.SHOP_ADMIN, "Vous n'avez pas suffisamment de lys d'or. Nécessite : $price")
            return
        }
        openConfirmationMenu(player.bukkitPlayer,message,this
        ) { player.addMarketCurrency(-price)
            consumer.accept(price)
        }
    }
}