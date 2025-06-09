package onl.tesseract.creative.domain.chat

import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.service.chat.PlayerTagHoverService
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.lib.chat.tag.PlayerTag
import onl.tesseract.lib.util.Util
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.regex.Matcher
import java.util.regex.Pattern

@Component
class CreativePlayerTag(
    private val rankService: PlayerRankService,
    private val playerTagHover: PlayerTagHoverService,
) : PlayerTag {

    private val mentionPattern = Pattern.compile(".* ?((@|j#)(\\S+)).*")

    override fun getMatcher(component: TextComponent): Matcher = mentionPattern.matcher(component.content())

    override fun apply(component: TextComponent, sender: Player): TextComponent {
        val matcher = mentionPattern.matcher(component.content())
        return if (matcher.find()) {
            val pseudo = matcher.group(3)
            val offlinePlayer = Bukkit.getOfflinePlayer(pseudo)

            if (!offlinePlayer.hasPlayedBefore()) {
                Util.replace(
                    component,
                    matcher.start(1),
                    matcher.end(1),
                    net.kyori.adventure.text.Component.text(pseudo)
                            .style(component.style()))
            } else {
                offlinePlayer.player?.playSound(
                    offlinePlayer.player!!.location,
                    Sound.ENTITY_EXPERIENCE_ORB_PICKUP,
                    5f,
                    1f)
                Util.replace(component, matcher.start(1), matcher.end(1), getComponent(offlinePlayer.uniqueId))
            }
        } else component
    }

    override fun hover(obj: UUID?): TextComponent? {
        return obj?.let { playerTagHover.getHoverComponent(it) } as TextComponent?
    }

    override fun getComponent(obj: UUID): TextComponent {
        val offlinePlayer = Bukkit.getOfflinePlayer(obj)
        val playerName = offlinePlayer.name ?: "Inconnu"

        val color = rankService.getStaffRank(obj)?.color ?: rankService.getPlayerRank(obj).color
        return playerTagHover.getOnClickComponent(
            obj,
            net.kyori.adventure.text.Component.text(playerName, color, TextDecoration.BOLD))
    }


}