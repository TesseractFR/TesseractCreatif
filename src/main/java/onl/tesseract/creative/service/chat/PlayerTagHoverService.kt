package onl.tesseract.creative.service.chat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.service.timeplayed.PlayerTimePlayedService
import onl.tesseract.creative.util.DurationFormat
import org.bukkit.Bukkit
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PlayerTagHoverService(
    private val rankService: PlayerRankService,
    private val timePlayedService: PlayerTimePlayedService,
) {
    fun getHoverComponent(uuid: UUID): Component {
        val offlinePlayer = Bukkit.getOfflinePlayer(uuid)
        val playerRank = rankService.getPlayerRank(uuid)
        val staffRank = rankService.getStaffRank(uuid)
        val timePlayed = timePlayedService.getPlayerTimePlayed(uuid)
        val formattedTime = DurationFormat.formatTime(timePlayed)

        val hoverText = Component.text()
            .append(Component.text("Pseudo : ", NamedTextColor.GOLD))
            .append(Component.text(offlinePlayer.name ?: "Inconnu", NamedTextColor.WHITE)) // ✅ Pseudo en blanc
            .append(Component.newline())

        staffRank?.let {
            hoverText.append(Component.text("Grade Staff : ", NamedTextColor.GOLD))
                .append(Component.text(it.name, it.color))
                .append(Component.newline())
        }

        hoverText.append(Component.text("Grade Joueur : ", NamedTextColor.GOLD))
            .append(Component.text(playerRank.name, playerRank.color))
            .append(Component.newline())
            .append(Component.text("Temps de jeu : ", NamedTextColor.GOLD))
            .append(Component.text(formattedTime, NamedTextColor.WHITE))
            .append(Component.newline())
            .append(Component.text("Cliquez pour envoyer un message.", NamedTextColor.GRAY, TextDecoration.ITALIC))

        return hoverText.build()
    }

    fun getOnClickComponent(uuid: UUID, displayComponent: Component? = null): TextComponent {
        val offlinePlayer = Bukkit.getOfflinePlayer(uuid)
        val color = rankService.getStaffRank(uuid)?.color ?: rankService.getPlayerRank(uuid).color
        val playerName = offlinePlayer.name ?: "Inconnu"

        val finalComponent = (displayComponent ?: Component.text(playerName, color)) as TextComponent

        val textComponent = finalComponent
            .hoverEvent(HoverEvent.showText { getHoverComponent(uuid) })
            .clickEvent(ClickEvent.suggestCommand("/msg $playerName "))

        return textComponent
    }
}
