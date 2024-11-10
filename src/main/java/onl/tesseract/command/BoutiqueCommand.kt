package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.BoutiqueMenu
import onl.tesseract.tesseractlib.player.TPlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class BoutiqueCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            BoutiqueMenu(TPlayer.get(sender)).open(sender)
        }

        return true
    }
}
