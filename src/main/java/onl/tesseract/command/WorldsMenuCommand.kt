package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.TPWorldMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class WorldsMenuCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        TPWorldMenu().open(sender)
        return true
    }
}
