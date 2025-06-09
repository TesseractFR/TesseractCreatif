package onl.tesseract.creative.controller.command

import onl.tesseract.creative.util.CommandsBookFactory
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.SpringComponent
import org.bukkit.entity.Player

@Command(name = "commandes")
@SpringComponent
class CommandsBookCommand(
    provider: CommandInstanceProvider,
) : CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        CommandsBookFactory.Companion.getInstance()
                .giveGuideBook(sender)
        return true
    }
}