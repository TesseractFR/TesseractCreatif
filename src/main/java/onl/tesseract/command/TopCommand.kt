package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class TopCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            val world = sender.world
            val currentLocation = sender.location

            val highestY = world.getHighestBlockYAt(currentLocation)
            val topLocation = currentLocation.clone().apply {
                y = highestY.toDouble()
                x = blockX + 0.5
                z = blockZ + 0.5

                if (block.type != Material.AIR) {
                    y += 1.0
                }

                pitch = currentLocation.pitch
                yaw = currentLocation.yaw
            }

            sender.teleport(topLocation)
            sender.sendMessage("§aTéléporté(e) au point le plus haut !")
        }
        return true
    }
}
