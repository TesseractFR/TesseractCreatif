package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.PluginsToolsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class BuildToolsCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            PluginsToolsMenu().open(sender)
        }

        return true
    }
}
