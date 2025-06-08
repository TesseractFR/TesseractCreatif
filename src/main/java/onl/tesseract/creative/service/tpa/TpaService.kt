package onl.tesseract.creative.service.tpa

import net.kyori.adventure.text.Component
import onl.tesseract.creative.PLUGIN_INSTANCE
import onl.tesseract.creative.util.DurationFormat
import onl.tesseract.lib.chat.ChatEntryService

import onl.tesseract.lib.translation.LanguageManager
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.plus
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.springframework.stereotype.Service
import java.time.Duration
import java.time.Instant
import java.util.UUID

@Service
class TpaService(
    private val chatEntryService: ChatEntryService,
) {
    private val teleportCooldowns = mutableMapOf<UUID, Instant>()
    private val requestsCooldowns = mutableMapOf<UUID, TimedBukkitTask>()
    private val teleportCooldownDuration = Duration.ofSeconds(30)
    private val requestsCooldownsDuration = Duration.ofMinutes(2)

    fun tpaRequest(sender: Player, dest: Player): Boolean {
        return handleTeleportRequest(sender, dest, isTpaHere = false)
    }

    fun tpaHereRequest(sender: Player, dest: Player): Boolean {
        return handleTeleportRequest(sender, dest, isTpaHere = true)
    }

    private fun handleTeleportRequest(
        sender: Player,
        dest: Player,
        isTpaHere: Boolean
    ): Boolean {
        if (dest.name.isEmpty()) {
            sender.sendMessage(ChatFormats.CHAT_ERROR + LanguageManager["creative.tpa_manager.no_player", sender])
            return false
        }

        if (isOnCooldown(teleportCooldowns[sender.uniqueId], teleportCooldownDuration, sender)) return true
        if (isOnCooldown(requestsCooldowns[sender.uniqueId]?.removeInstant, requestsCooldownsDuration, sender)) return true

        askTeleport(sender, dest, isTpaHere)
        return true
    }

    private fun isOnCooldown(
        lastUse: Instant?,
        duration: Duration,
        sender: Player
    ): Boolean {
        if (lastUse != null && Duration.between(lastUse, Instant.now()) < duration) {
            val remaining = duration.minus(Duration.between(lastUse, Instant.now()))
            sender.sendMessage(
                ChatFormats.CHAT_ERROR + LanguageManager["creative.tpa_manager.on_cooldown", mapOf("duration" to formatTime(remaining)), sender]
            )
            return true
        }
        return false
    }


    private fun askTeleport(sender: Player, dest: Player, isTpaHere: Boolean) {
        val sendKey = "creative.tpa_manager.send"
        val askKey = if (isTpaHere) "creative.tpa_manager.ask_here" else "creative.tpa_manager.ask"
        val destAcceptKey = if (isTpaHere) "creative.tpa_manager.dest_accept_here" else "creative.tpa_manager.dest_accept"
        val srcAcceptKey = if (isTpaHere) "creative.tpa_manager.src_accept_here" else "creative.tpa_manager.src_accept"
        val doneKey = if (isTpaHere) "creative.tpa_manager.done_here" else "creative.tpa_manager.done"

        sender.sendMessage(ChatFormats.CHAT + LanguageManager[sendKey, sender])
        dest.sendMessage(ChatFormats.CHAT + LanguageManager[askKey, mapOf("player" to sender.displayName()), dest])val acceptButton = LanguageManager["lib.chat.accept", dest]
                .clickEvent(
                    chatEntryService.clickCommand(dest) {
                        dest.sendMessage(ChatFormats.CHAT_SUCCESS + LanguageManager[destAcceptKey, mapOf("player" to sender.displayName()), dest])
                        sender.sendMessage(ChatFormats.CHAT_SUCCESS + LanguageManager[srcAcceptKey, sender])

                        PLUGIN_INSTANCE?.let {
                            object : BukkitRunnable() {
                                override fun run() {
                                    if (isTpaHere) {
                            dest.teleport(sender.location)
                        } else {
                            sender.teleport(dest.location)
                        }
                        requestsCooldowns.remove(sender.uniqueId)
                        teleportCooldowns[sender.uniqueId] = Instant.now()
                        sender.sendMessage(ChatFormats.CHAT_SUCCESS + LanguageManager[doneKey, dest])
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

        dest.sendMessage(acceptButton.append(Component.text(" "))
                .append(denyButton))

        val resetTask = object : TimedBukkitTask() {
            override fun run() {
                requestsCooldowns.remove(sender.uniqueId)
            }
        }
        resetTask.removeInstant = Instant.now()
        PLUGIN_INSTANCE.let { resetTask.runTaskLater(it, requestsCooldownsDuration.seconds * 20) }
        requestsCooldowns[sender.uniqueId] = resetTask
    }

    private open class TimedBukkitTask : BukkitRunnable() {
        var removeInstant: Instant? = null

        override fun run() {
            // Rien dedans (juste pour Sonar)
        }
    }
}