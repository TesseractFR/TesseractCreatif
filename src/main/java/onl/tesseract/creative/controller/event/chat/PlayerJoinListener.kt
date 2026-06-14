package onl.tesseract.creative.controller.event.chat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.event.ColoredChat
import onl.tesseract.creative.service.nickname.NicknameService
import onl.tesseract.creative.service.player.PermissionService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.lib.util.append
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.UUID
import org.springframework.stereotype.Component as SpringComponent

@SpringComponent
class PlayerJoinListener(
    private val nicknameService: NicknameService,
    private val permissionService: PermissionService,
    private val rankService: PlayerRankService,
) : Listener {
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val displayNameComponent = getFormattedDisplayName(event.player.uniqueId, event.player.name)
        if (!event.player.hasPlayedBefore()) {
            event.player.teleport(onl.tesseract.core.Config.invoke().firstSpawnLocation)
            event.joinMessage(
                Component.text("Bienvenue ", NamedTextColor.GOLD)
                        .append(displayNameComponent)
                        .append(" sur le Créatif !", NamedTextColor.GOLD)
            )
        } else {
            event.joinMessage(
                Component.text("+ ", NamedTextColor.GREEN)
                        .append(displayNameComponent)
                        .append(" a rejoint le serveur.", NamedTextColor.GOLD)
            )
        }
        permissionService.updatePermission(event.player.uniqueId)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val displayNameComponent = getFormattedDisplayName(event.player.uniqueId, event.player.name)
        event.quitMessage(
            Component.text("- ", NamedTextColor.RED)
                    .append(displayNameComponent)
                    .append(" s'est déconnecté.", NamedTextColor.GOLD)
        )
    }

    private fun getFormattedDisplayName(uuid: UUID, playerName: String): Component {
        val nickname = nicknameService.getNickname(uuid)
        val staffRank = rankService.getStaffRank(uuid)
        return when {
            nickname != null -> {
                val colored = ColoredChat.colorComponent(Component.text(nickname))
                if (colored.color() == null) colored.color(NamedTextColor.WHITE) else colored
            }
            rankService.isPrestige(uuid) -> Component.text("✦ ", NamedTextColor.YELLOW)
                .append(Component.text(playerName, NamedTextColor.GOLD, TextDecoration.BOLD))
                .append(Component.text(" ✦", NamedTextColor.YELLOW))
            staffRank != null -> Component.text(playerName, staffRank.color)
            else -> Component.text(playerName, rankService.getPlayerRank(uuid).color)
        } as Component
    }

}