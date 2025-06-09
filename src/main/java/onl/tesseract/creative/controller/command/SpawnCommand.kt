package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.core.Config
import onl.tesseract.creative.SpringComponent
import org.bukkit.entity.Player

@Command(name = "spawn", playerOnly = true)
@SpringComponent
class SpawnCommand(provider: CommandInstanceProvider) : CommandContext(provider) {

    @CommandBody
    fun onCommand(sender: Player) {
        sender.teleport(Config.invoke().firstSpawnLocation)
    }
}
