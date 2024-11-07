package onl.tesseract.command

import onl.tesseract.CommandsBookFactory.Companion.getInstance
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class BasicCommandsCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            getInstance().giveGuideBook(sender)
        }
        return true
    }
}
