package onl.tesseract.chat.tags

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.chat.tag.PlayerTag
import onl.tesseract.lib.util.Util
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.lib.service.ServiceContainer
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.entity.Player
import java.util.*
import java.util.regex.MatchResult
import java.util.regex.Matcher
import java.util.regex.Pattern

class CreativePlayerTag : PlayerTag {

    // Correction du pattern pour mieux détecter les mentions
    private val mentionPattern = Pattern.compile(".* ?((@|j#)(\\S+)).*")

    override fun getMatcher(component: TextComponent): Matcher {
        return mentionPattern.matcher(component.content())
    }

    override fun apply(component: TextComponent, sender: Player): TextComponent {
        val matcher = mentionPattern.matcher(component.content())
        var modifiedComponent: TextComponent = component // Copie mutable

        if (matcher.matches()) {
            val pseudo = matcher.group(3) // Récupération du pseudo mentionné
            val offlinePlayer = Bukkit.getOfflinePlayer(pseudo)
            val mentionedUUID = offlinePlayer.uniqueId

            if (!offlinePlayer.hasPlayedBefore()) {
                // Si le joueur n'existe pas, garder le texte brut
                val same = Component.text(matcher.group(3)).style(component.style())
                return Util.replace(modifiedComponent, matcher.start(1), matcher.end(1), same)
            }
            return apply(modifiedComponent, mentionedUUID, matcher.toMatchResult())
        }
        return modifiedComponent
    }

    private fun apply(component: TextComponent, uuid: UUID, result: MatchResult): TextComponent {
        val offlinePlayer = Bukkit.getOfflinePlayer(uuid)

        if (offlinePlayer.isOnline) {
            offlinePlayer.player?.playSound(offlinePlayer.player!!.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5f, 1f)
        }

        return Util.replace(component, result.start(1), result.end(1), getComponent(uuid))
    }

    override fun hover(obj: UUID): TextComponent {
        val rankService = ServiceContainer[PlayerRankService::class.java]
        val playerRank = rankService.getPlayerRank(obj)
        return Component.text("Grade: ", NamedTextColor.GOLD)
            .append(Component.text(playerRank.name, playerRank.color))
            .append(Component.newline())
            .append(Component.text("Cliquez pour envoyer un message.", NamedTextColor.GRAY))
    }

    override fun getComponent(obj: UUID): TextComponent {
        val offlinePlayer = Bukkit.getOfflinePlayer(obj)
        val rankService = ServiceContainer[PlayerRankService::class.java]
        val color = rankService.getStaffRank(obj)?.color
            ?: rankService.getPlayerRank(obj).color

        // ✅ On affiche seulement le pseudo sans le grade
        val playerName = offlinePlayer.name ?: "Inconnu"
        val playerMention = Component.text(playerName, color, TextDecoration.BOLD)

        val finalMention = playerMention
            .hoverEvent(HoverEvent.showText(hover(obj)))
            .clickEvent(ClickEvent.suggestCommand("/msg $playerName "))

        // 🔥 DEBUG : Vérification dans les logs
        Bukkit.getLogger().info("Mention colorée générée (sans grade): $finalMention")

        return finalMention
    }

}
