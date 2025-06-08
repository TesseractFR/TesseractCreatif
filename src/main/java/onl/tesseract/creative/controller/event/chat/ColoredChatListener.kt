package onl.tesseract.creative.controller.event.chat

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.service.chat.PlayerTagHoverService
import onl.tesseract.core.event.ColoredChat
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.creative.service.nickname.NicknameService
import onl.tesseract.creative.service.rank.PlayerRankService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

@org.springframework.stereotype.Component
class ColoredChatListener(
    private val nicknameService: NicknameService,
    private val rankService: PlayerRankService,
    private val tPlayerInfoService: TPlayerInfoService,
    private val playerTagHoverService: PlayerTagHoverService,
) : Listener {

    @EventHandler
    fun onPlayerChat(event: AsyncChatEvent) {
        val player = event.player
        val playerRank = rankService.getPlayerRank(player.uniqueId)
        val staffRank = rankService.getStaffRank(player.uniqueId)
        val color = staffRank?.color ?: playerRank.color
        val titleDisplay = staffRank?.title ?: playerRank.title

        val gender = tPlayerInfoService[player.uniqueId].genre
        val nickname = nicknameService.getNickname(player.uniqueId)

        val displayNameComponent = nickname?.let {
            val coloredNickname = ColoredChat.colorComponent(Component.text(it))
            playerTagHoverService.getOnClickComponent(player.uniqueId, coloredNickname)
        } ?: playerTagHoverService.getOnClickComponent(player.uniqueId)

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
