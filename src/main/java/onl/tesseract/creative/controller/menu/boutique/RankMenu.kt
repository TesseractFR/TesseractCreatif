package onl.tesseract.creative.controller.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.NamedTextColor.GOLD
import net.kyori.adventure.text.format.NamedTextColor.GRAY
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.domain.rank.PlayerRank
import onl.tesseract.creative.service.player.PermissionService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.util.permissionService
import onl.tesseract.creative.util.playerRankService
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.lib.util.append
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class RankMenu(player: Player, previous: Menu? = null) :
        BoutiqueCoreMenu(
            MenuSize.Three,
            Component.text("Améliorer votre grade", NamedTextColor.BLUE, TextDecoration.BOLD),
            previous,
            player) {

    private val playerRankService: PlayerRankService = playerRankService()
    private val permissionService: PermissionService = permissionService()

    override fun placeButtons(viewer: Player) {
        super.placeButtons(viewer)
        val actualRank = BoutiqueRank.fromPlayerRank(playerRankService.getPlayerRank(player.uniqueId))
        createRankButton(1, BoutiqueRank.CONCEPTEUR, actualRank)
        createRankButton(11, BoutiqueRank.CREATEUR, actualRank)
        createRankButton(15, BoutiqueRank.INGENIEUR, actualRank)
        createRankButton(7, BoutiqueRank.BATISSEUR, actualRank)
        addButton(4, virtuoseItem) {
            confirmBuyLysDor(player, 2400, "Confirmer l'achat du grade Virtuose pour 2400 lys d'or")
            {
                playerRankService.setPlayerRank(player.uniqueId, PlayerRank.VIRTUOSE)
                player.sendMessage(ChatFormats.SHOP_ADMIN.append("Vous venez d'acheter le grade Virtuose", GOLD))
                permissionService.updatePermission(player.uniqueId)
                this.close()
            }
        }

        addBackButton()
        addCloseButton()
    }

    private fun createRankButton(index: Int, targetRank: BoutiqueRank, actualRank: BoutiqueRank) {

        if (targetRank > actualRank) {
            addButton(index, createRankItem(targetRank, actualRank, true)) {
                val price = targetRank.price - actualRank.price;
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
                    .append("Obtention de ", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .append("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
        }
        if (buyable) {
            ilb = ilb.newline()
                    .append("Prix : ", GRAY)
                    .newline()
                    .append(
                        (targetRank.price - actualRank.price).toString() + " lys d'or (" + targetRank.hourPrice + "/heure)",
                        GOLD,
                        TextDecoration.ITALIC)
                    .newline()
                    .newline()
                    .append("Cliquez pour acheter ", GRAY)
                    .newline()
        } else {
            ilb = ilb.newline()
                    .append("Vous possédez déjà ce grade ", GRAY)
                    .newline()
        }
        return playerRank.headBuilder
                .name(
                    playerRank.name.lowercase()
                            .replaceFirstChar { it.uppercase() }, NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
                .lore(ilb.get())
                .build()
    }
}
