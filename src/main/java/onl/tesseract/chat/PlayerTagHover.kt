package onl.tesseract.chat

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.timeplayed.PlayerTimePlayedService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.util.DurationFormat
import org.bukkit.Bukkit
import java.util.UUID

object PlayerTagHover {
    private val rankService: PlayerRankService = ServiceContainer[PlayerRankService::class.java]
    private val timePlayedService: PlayerTimePlayedService = ServiceContainer[PlayerTimePlayedService::class.java]

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
