package onl.tesseract.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor.*
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.lib.util.append
import onl.tesseract.permpack.PlayerPermPackService
import onl.tesseract.player.PermissionService
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.Duration
import java.time.Instant
import java.time.ZoneId

private val tetePlayerParticles = ItemBuilder(Material.PLAYER_HEAD).customHead(
    "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ2MWQ5ZDA2YzBiZjRhN2FmNGIxNmZkMTI4MzFlMmJlMGNmNDJlNmU1NWU5YzBkMzExYTJhODk2NWEyM2IzNCJ9fX0=",
    "4461d9d06c0bf4a7af4b16fd12831e2be0cf42e6e55e9c0d311a2a8965a23b34"
)

private const val PRIX_TEXT = "Prix : "

private const val PRIX_VALEUR_TEXT = "200 lys d'or / mois"

class RentPluginsMenu(private val player: Player, previous: Menu) :
    BoutiqueCoreMenu(
        MenuSize.Three,
        Component.text("Louer des plugins et outils", DARK_GREEN, TextDecoration.BOLD),
        previous
    ) {


    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())

        val packService = ServiceContainer[PlayerPermPackService::class.java]
        addButton(0, getArceonItemStack()
        ) {
            confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois d'Arceon pour 200 lys d'or")
            {
                packService.addTimeLeftArceon(player.uniqueId, Duration.ofDays(30))
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions Arceon",GOLD))
                ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
                this.close()
            }
        }

        addButton(
            1, getPlayerParticlesItemStack()
        ) {
            confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois de PlayerParticles pour 200 lys d'or")
            {
                packService.addTimeLeftPlayerParticule(player.uniqueId, Duration.ofDays(30))
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions PlayerParticles",GOLD))
                ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
                this.close()
            }
        }

        addButton(
            2, getMetaBrushItemStack()
        ) {confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois de MetaBrush pour 200 lys d'or")
        {
            packService.addTimeLeftMetaBrush(player.uniqueId, Duration.ofDays(30))
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions MetaBrush",GOLD))
            ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
            this.close()
        }
        }

        addButton(
            3, getDisplayEntitiesItemStack()
        ) {confirmBuyLysDor(player, 200, "Confirmer l'achat d'un mois de DisplayEntities pour 200 lys d'or")
        {
            packService.addTimeLeftDisplayEntity(player.uniqueId, Duration.ofDays(30))
            player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter les permissions DisplayEntities",GOLD))
            ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
            this.close()
        } }

        addBackButton()
        addCloseButton()
    }

    private fun getArceonItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
            .append("Offre l'accès au plugin Arceon",LIGHT_PURPLE)
            .newline()
                .append(PRIX_TEXT, GRAY)
            .newline()
                .append(PRIX_VALEUR_TEXT, GOLD, TextDecoration.ITALIC)
        val packService = ServiceContainer[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftArceon(player.uniqueId)
        addExpirationDate(timeLeft, ilb)
        return ItemBuilder(Material.PAPER)
                .name("Arceon", GREEN, TextDecoration.BOLD)
                .lore(ilb.get())
                .build()
    }

    private fun getMetaBrushItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
                .append("Offre l'accès au plugin MetaBrush",LIGHT_PURPLE)
                .newline()
                .append(PRIX_TEXT, GRAY)
                .newline()
                .append(PRIX_VALEUR_TEXT, GOLD, TextDecoration.ITALIC)
        val packService = ServiceContainer[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftMetaBrush(player.uniqueId)

        addExpirationDate(timeLeft, ilb)
        return ItemBuilder(Material.BRUSH)
                .name("MetaBrush", DARK_BLUE, TextDecoration.BOLD)
                .lore(ilb.get())
                .build()
    }

    private fun getPlayerParticlesItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
                .append("Offre l'accès au plugin PlayerParticles",LIGHT_PURPLE)
                .newline()
                .append(PRIX_TEXT, GRAY)
                .newline()
                .append(PRIX_VALEUR_TEXT, GOLD, TextDecoration.ITALIC)
        val packService = ServiceContainer[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftPlayerParticule(player.uniqueId)

        addExpirationDate(timeLeft, ilb)
        return tetePlayerParticles
                .name("PlayerParticles", LIGHT_PURPLE, TextDecoration.BOLD)
                .lore(ilb.get())
                .build()
    }

    private fun getDisplayEntitiesItemStack(): ItemStack {
        val ilb = ItemLoreBuilder().newline()
                .append("Offre l'accès au plugin DisplayEntities",LIGHT_PURPLE)
                .newline()
                .append(PRIX_TEXT, GRAY)
                .newline()
                .append(PRIX_VALEUR_TEXT, GOLD, TextDecoration.ITALIC)
        val packService = ServiceContainer[PlayerPermPackService::class.java]
        val timeLeft = packService.getTimeLeftDisplayEntity(player.uniqueId)

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
