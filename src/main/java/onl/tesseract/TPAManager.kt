package onl.tesseract

import net.kyori.adventure.text.Component
import onl.tesseract.lib.chat.ChatEntryService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.translation.LanguageManager
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.plus
import onl.tesseract.util.DurationFormat.formatTime
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration
import java.time.Instant
import java.util.*

class TPAManager {
    private val teleportCooldowns = mutableMapOf<UUID, Instant>()
    private val requestsCooldowns = mutableMapOf<UUID, TimedBukkitTask>()
    private val teleportCooldownDuration = Duration.ofSeconds(30)
    private val requestsCooldownsDuration = Duration.ofMinutes(2)
    private val chatEntryService: ChatEntryService = ServiceContainer[ChatEntryService::class.java]

    fun tpaRequest(sender: Player, dest: Player): Boolean {
        if (dest.name.isEmpty()) {
            sender.sendMessage(ChatFormats.CHAT_ERROR + LanguageManager["creative.tpa_manager.no_player", sender])
            return false
        }

        val lastTeleport = teleportCooldowns[sender.uniqueId]
        if (lastTeleport != null && Duration.between(lastTeleport, Instant.now()).seconds < teleportCooldownDuration.seconds) {
            val duration = teleportCooldownDuration.minus(Duration.between(lastTeleport, Instant.now()))
            val durationFormatted = formatTime(duration)
            sender.sendMessage(ChatFormats.CHAT_ERROR + LanguageManager["creative.tpa_manager.on_cooldown", mapOf("duration" to durationFormatted), sender])
            return true
        }

        val requestCooldownTask = requestsCooldowns[sender.uniqueId]
        if (requestCooldownTask != null) {
            val lastRequest = requestCooldownTask.removeInstant
            if (lastRequest != null && Duration.between(lastRequest, Instant.now()) < requestsCooldownsDuration) {
                val remainingTime = requestsCooldownsDuration.minus(Duration.between(lastRequest, Instant.now()))
                val remainingTimeFormatted = formatTime(remainingTime)
                sender.sendMessage(ChatFormats.CHAT_ERROR + LanguageManager["creative.tpa_manager.on_cooldown", mapOf("duration" to remainingTimeFormatted), sender])
                return true
            }
        }

        //if (player.uniqueId != dest.uniqueId) {
        askTeleport(sender, dest)
        //} else {
        //    player.sendMessage(ChatFormats.CHAT_ERROR + "Joueur invalide.")
        //}
        return true
    }

    private fun askTeleport(sender: Player, dest: Player) {
        sender.sendMessage(ChatFormats.CHAT + LanguageManager["creative.tpa_manager.send", sender])
        dest.sendMessage(ChatFormats.CHAT + LanguageManager["creative.tpa_manager.ask", mapOf("player" to dest.displayName()), dest])

        val acceptButton = LanguageManager["lib.chat.accept", dest]
            .clickEvent(chatEntryService.clickCommand(dest) {
                dest.sendMessage(ChatFormats.CHAT_SUCCESS + LanguageManager["creative.tpa_manager.dest_accept", dest])
                sender.sendMessage(ChatFormats.CHAT_SUCCESS + LanguageManager["creative.tpa_manager.src_accept", sender])

                Creatif.instance?.let {
                    object : BukkitRunnable() {
                        override fun run() {
                            teleport(sender, dest)
                        }
                    }.runTaskLater(it, 60)
                }
            })

        val denyButton = LanguageManager["lib.chat.decline", dest]
            .clickEvent(chatEntryService.clickCommand(dest) {
                dest.sendMessage(ChatFormats.CHAT_ERROR + "Demande refusée.")
                sender.sendMessage(ChatFormats.CHAT_ERROR + "Demande refusée.")
                requestsCooldowns.remove(sender.uniqueId)
            })

        val acceptDeny = acceptButton.append(Component.text(" ")).append(denyButton)
        dest.sendMessage(acceptDeny)

        val resetTask = object : TimedBukkitTask() {
            override fun run() {
                requestsCooldowns.remove(sender.uniqueId)
            }
        }
        resetTask.removeInstant = Instant.now()
        Creatif.instance?.let { resetTask.runTaskLater(it, requestsCooldownsDuration.seconds * 20) }
        requestsCooldowns[sender.uniqueId] = resetTask
    }

    fun teleport(sender: Player, dest: Player) {
        sender.teleport(dest.location)
        requestsCooldowns.remove(sender.uniqueId)
        teleportCooldowns[sender.uniqueId] = Instant.now()
        sender.sendMessage(ChatFormats.CHAT_SUCCESS + LanguageManager["creative.tpa_manager.done", dest])
    }

    private open class TimedBukkitTask : BukkitRunnable() {
        var removeInstant: Instant? = null

        override fun run() {
        }
    }
}
