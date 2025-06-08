package onl.tesseract.creative.controller.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Command(name = "hat", playerOnly = true)
@SpringComponent
class HatCommand(provider: CommandInstanceProvider) : CommandContext(provider) {

    @CommandBody
    fun onCommand(sender: Player) {
        if (!sender.hasPermission("tesseract.creatif.hat")) {
            sender.sendMessage(
                Component.text(
                    "Vous n'avez pas la permission d'utiliser cette commande.",
                    NamedTextColor.RED))
            return
        }

        val itemInHand: ItemStack = sender.inventory.itemInMainHand
        val helmet: ItemStack? = sender.inventory.helmet

        if (helmet != null && !helmet.type.isAir) {
            sender.sendMessage(Component.text("Vous avez déjà un chapeau sur la tête.", NamedTextColor.RED))
            return
        }
        if (itemInHand.type.isAir) {
            sender.sendMessage(
                Component.text(
                    "Prenez un objet en main pour l'équiper sur votre tête !",
                    NamedTextColor.RED))
            return
        }
        sender.inventory.helmet = itemInHand
        sender.inventory.setItemInMainHand(null)
        sender.sendMessage(Component.text("Bravo, vous avez un nouveau chapeau magnifique !", NamedTextColor.GREEN))
    }
}