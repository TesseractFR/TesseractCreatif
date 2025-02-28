package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.core.Config
import org.bukkit.entity.Player

@Command(name = "spawn", playerOnly = true)
class SpawnCommand : CommandContext() {

    @CommandBody
    fun onCommand(sender: Player) {
        sender.teleport(Config.invoke().firstSpawnLocation)
    }
}
