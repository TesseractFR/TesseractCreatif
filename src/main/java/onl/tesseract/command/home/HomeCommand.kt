package onl.tesseract.command.home

import onl.tesseract.command.argument.PlayerHomeNameArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.home.HomeService
import onl.tesseract.home.entity.NoHomeFoundException
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.append
import org.bukkit.entity.Player


@Command(
    name = "home",
    playerOnly = true,
    args = [Argument(value = "nom", clazz = PlayerHomeNameArg::class)])
class HomeCommand : CommandContext() {
    @CommandBody
    fun onCommand(@Env(key = "nom") homeName: String, sender: Player) = run {
        val homeService = ServiceContainer[HomeService::class.java]
        try{
            sender.teleport(homeService.getHome(sender.uniqueId,homeName)!!);
        } catch (e:NoHomeFoundException) {
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Ce home n'existe pas !"))
        }
    }
}