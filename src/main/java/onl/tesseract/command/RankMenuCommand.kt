package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.RankMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class RankMenuCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        RankMenu().open(sender)
        return true
    }
}
