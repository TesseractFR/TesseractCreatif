package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.Creatif
import onl.tesseract.command.argument.OnlinePlayerArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormat
import onl.tesseract.tesseractlib.util.append
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.time.Duration
import java.time.Instant
import java.util.*
import javax.annotation.Nonnull


@Command(
    name = "tpa",
    playerOnly = true,
    args = [Argument(value = "joueur", clazz = OnlinePlayerArg::class)]
)
class TPACommand : CommandContext() {
    private val requests = mutableMapOf<UUID, TimedBukkitTask>()

    @CommandBody
    fun onCommand(@Nonnull sender: CommandSender, @Nonnull args: Array<String>?): Boolean {
        if (sender !is Player) return false
        val player: Player = sender

        if (args.isNullOrEmpty()) {
            player.sendMessage(ChatFormat.CHAT_ERROR + "Veuillez spécifier un joueur pour la téléportation.")
            return false
        }

        if (requests.containsKey(sender.uniqueId)) {
            val duration: Duration = Duration.between(Instant.now(), requests[player.uniqueId]?.removeInstant ?: Instant.now())
            val secondsLeft: Long = duration.toSeconds()

            if (secondsLeft > 0) {
                if (secondsLeft >= 30) {
                    player.sendMessage(ChatFormat.CHAT_ERROR + "Vous devez attendre encore " + secondsLeft + " secondes avant de vous téléporter de nouveau.")
                }
                return true
            }
        }

        val dest: Player? = Bukkit.getPlayer(args[0])
        if (dest != null && player.uniqueId != dest.uniqueId) {
            askTeleport(player, dest)
        } else  {
            player.sendMessage(ChatFormat.CHAT_ERROR + "Joueur invalide.")
        }
        return true
    }

    fun askTeleport(sender: Player, dest: Player) {
        sender.sendMessage(ChatFormat.CHAT + " Demande envoyée !")
        dest.sendMessage(ChatFormat.CHAT + sender.name + " souhaite se téléporter vers votre position.")

        val acceptDeny = Component.text("[Accepter]", NamedTextColor.GREEN)
            .clickEvent(ClickEvent.runCommand("/command accept ${sender.uniqueId}"))
            .hoverEvent(HoverEvent.showText(Component.text("Cliquez pour accepter la demande")))
            .append(" ")
            .append("[Refuser]", NamedTextColor.RED)
            .clickEvent(ClickEvent.runCommand("/commande deny ${sender.uniqueId}"))
            .hoverEvent(HoverEvent.showText(Component.text("Cliquez pour refuser la demande")))

        dest.sendMessage(acceptDeny)

        TPlayer.get(dest).getChatCommand { response ->
            if (response.isNotEmpty() && response[0] == "accept") {
                dest.sendMessage(ChatFormat.CHAT + "Demande acceptée.")
                sender.sendMessage(ChatFormat.CHAT + "Demande de téléportation acceptée. Préparez-vous à être téléporté...")

                Creatif.instance?.let {
                    object : BukkitRunnable() {
                        override fun run() {
                            teleport(sender, dest)
                        }
                    }.runTaskLater(it, 40)
                }
            }
            else if (response.isNotEmpty() && response[0] == "deny") {
                dest.sendMessage(ChatFormat.CHAT_ERROR + "Demande refusée.")
                sender.sendMessage(ChatFormat.CHAT_ERROR + "Demande refusée.")
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
