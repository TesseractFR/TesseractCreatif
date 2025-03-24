package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.boutique.BoutiqueMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class BoutiqueCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        BoutiqueMenu(sender).open(sender)
        return true
    }
}
