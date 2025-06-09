package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import onl.tesseract.creative.controller.menu.PluginsToolsMenu
import org.bukkit.entity.Player

@Command(name = "outils")
@SpringComponent
class PluginsToolsMenuCommand(provider: CommandInstanceProvider) : CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        PluginsToolsMenu().open(sender)
        return true
    }
}