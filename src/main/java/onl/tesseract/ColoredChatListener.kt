package onl.tesseract

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.event.ColoredChat
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.lib.service.ServiceContainer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ColoredChatListener : Listener {

    private val rankService: PlayerRankService = ServiceContainer[PlayerRankService::class.java]

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val playerRank = rankService.getPlayerRank(player.uniqueId)
        val gender = ServiceContainer[TPlayerInfoService::class.java][player.uniqueId].genre

        val prefix = Component.text()
            .append(Component.text("[${playerRank.title.getDisplayName(gender)}] ", playerRank.color, TextDecoration.BOLD))
            .append(Component.text(player.name, playerRank.color))
            .append(Component.text(" : ", NamedTextColor.WHITE))
            .build()

        val coloredMessage = ColoredChat.colorComponent(event.message())

        val finalMessage = prefix.append(coloredMessage)

        event.renderer { _, _, _, _ -> finalMessage }
    }
}
