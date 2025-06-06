package onl.tesseract.command.home

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.home.HomeService
import onl.tesseract.lib.command.argument.StringArg
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.util.ChatFormats
import onl.tesseract.lib.util.append
import org.bukkit.Location
import org.bukkit.entity.Player

@Command(
    name = "sethome",
    playerOnly = true)
class SetHomeCommand: CommandContext() {
    @CommandBody
    fun onCommand(@Argument(value = "nom", clazz = StringArg::class) homeName : String?, sender: Player) = run {
        if(homeName ==null){
            sender.sendMessage(ChatFormats.CHAT_ERROR.append("Vous devez entrer un nom pour le home."))
            return@run
        }
        val homeService = ServiceContainer[HomeService::class.java]
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