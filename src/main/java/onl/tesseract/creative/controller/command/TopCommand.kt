package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import org.bukkit.Material
import org.bukkit.entity.Player

@Command(name = "top")
@SpringComponent
class TopCommand(provider: CommandInstanceProvider) : CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player) {
            val highestY = sender.world.getHighestBlockYAt(sender.location)
            val topLocation = sender.location.clone().apply {
                y = highestY.toDouble()
                x = blockX + 0.5
                z = blockZ + 0.5

                if (block.type != Material.AIR) {
                    y += 1.0
                }
                pitch = sender.location.pitch
                yaw = sender.location.yaw
            }
            sender.teleport(topLocation)
            sender.sendMessage("§aTéléporté(e) au point le plus haut !")
    }
}
