package onl.tesseract.chat

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.event.ColoredChat
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.nickname.NicknameService
import onl.tesseract.rank.PlayerRankService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class ColoredChatListener : Listener {

    private val rankService: PlayerRankService = ServiceContainer[PlayerRankService::class.java]
    private val nicknameService: NicknameService = ServiceContainer[NicknameService::class.java]
    private val tPlayerInfoService = ServiceContainer[TPlayerInfoService::class.java]

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val playerRank = rankService.getPlayerRank(player.uniqueId)
        val staffRank = rankService.getStaffRank(player.uniqueId)
        val color = staffRank?.color ?: playerRank.color
        val titleDisplay = staffRank?.title ?: playerRank.title

        val gender = tPlayerInfoService[player.uniqueId].genre
        val nickname = nicknameService.getNickname(player.uniqueId)

        val displayNameComponent = if (nickname != null) {
            val coloredNickname = ColoredChat.colorComponent(Component.text(nickname))
            coloredNickname.hoverEvent(HoverEvent.showText { PlayerTagHover.getHoverComponent(player.uniqueId) })
                .clickEvent(ClickEvent.suggestCommand("/msg ${player.name} "))
        } else {
            PlayerTagHover.getOnClickComponent(player.uniqueId)
        }

        val prefix = Component.text()
            .append(Component.text("[${titleDisplay.getDisplayName(gender)}] ", color, TextDecoration.BOLD))
            .append(displayNameComponent)
            .append(Component.text(" : ", NamedTextColor.WHITE))
            .build()

        event.renderer { _, _, _, _ ->
            Component.text()
                .append(prefix)
                .append(event.message())
                .build()
        }
    }
}
