package onl.tesseract.command

import onl.tesseract.command.argument.PlayerHomeNameArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.home.HomeService
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.plus
import org.bukkit.entity.Player

@Command
class DelhomeCommand : CommandContext() {
    @CommandBody
    fun onCommand(@Argument(value = "nom", clazz = PlayerHomeNameArg::class) home: String?, sender: Player) = run {
        if(home ==null){
            sender.sendMessage(ChatFormats.CHAT.plus("Vous devez entrer le nom du home."))
            return@run
        }
        val player: TPlayer = TPlayer.get(sender)
        CreativeServices[HomeService::class.java].deleteHome(player.uuid, home)
        sender.sendMessage(ChatFormats.CHAT.plus("Votre home $home a bien été supprimé."))
    }
}
// BOUCLE IF A FAIRE AVEC LA FUTURE METHODE .exist(player, home)