package onl.tesseract.command

import onl.tesseract.command.argument.PlayerHomeNameArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.home.HomeService
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.append
import org.bukkit.entity.Player

@Command
class DelhomeCommand : CommandContext() {
    @CommandBody
    fun onCommand(@Argument(value = "nom", clazz = PlayerHomeNameArg::class) homeName: String?, sender: Player) = run {
        if (homeName == null) {
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Vous devez entrer le nom du home."))
            return@run
        }
        val homeService = CreativeServices[HomeService::class.java]
        if (homeService.exist(sender.uniqueId, homeName)) {
            homeService.deleteHome(sender.uniqueId, homeName)
            sender.sendMessage(ChatFormats.CHAT_SUCCESS.append("Votre home $homeName a bien été supprimé."))
        } else {
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Ce home n'existe pas !"))
        }
    }
}