package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.PluginsToolsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class PluginsToolsMenuCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        PluginsToolsMenu().open(sender)
        return true
    }
}
