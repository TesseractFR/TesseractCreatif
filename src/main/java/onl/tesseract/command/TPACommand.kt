package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.Creatif
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.tesseractlib.command.argument.PlayerArg
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.plus
import onl.tesseract.util.DurationFormat.formatTime
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration
import java.time.Instant
import java.util.*

@Command(
    name = "tpa",
    args = [Argument(value = "joueur", clazz = PlayerArg::class)]
)
class TPACommand : CommandContext() {
    private val teleportCooldowns = mutableMapOf<UUID, Instant>()
    private val requestsCooldowns = mutableMapOf<UUID, TimedBukkitTask>()
    private val teleportCooldownDuration = Duration.ofSeconds(30)
    private val requestsCooldownsDuration = Duration.ofMinutes(2)

    @CommandBody
    fun onCommand(@Env(key = "joueur") dest: Player, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        val player: Player = sender

        if (dest.name.isEmpty()) {
            player.sendMessage(ChatFormats.CHAT_ERROR + "Veuillez spécifier un joueur pour la téléportation.")
            return false
        }

        val lastTeleport = teleportCooldowns[player.uniqueId]
        if (lastTeleport != null && Duration.between(lastTeleport, Instant.now()).seconds < teleportCooldownDuration.seconds) {
            val duration = teleportCooldownDuration.minus(Duration.between(lastTeleport, Instant.now()))
            val durationFormatted = formatTime(duration)
            player.sendMessage(ChatFormats.CHAT_ERROR + "Vous devez attendre encore $durationFormatted avant une nouvelle téléportation.")
            return true
        }

        val requestCooldownTask = requestsCooldowns[player.uniqueId]
        if (requestCooldownTask != null) {
            val lastRequest = requestCooldownTask.removeInstant
            if (lastRequest != null && Duration.between(lastRequest, Instant.now()) < requestsCooldownsDuration) {
                val remainingTime = requestsCooldownsDuration.minus(Duration.between(lastRequest, Instant.now()))
                val remainingTimeFormatted = formatTime(remainingTime)
                player.sendMessage(ChatFormats.CHAT_ERROR + "Vous avec une demande en attente, vous devez attendre encore ${remainingTimeFormatted} avant de refaire une demande.")
                return true
            }
        }

        //if (player.uniqueId != dest.uniqueId) {
            askTeleport(player, dest)
        //} else {
        //    player.sendMessage(ChatFormats.CHAT_ERROR + "Joueur invalide.")
        //}

        return true
    }

    private fun askTeleport(sender: Player, dest: Player) {
        sender.sendMessage(ChatFormats.CHAT + " Demande envoyée !")
        dest.sendMessage(ChatFormats.CHAT + sender.name + " souhaite se téléporter vers votre position.")

        val acceptButton = Component.text("[Accepter]", NamedTextColor.GREEN)
            .clickEvent(ClickEvent.runCommand("/command accept ${sender.uniqueId}"))

        val denyButton = Component.text("[Refuser]", NamedTextColor.RED)
            .clickEvent(ClickEvent.runCommand("/command deny ${sender.uniqueId}"))

        val acceptDeny = acceptButton.append(" ").append(denyButton)
        dest.sendMessage(acceptDeny)

        TPlayer.get(dest).getChatCommand { response ->
            requestsCooldowns[sender.uniqueId]?.cancel()
            requestsCooldowns.remove(sender.uniqueId)
            if (response.isNotEmpty() && response[0] == "accept") {
                dest.sendMessage(ChatFormats.CHAT_SUCCESS + "Demande acceptée.")
                sender.sendMessage(ChatFormats.CHAT_SUCCESS + "Demande de téléportation acceptée. Préparez-vous à être téléporté...")

                Creatif.instance?.let {
                    object : BukkitRunnable() {
                        override fun run() {
                            teleport(sender, dest)
                        }
                    }.runTaskLater(it, 60)
                }
            } else if (response.isNotEmpty() && response[0] == "deny") {
                dest.sendMessage(ChatFormats.CHAT_ERROR + "Demande refusée.")
                sender.sendMessage(ChatFormats.CHAT_ERROR + "Demande refusée.")
            }
        }

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
        sender.sendMessage(ChatFormats.CHAT_SUCCESS + "Vous avez été téléporté avec succès ! Vous devrez attendre 30s avant une nouvelle téléportation.")
    }

    private open class TimedBukkitTask : BukkitRunnable() {
        var removeInstant: Instant? = null

        override fun run() {
        }
    }
}
