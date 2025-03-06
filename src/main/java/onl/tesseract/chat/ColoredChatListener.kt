package onl.tesseract.chat

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.event.ColoredChat
import onl.tesseract.core.persistence.hibernate.boutique.TPlayerInfoService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.nickname.NicknameService
import onl.tesseract.rank.PlayerRankService
import org.bukkit.Bukkit
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

        val hoverText = Component.text()
            .append(Component.text("Pseudo: ", NamedTextColor.GRAY))
            .append(Component.text(player.name, NamedTextColor.WHITE))
            .append(Component.newline())
            .append(Component.text("Grade: ", NamedTextColor.GOLD))
            .append(Component.text(playerRank.name, playerRank.color))
            .append(Component.newline())
            .append(Component.text("Cliquez pour envoyer un message privé.", NamedTextColor.GRAY))
            .build()

        val displayNameComponent = if (nickname != null) {
            ColoredChat.colorComponent(Component.text(nickname, color))
        } else {
            Component.text(player.name, color)
        }.hoverEvent(HoverEvent.showText(hoverText))

        val prefix = Component.text()
            .append(Component.text("[${titleDisplay.getDisplayName(gender)}] ", color, TextDecoration.BOLD))
            .append(displayNameComponent)
            .append(Component.text(" : ", NamedTextColor.WHITE))
            .build()

        // ✅ Gère uniquement l'affichage du [Grade] Pseudo :
        event.renderer { _, _, _, _ ->
            Component.text()
                .append(prefix)
                .append(event.message()) // Ajoute le message du joueur
                .build()
        }

    }


}
