package onl.tesseract.nickname

import net.kyori.adventure.text.Component
import onl.tesseract.tesseractlib.event.ColoredChat
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import onl.tesseract.service.CreativeServices.Companion.get

class NicknameListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val nickname = get(NicknameService::class.java).getNickname(event.player.uniqueId)

        if (nickname != null) {
            val formattedNickname = ColoredChat.colorComponent(Component.text(nickname))
            event.player.displayName(formattedNickname)
            event.player.playerListName(formattedNickname)
        }
    }
}
