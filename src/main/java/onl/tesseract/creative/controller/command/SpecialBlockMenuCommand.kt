package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import onl.tesseract.creative.controller.menu.SpecialBlockMenu
import org.bukkit.entity.Player

@Command(name = "blocks")
@SpringComponent
class SpecialBlockMenuCommand(provider: CommandInstanceProvider) : CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        SpecialBlockMenu(sender).open(sender)
        return true
    }
}
