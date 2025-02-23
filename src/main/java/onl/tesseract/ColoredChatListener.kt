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
        val playerRank = rankService.getPlayerRank(event.player.uniqueId)
        val staffRank = rankService.getStaffRank(event.player.uniqueId)
        val color = staffRank?.color ?: playerRank.color
        val titleDisplay = staffRank?.title ?: playerRank.title

        val gender = ServiceContainer[TPlayerInfoService::class.java][event.player.uniqueId].genre

        val prefix = Component.text()
            .append(Component.text("[${titleDisplay.getDisplayName(gender)}] ", color, TextDecoration.BOLD))
            .append(Component.text(event.player.name, color))
            .append(Component.text(" : ", NamedTextColor.WHITE))
            .build()

        val coloredMessage = ColoredChat.colorComponent(event.message())
        val finalMessage = prefix.append(coloredMessage)

        event.renderer { _, _, _, _ -> finalMessage }
    }
}
