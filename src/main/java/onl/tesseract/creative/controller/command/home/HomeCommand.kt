package onl.tesseract.creative.controller.command.home

import onl.tesseract.creative.controller.command.argument.PlayerHomeNameArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.creative.service.home.HomeService
import onl.tesseract.creative.repository.hibernate.home.entity.NoHomeFoundException
import org.springframework.stereotype.Component as SpringComponent
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.append
import org.bukkit.entity.Player


@Command(
    name = "home",
    playerOnly = true,
    args = [Argument(value = "nom", clazz = PlayerHomeNameArg::class)])
@SpringComponent
class HomeCommand(
    provider: CommandInstanceProvider,
    val homeService: HomeService,
) : CommandContext(provider) {
    @CommandBody
    fun onCommand(@Env(key = "nom") homeName: String, sender: Player) = run {
        try {
            sender.teleport(homeService.getHome(sender.uniqueId, homeName).location)
        } catch (e: NoHomeFoundException) {
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Ce home n'existe pas !"))
        }
    }
}