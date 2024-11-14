package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.home.HomeService
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.command.argument.StringArg
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.append
import org.bukkit.Location
import org.bukkit.entity.Player

@Command
class SetHomeCommand: CommandContext() {
    @CommandBody
    fun onCommand(@Argument(value = "nom", clazz = StringArg::class) homeName : String?, sender: Player) = run {
        if(homeName ==null){
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Vous devez entrer un nom pour le home."))
            return@run
        }
        val homeService = CreativeServices[HomeService::class.java]
        if (homeService.exist(sender.uniqueId, homeName)) {
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Le home $homeName existe déjà."))
            return
        }
        val homeLoc: Location = sender.location
        if (homeService.canCreateHome(sender.uniqueId)) {
            homeService.createHome(sender.uniqueId, homeName, homeLoc)
            sender.sendMessage(ChatFormats.CHAT_SUCCESS.append("Votre home $homeName a bien été créé."))
        }
    }

}