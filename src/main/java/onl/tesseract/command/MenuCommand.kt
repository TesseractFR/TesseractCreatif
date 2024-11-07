package onl.tesseract.command

import onl.tesseract.CreativePlayer
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.MenuMenu
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class MenuCommand : CommandContext() {
    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            val creativePlayer = CreativePlayer.get(sender)
            if (creativePlayer is CreativePlayer) {
                MenuMenu(creativePlayer).open(sender)
            }
        }
        return true
    }
}
