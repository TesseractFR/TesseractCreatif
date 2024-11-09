package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.SocialsMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class SocialsCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            SocialsMenu().open(sender)
        }

        return true
    }
}
