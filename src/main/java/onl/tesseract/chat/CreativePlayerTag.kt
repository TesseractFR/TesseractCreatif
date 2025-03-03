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

    private val mentionPattern = Pattern.compile(".*?(@|j#)(\\S+).*")

    override fun getMatcher(component: TextComponent): Matcher {
        return mentionPattern.matcher(component.content())
    }

    override fun apply(component: TextComponent, sender: Player): TextComponent {
        val matcher = mentionPattern.matcher(component.content())
        var modifiedComponent: TextComponent = component // Copie mutable

        while (matcher.find()) {
            val pseudo = matcher.group(2) // Récupération du pseudo mentionné
            val offlinePlayer = Bukkit.getOfflinePlayer(pseudo)
            val mentionedUUID = offlinePlayer.uniqueId

            if (!offlinePlayer.hasPlayedBefore()) {
                // Si le joueur n'existe pas, garder le texte brut
                val same = Component.text(pseudo).style(component.style())
                modifiedComponent = Util.replace(modifiedComponent, matcher.start(1), matcher.end(1), same)
            } else {
                // Appliquer le formatage de la mention avec grade + couleur
                val mentionComponent = getComponent(mentionedUUID)

                // Si le joueur est en ligne, lui envoyer un son de notification
                if (offlinePlayer.isOnline) {
                    offlinePlayer.player?.playSound(offlinePlayer.player!!.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 5f, 1f)
                }

                modifiedComponent = modifiedComponent.replaceText { builder ->
                    builder.match(mentionPattern).replacement { matchResult ->
                        getComponent(mentionedUUID)
                    }
                } as TextComponent
            }
        }
        return modifiedComponent
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
        val playerRank = rankService.getPlayerRank(obj)
        val color = playerRank.color

        // Récupérer le nom du joueur et appliquer le format du grade
        val playerName = offlinePlayer.name ?: "Inconnu"
        val rankPrefix = Component.text("[${playerRank.name}] ", color, TextDecoration.BOLD)
        val playerMention = Component.text(playerName, color, TextDecoration.BOLD)

        return rankPrefix.append(playerMention)
            .hoverEvent(HoverEvent.showText(hover(obj)))
            .clickEvent(ClickEvent.suggestCommand("/msg $playerName "))
    }
}
