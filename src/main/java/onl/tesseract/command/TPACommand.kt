package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
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
import org.bukkit.Bukkit
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
    private val requests = mutableMapOf<UUID, TimedBukkitTask>()

    @CommandBody
    fun onCommand(@Env(key = "joueur") dest: Player, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        val player: Player = sender

        println("Sender : $player")
        println("dest : $dest")
        if (dest.name.isEmpty()) {
            player.sendMessage(ChatFormats.CHAT_ERROR + "Veuillez spécifier un joueur pour la téléportation.")
            return false
        }

        if (requests.containsKey(sender.uniqueId)) {
            val duration: Duration =
                Duration.between(Instant.now(), requests[player.uniqueId]?.removeInstant ?: Instant.now())
            val secondsLeft: Long = duration.toSeconds()

            if (secondsLeft > 0) {
                if (secondsLeft >= 30) {
                    player.sendMessage(ChatFormats.CHAT_ERROR + "Vous devez attendre encore $secondsLeft secondes avant de vous téléporter de nouveau.")
                }
                return true
            }
        }

        if (player.uniqueId != dest.uniqueId) {
            askTeleport(player, dest)
        } else {
            player.sendMessage(ChatFormats.CHAT_ERROR + "Joueur invalide.")
        }
        return true
    }

    fun askTeleport(sender: Player, dest: Player) {
        sender.sendMessage(ChatFormats.CHAT + " Demande envoyée !")
        dest.sendMessage(ChatFormats.CHAT + sender.name + " souhaite se téléporter vers votre position.")

        val acceptButton = Component.text("[Accepter]", NamedTextColor.GREEN)
            .clickEvent(ClickEvent.runCommand("/command accept ${sender.uniqueId}"))

        val denyButton = Component.text("[Refuser]", NamedTextColor.RED)
            .clickEvent(ClickEvent.runCommand("/command deny ${sender.uniqueId}"))

        val acceptDeny = acceptButton.append(" ").append(denyButton)
        dest.sendMessage(acceptDeny)

        TPlayer.get(dest).getChatCommand { response ->
            println("Réponse : ${response.joinToString(", ")}")
            if (response.isNotEmpty() && response[0] == "accept") {
                dest.sendMessage(ChatFormats.CHAT_SUCCESS + "Demande acceptée.")
                sender.sendMessage(ChatFormats.CHAT_SUCCESS + "Demande de téléportation acceptée. Préparez-vous à être téléporté...")

                Creatif.instance?.let {
                    object : BukkitRunnable() {
                        override fun run() {
                            teleport(sender, dest)
                        }
                    }.runTaskLater(it, 40)
                }
            } else if (response.isNotEmpty() && response[0] == "deny") {
                dest.sendMessage(ChatFormats.CHAT_ERROR + "Demande refusée.")
                sender.sendMessage(ChatFormats.CHAT_ERROR + "Demande refusée.")
            }
        }
    }

    fun teleport(sender: Player, dest: Player) {
        val cooldown = 30
        sender.teleport(dest.location)

        val request = object : TimedBukkitTask() {
            override fun run() {
                requests.remove(sender.uniqueId)
            }
        }
        request.removeInstant = Instant.ofEpochMilli(Instant.now().toEpochMilli() + cooldown * 50)
        Creatif.instance?.let { request.runTaskLater(it, cooldown.toLong()) }
        requests[sender.uniqueId] = request
    }

    private open class TimedBukkitTask : BukkitRunnable() {
        var removeInstant: Instant = Instant.now()

        override fun run() {
        }
    }
}
