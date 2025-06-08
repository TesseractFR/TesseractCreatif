package onl.tesseract.creative.controller.command.home

import onl.tesseract.creative.controller.command.argument.PlayerHomeNameArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.creative.service.home.HomeService
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.append
import org.bukkit.entity.Player
import org.springframework.stereotype.Component

@Command(
    name = "delhome",
    playerOnly = true,
    args = [Argument(value = "nom", clazz = PlayerHomeNameArg::class)])
@Component
class DelhomeCommand(
    provider: CommandInstanceProvider,
    private val homeService: HomeService,
) : CommandContext(provider) {
    @CommandBody
    fun onCommand(@Env(key = "nom") homeName: String, sender: Player) = run {
        if (homeService.exist(sender.uniqueId, homeName)) {
            homeService.deleteHome(sender.uniqueId, homeName)
            sender.sendMessage(ChatFormats.CHAT_SUCCESS.append("Votre home $homeName a bien été supprimé."))
        } else {
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Ce home n'existe pas !"))
        }
    }
}