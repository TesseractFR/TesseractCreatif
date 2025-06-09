package onl.tesseract.creative.controller.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import org.bukkit.entity.Player

@Command(name = "ec", playerOnly = true)
@SpringComponent
class ECCommand(provider: CommandInstanceProvider) : CommandContext(provider) {

    @CommandBody
    fun onCommand(sender: Player) {
        if (!sender.hasPermission("tesseract.creatif.ec")) {
            sender.sendMessage(
                Component.text(
                    "Vous n'avez pas la permission d'ouvrir l'EnderChest.",
                    NamedTextColor.RED))
            return
        }
        sender.openInventory(sender.enderChest)
    }
}