package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import org.bukkit.entity.Player

@Command(name = "ec", playerOnly = true)
class ECCommand : CommandContext() {

    @CommandBody
    fun onCommand(sender: Player) {
        if (!sender.hasPermission("tesseract.creatif.ec.use")) {
            sender.sendMessage(Component.text("Vous n'avez pas la permission d'ouvrir l'EnderChest.", NamedTextColor.RED))
            return
        }
        sender.openInventory(sender.enderChest)
    }
}
