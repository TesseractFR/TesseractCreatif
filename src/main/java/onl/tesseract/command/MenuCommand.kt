package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.MenuMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class MenuCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        MenuMenu(sender).open(sender)
        return true
    }
}
