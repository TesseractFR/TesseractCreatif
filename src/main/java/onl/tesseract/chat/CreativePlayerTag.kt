package onl.tesseract.chat.tags

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.chat.PlayerTagHover
import onl.tesseract.lib.chat.tag.PlayerTag
import onl.tesseract.lib.util.Util
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.lib.service.ServiceContainer
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class CreativePlayerTag : PlayerTag {

    private val mentionPattern = Pattern.compile(".* ?((@|j#)(\\S+)).*")
    private val rankService: PlayerRankService = ServiceContainer[PlayerRankService::class.java]

    override fun getMatcher(component: TextComponent): Matcher = mentionPattern.matcher(component.content())

    override fun apply(component: TextComponent, sender: Player): TextComponent {
        val matcher = mentionPattern.matcher(component.content())
        return if (matcher.find()) {
            val pseudo = matcher.group(3)
            val offlinePlayer = Bukkit.getOfflinePlayer(pseudo)

            if (!offlinePlayer.hasPlayedBefore()) {
                Util.replace(component, matcher.start(1), matcher.end(1), Component.text(pseudo).style(component.style()))
            } else {
                offlinePlayer.player?.playSound(offlinePlayer.player!!.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5f, 1f)
                Util.replace(component, matcher.start(1), matcher.end(1), getComponent(offlinePlayer.uniqueId))
            }
        } else component
    }

    override fun hover(obj: UUID?): TextComponent? {
        return obj?.let { PlayerTagHover.getHoverComponent(it) } as TextComponent?
    }

    override fun getComponent(obj: UUID): TextComponent {
        val offlinePlayer = Bukkit.getOfflinePlayer(obj)
        val playerName = offlinePlayer.name ?: "Inconnu"

        return PlayerTagHover.getOnClickComponent(obj, playerName, bold = TextDecoration.BOLD)
    }

}
