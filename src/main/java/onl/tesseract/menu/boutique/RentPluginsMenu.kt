package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.GOLD
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.permpack.PlayerPermPackService
import onl.tesseract.player.CreativePlayer
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.ItemBuilder
import onl.tesseract.tesseractlib.util.ItemLoreBuilder
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.AButton
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

class RentPluginsMenu(val player: TPlayer, previous: InventoryMenu) :
    BoutiqueCoreMenu(
        27,
        Component.text("Louer des plugins et outils", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
        previous
    ) {

    private val tetePlayerParticles: ItemStack = getCustomHead(
        "",
        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ2MWQ5ZDA2YzBiZjRhN2FmNGIxNmZkMTI4MzFlMmJlMGNmNDJlNmU1NWU5YzBkMzExYTJhODk2NWEyM2IzNCJ9fX0=",
        "4461d9d06c0bf4a7af4b16fd12831e2be0cf42e6e55e9c0d311a2a8965a23b34"
    )

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        val packService = CreativeServices[PlayerPermPackService::class.java]
        addButton(
            0, getArceonItemStack()
        ) {
            confirmBuyLysDor(player,200,"Confirmer l'achat d'un moins d'Arceon pour 200 lys d'or")
            {
                packService.addTimeLeftArceon(player.uuid,Duration.ofDays(30));
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions Arceon",GOLD))
                (player as CreativePlayer).updatePermission()
                this.close()
            }
        }

        addButton(
            1, tetePlayerParticles,
            Component.text("PlayerParticles", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            2, Material.BRUSH,
            Component.text("MetaBrush", NamedTextColor.DARK_BLUE, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            3, Material.ARMOR_STAND,
            Component.text("DisplayEntities", NamedTextColor.DARK_RED, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    private fun getArceonItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
            .append("Offre l'accès au plugin Arceon",NamedTextColor.LIGHT_PURPLE)
            .newline()
            .append("Prix : ",NamedTextColor.GRAY)
            .newline()
            .append("200 lys d'or / mois",NamedTextColor.GOLD,TextDecoration.ITALIC);
        val packService = CreativeServices[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftArceon(player.uuid)
        if(timeLeft.isPositive){
            val expirationDate = Instant.now().plus(timeLeft).atZone(ZoneId.systemDefault())
            ilb.newline()
                .append("Possédé jusqu'au ",NamedTextColor.LIGHT_PURPLE)
                .append(expirationDate.dayOfMonth.toString()+"/"+expirationDate.monthValue+"/"+expirationDate.year,NamedTextColor.AQUA)
        }
        return ItemBuilder(Material.PAPER,Component.text("Arceon", NamedTextColor.GREEN, TextDecoration.BOLD),ilb.get()).build()
    }

}
