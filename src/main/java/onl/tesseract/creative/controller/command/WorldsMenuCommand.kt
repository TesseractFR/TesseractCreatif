package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.creative.service.world.WorldService
import onl.tesseract.creative.controller.menu.TPWorldMenu
import org.bukkit.entity.Player
import org.springframework.stereotype.Component

@Command(name = "mondes")
@Component
class WorldsMenuCommand(provider: CommandInstanceProvider, private val worldService: WorldService) :
        CommandContext(provider) {
    @CommandBody
    fun onCommand(sender: Player): Boolean {
        TPWorldMenu(worldService).open(sender)
        return true
    }
}
