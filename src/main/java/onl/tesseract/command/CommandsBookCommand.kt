package onl.tesseract.command

import onl.tesseract.CommandsBookFactory.Companion.getInstance
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class CommandsBookCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        getInstance().giveGuideBook(sender)
        return true
    }
}
