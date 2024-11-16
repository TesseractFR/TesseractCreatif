package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.*
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.permpack.PlayerPermPackService
import onl.tesseract.player.CreativePlayer
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.ItemBuilder
import onl.tesseract.tesseractlib.util.ItemLoreBuilder
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import onl.tesseract.tesseractlib.util.menu.InventoryMenu.getCustomHead
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

private val tetePlayerParticles: ItemStack = getCustomHead(
    "",
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ2MWQ5ZDA2YzBiZjRhN2FmNGIxNmZkMTI4MzFlMmJlMGNmNDJlNmU1NWU5YzBkMzExYTJhODk2NWEyM2IzNCJ9fX0=",
    "4461d9d06c0bf4a7af4b16fd12831e2be0cf42e6e55e9c0d311a2a8965a23b34"
)

class RentPluginsMenu(val player: TPlayer, previous: InventoryMenu) :
    BoutiqueCoreMenu(
        27,
        Component.text("Louer des plugins et outils", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
        previous
    ) {



    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        val packService = CreativeServices[PlayerPermPackService::class.java]
        addButton(0, getArceonItemStack()
        ) {
            confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois d'Arceon pour 200 lys d'or")
            {
                packService.addTimeLeftArceon(player.uuid,Duration.ofDays(30));
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions Arceon",GOLD))
                (player as CreativePlayer).updatePermission()
                this.close()
            }
        }

        addButton(
            1, getPlayerParticlesItemStack()
        ) {
            confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois de PlayerParticles pour 200 lys d'or")
            {
                packService.addTimeLeftPlayerParticule(player.uuid,Duration.ofDays(30));
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions PlayerParticles",GOLD))
                (player as CreativePlayer).updatePermission()
                this.close()
            }
        }

        addButton(
            2, getMetaBrushItemStack()
        ) {confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois de MetaBrush pour 200 lys d'or")
        {
            packService.addTimeLeftMetaBrush(player.uuid,Duration.ofDays(30));
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions MetaBrush",GOLD))
            (player as CreativePlayer).updatePermission()
            this.close()
        }
        }

        addButton(
            3, getDisplayEntitiesItemStack()
        ) {confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois de DisplayEntities pour 200 lys d'or")
        {
            packService.addTimeLeftDisplayEntity(player.uuid,Duration.ofDays(30));
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions DisplayEntities",GOLD))
            (player as CreativePlayer).updatePermission()
            this.close()
        } }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    private fun getArceonItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
            .append("Offre l'accès au plugin Arceon",LIGHT_PURPLE)
            .newline()
            .append("Prix : ", GRAY)
            .newline()
            .append("200 lys d'or / mois", GOLD, TextDecoration.ITALIC)
        val packService = CreativeServices[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftArceon(player.uuid)
        addExpirationDate(timeLeft, ilb)
        return ItemBuilder(Material.PAPER,Component.text("Arceon", GREEN, TextDecoration.BOLD),ilb.get()).build()
    }

    private fun getMetaBrushItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
                .append("Offre l'accès au plugin MetaBrush",LIGHT_PURPLE)
                .newline()
                .append("Prix : ", GRAY)
                .newline()
                .append("200 lys d'or / mois", GOLD, TextDecoration.ITALIC)
        val packService = CreativeServices[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftMetaBrush(player.uuid)

        addExpirationDate(timeLeft, ilb)
        return ItemBuilder(Material.BRUSH,Component.text("MetaBrush", DARK_BLUE, TextDecoration.BOLD),ilb.get()).build()
    }

    private fun getPlayerParticlesItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
                .append("Offre l'accès au plugin PlayerParticles",LIGHT_PURPLE)
                .newline()
                .append("Prix : ", GRAY)
                .newline()
                .append("200 lys d'or / mois", GOLD, TextDecoration.ITALIC)
        val packService = CreativeServices[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftPlayerParticule(player.uuid)

        addExpirationDate(timeLeft, ilb)
        return ItemBuilder(tetePlayerParticles)
                .name(Component.text("PlayerParticles", LIGHT_PURPLE, TextDecoration.BOLD))
                .lore(ilb.get())
                .build()
    }

    private fun getDisplayEntitiesItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
                .append("Offre l'accès au plugin DisplayEntities",LIGHT_PURPLE)
                .newline()
                .append("Prix : ", GRAY)
                .newline()
                .append("200 lys d'or / mois", GOLD, TextDecoration.ITALIC)
        val packService = CreativeServices[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftDisplayEntity(player.uuid)

        addExpirationDate(timeLeft, ilb)
        return ItemBuilder(Material.ARMOR_STAND)
                .name(Component.text("DisplayEntities", DARK_RED, TextDecoration.BOLD))
                .lore(ilb.get())
                .build()
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
