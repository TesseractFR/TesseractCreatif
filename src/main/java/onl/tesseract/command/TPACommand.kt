package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.menu.boutique.BoutiqueMenu
import onl.tesseract.tesseractlib.player.TPlayer
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Command
class TPACommand : CommandContext() {

    @CommandBody
    fun onCommand(sender: CommandSender?): Boolean {
        if (sender is Player) {
            }
        return true
    }

    fun askTeleport(sender: Player, dest: Player) {

    }

    fun teleport(sender: Player, dest: Player) {

    }

    fun getCooldown(sender: Player) : Int {
        return 0
    }
}