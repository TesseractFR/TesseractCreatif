package onl.tesseract.creative.controller.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.DARK_GRAY
import net.kyori.adventure.text.format.NamedTextColor.GOLD
import net.kyori.adventure.text.format.NamedTextColor.GRAY
import net.kyori.adventure.text.format.NamedTextColor.GREEN
import net.kyori.adventure.text.format.NamedTextColor.YELLOW
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.service.player.PermissionService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.util.permissionService
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

class RankMenu(player: Player, previous: Menu? = null) :
        BoutiqueCoreMenu(
            MenuSize.Three,
            Component.text("Améliorez votre grade", NamedTextColor.BLUE, TextDecoration.BOLD),
            previous,
            player) {

    private val playerRankService: PlayerRankService = playerRankService()
    private val permissionService: PermissionService = permissionService()

    override fun placeButtons(viewer: Player) {
        super.placeButtons(viewer)
        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", NamedTextColor.WHITE)
                    .build()) {}
        }
        val actualRank = BoutiqueRank.fromPlayerRank(playerRankService.getPlayerRank(player.uniqueId))
        addButton(0, teteInformation
            .name("Informations", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore()
            .newline()
            .append("Les prix sont dégressifs selon votre grade actuel.", GRAY, TextDecoration.ITALIC)
            .newline()
            .newline()
            .append("Prix à payer = ", GOLD, setOf(TextDecoration.BOLD, TextDecoration.ITALIC))
            .append("prix du grade cible ", YELLOW, TextDecoration.ITALIC)
            .append("- ", GRAY, TextDecoration.ITALIC)
            .append("prix de votre grade actuel", YELLOW, TextDecoration.ITALIC)
            .newline()
            .newline()
            .append("Virtuose ", NamedTextColor.AQUA, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
            .append(": 2500 lys d'or pour tous les grades, puis 1500 à partir de ", GRAY, TextDecoration.ITALIC)
            .append("Bâtisseur", NamedTextColor.BLUE, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
            .append(".", GRAY, TextDecoration.ITALIC)
            .newline()
            .buildLore()
            .build()
        ) {}
        createRankButton(10, BoutiqueRank.CONCEPTEUR, actualRank)
        createRankButton(12, BoutiqueRank.CREATEUR, actualRank)
        createRankButton(14, BoutiqueRank.INGENIEUR, actualRank)
        createRankButton(16, BoutiqueRank.BATISSEUR, actualRank)
        createRankButton(4, BoutiqueRank.VIRTUOSE, actualRank)

        addBackButton()
        addCloseButton()
    }

    private fun createRankButton(index: Int, targetRank: BoutiqueRank, actualRank: BoutiqueRank) {

        if (targetRank > actualRank) {
            addButton(index, createRankItem(targetRank, actualRank, true)) {
                val price = getPrice(targetRank, actualRank)
                confirmBuyLysDor(player, price, "Confirmer l'achat du grade ${targetRank.strName} pour $price lys d'or")
                {
                    playerRankService.setPlayerRank(
                        player.uniqueId,
                        targetRank.playerRank)
                    player.sendMessage(
                        ChatFormats.SHOP_ADMIN.append(
                            "Vous venez d'acheter le grade ${targetRank.strName}",
                            GOLD))
                    permissionService.updatePermission(player.uniqueId)
                    this.close()
                }
            }
        } else {
            addButton(index, createRankItem(targetRank, actualRank, false)) {
            }
        }
    }


    private fun createRankItem(targetRank: BoutiqueRank, actualRank: BoutiqueRank, buyable: Boolean): ItemStack {
        val playerRank = targetRank.playerRank
        var ilb = ItemLoreBuilder()
                .newline()
                .append("Nombre de plots maximum : ", GRAY, TextDecoration.ITALIC)
                .newline()
                .append("Monde 100 : ", GOLD)
                .append(playerRank.rankPlot.plot100.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
                .newline()
                .append("Monde 250 : ", GOLD)
                .append(playerRank.rankPlot.plot250.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
                .newline()
                .append("Monde 500 : ", GOLD)
                .append(playerRank.rankPlot.plot500.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
                .newline()
                .append("Monde 1000 : ", GOLD)
                .append(playerRank.rankPlot.plot1000.toString(), NamedTextColor.WHITE, TextDecoration.BOLD)
                .newline()
        if (targetRank == BoutiqueRank.BATISSEUR) {
            ilb = ilb.newline()
                    .append("Obtention de ", GREEN, TextDecoration.BOLD)
                    .append("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
                    .newline()
        }
        if (buyable) {
            val price = getPrice(targetRank, actualRank)
            val previousRank = BoutiqueRank.entries
                .filter { it < targetRank }
                .maxByOrNull { it.price }

            ilb = ilb.newline()
                .append("Prix : ", GRAY)
                .newline()
                .append("$price ", YELLOW, setOf(TextDecoration.BOLD, TextDecoration.ITALIC))
                .append("lys d'or", YELLOW, TextDecoration.ITALIC)
            if (targetRank != BoutiqueRank.VIRTUOSE) {
                ilb = ilb.newline()
                    .append("(${targetRank.hourPrice} lys d'or/heure de jeu)", DARK_GRAY, TextDecoration.ITALIC)
            }
            if (previousRank != null && previousRank.hourPrice > targetRank.hourPrice) {
                ilb = ilb.newline()
                    .append("⭐ Plus rentable que ", GREEN, TextDecoration.ITALIC)
                    .append(previousRank.strName, GREEN, setOf(TextDecoration.ITALIC, TextDecoration.BOLD))
            }

            ilb = ilb.newline()
                .newline()
                .append("--- Clic gauche ---", NamedTextColor.LIGHT_PURPLE)
                .newline()
                .append("Acheter en lys d'or", NamedTextColor.AQUA)
                .newline()
        } else {
            ilb = ilb.newline()
                    .append("Vous possédez déjà ce grade ", GRAY)
                    .newline()
        }
        return playerRank.headBuilder
                .name(
                    playerRank.name.lowercase()
                            .replaceFirstChar { it.uppercase() }, playerRank.color, TextDecoration.BOLD)
                .lore(ilb.get())
                .build()
    }

    private fun getPrice(targetRank: BoutiqueRank, actualRank: BoutiqueRank): Int {
        val price = targetRank.price - actualRank.price
        return when (targetRank) {
            BoutiqueRank.VIRTUOSE -> minOf(price, 2500)
            else -> price
        }
    }

    companion object {
        val teteInformation = ItemBuilder(Material.PLAYER_HEAD).customHead(
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTY0MzlkMmUzMDZiMjI1NTE2YWE5YTZkMDA3YTdlNzVlZGQyZDUwMTVkMTEzYjQyZjQ0YmU2MmE1MTdlNTc0ZiJ9fX0=",
            ""
        )
    }
}
