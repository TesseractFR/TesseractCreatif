package onl.tesseract.listener

import net.kyori.adventure.text.Component
import onl.tesseract.nickname.NicknameService
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.tesseractlib.event.ColoredChat
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import onl.tesseract.service.CreativeServices.Companion.get

class NicknameListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val playerRank = get(PlayerRankService::class.java).getPlayerRank(event.player.uniqueId)
        val rankColor = playerRank.color

        val nickname = get(NicknameService::class.java).getNickname(event.player.uniqueId)

        val displayName = if (nickname != null) {
            val formattedNickname = ColoredChat.colorComponent(Component.text(nickname, rankColor))
            event.player.displayName(formattedNickname)
            event.player.playerListName(formattedNickname)
            formattedNickname
        } else {
            val formattedName = Component.text(event.player.name, rankColor)
            event.player.displayName(formattedName)
            event.player.playerListName(formattedName)
            formattedName
        }

    }
}
