package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import onl.tesseract.creative.controller.menu.boutique.BoutiqueMenu
import org.bukkit.entity.Player

@Command(name = "boutique")
@SpringComponent
class BoutiqueCommand(
    provider: CommandInstanceProvider,
) : CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        BoutiqueMenu(sender).open(sender)
        return true
    }
}