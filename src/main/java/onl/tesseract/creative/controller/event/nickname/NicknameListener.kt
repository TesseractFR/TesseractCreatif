package onl.tesseract.creative.controller.event.nickname

import onl.tesseract.core.event.ColoredChat
import onl.tesseract.creative.service.nickname.NicknameService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.springframework.stereotype.Component

@Component
class NicknameListener(val nicknameService: NicknameService) : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val nickname = nicknameService.getNickname(event.player.uniqueId)

        if (nickname != null) {
            val formattedNickname = ColoredChat.colorComponent(net.kyori.adventure.text.Component.text(nickname))
            event.player.displayName(formattedNickname)
            event.player.playerListName(formattedNickname)
        }
    }
}