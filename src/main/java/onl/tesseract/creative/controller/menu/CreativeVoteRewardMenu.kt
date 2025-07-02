package onl.tesseract.creative.controller.menu

import net.kyori.adventure.text.format.NamedTextColor
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
                    .name("Lys temporel", NamedTextColor.GOLD)
                    .lore()
                    .newline()
                    .append("Échanger des points de vote contre des lys temporel", NamedTextColor.GRAY)
                    .newline(2)
                    .append("1 point", NamedTextColor.YELLOW)
                    .append(" = ", NamedTextColor.GRAY)
                    .append(" 1 Lys temporel", NamedTextColor.YELLOW)
                    .buildLore()
                    .build()
        ) {
            askAmount(viewer) { amount ->
                playerTimePlayedService.addTemporalLys(playerID, amount)
                Bukkit.getPlayer(playerID)
                        ?.sendMessage(ChatFormats.VOTE + "Vous avez reçu $amount Lys temporel !")
            }
        }


    }
}