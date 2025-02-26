package onl.tesseract.core.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.player.PlayerTeleportEvent
import java.util.*

@Command
class BackCommand : CommandContext(), Listener {

    companion object {
        val lastLocations = mutableMapOf<UUID, Location>()
    }

    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            val lastLocation = lastLocations[sender.uniqueId]
            if (lastLocation != null) {
                sender.teleport(lastLocation)
                sender.sendMessage("§aTéléporté(e) à la dernière position !")
            } else {
                sender.sendMessage("§cAucune position précédente enregistrée.")
            }
        }
        return true
    }

    @EventHandler
    fun onPlayerTeleport(event: PlayerTeleportEvent) {
            lastLocations[event.player.uniqueId] = event.from
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        lastLocations.remove(event.player.uniqueId)
    }
}
