package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.TPWorldMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class WorldsCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            TPWorldMenu().open(sender)
        }

        return true
    }
}
