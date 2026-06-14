package onl.tesseract.creative.controller.menu

import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.vote.AVoteRewardMenu
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.util.playerTimePlayedService
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.plus
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

class CreativeVoteRewardMenu(uuid: UUID, menu: Menu) : AVoteRewardMenu(uuid, MenuSize.Two, menu) {

    private val playerTimePlayedService: PlayerTimePlayedService = playerTimePlayedService()

    override fun placeButtons(viewer: Player) {
        fill(ItemBuilder(Material.GRAY_STAINED_GLASS_PANE, " ").build())
        addBackButton()
        addCloseButton()

        addConvertionRewardButton(4, viewer)
    }

    fun addConvertionRewardButton(index: Int, viewer: Player) {
        addButton(
            index, ItemBuilder(Material.CLOCK)
                    .name("Lys temporels", NamedTextColor.GOLD, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("Échanger des points de vote contre des lys temporels, permettant notamment d'acheter des ", NamedTextColor.GRAY, TextDecoration.ITALIC)
                .append("plots supplémentaires", NamedTextColor.WHITE, setOf(TextDecoration.BOLD, TextDecoration.ITALIC))
                .append(".", NamedTextColor.GRAY, TextDecoration.ITALIC)
                .newline(2)
                    .append("1 point", NamedTextColor.YELLOW)
                    .append(" = ", NamedTextColor.GRAY)
                    .append("1 lys temporel", NamedTextColor.YELLOW)
                    .buildLore()
                    .build()
        ) {
            askAmount(viewer) { amount ->
                playerTimePlayedService.addTemporalLys(playerID, amount)
                Bukkit.getPlayer(playerID)
                        ?.sendMessage(ChatFormats.VOTE + "Vous avez reçu $amount lys temporels !")
            }
        }


    }
}