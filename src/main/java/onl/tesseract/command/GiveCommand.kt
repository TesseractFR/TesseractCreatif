package onl.tesseract.command

import onl.tesseract.command.argument.MaterialArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@Command(
    playerOnly = true, name = "give", description = "Give item to player",
    args = [Argument(value = "material", clazz = MaterialArg::class)])
class GiveCommand : CommandContext() {
    @CommandBody
    fun onCommand(@Env(key = "material") material: Material, sender: Player) {
        sender.inventory.addItem(ItemStack(material))

    }
}