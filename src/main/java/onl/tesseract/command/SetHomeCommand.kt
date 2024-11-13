package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.home.HomeService
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.command.argument.StringArg
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.ChatFormats
import onl.tesseract.tesseractlib.util.plus
import org.bukkit.Location
import org.bukkit.entity.Player

@Command
class SetHomeCommand: CommandContext() {
    @CommandBody
    fun onCommand(@Argument(value = "nom", clazz = StringArg::class) home: String?, sender: Player) = run {
        if(home ==null){
            sender.sendMessage(ChatFormats.CHAT.plus("Vous devez entrer un nom pour le home."))
            return@run
        }
        val player: TPlayer = TPlayer.get(sender)
        var homeLoc: Location = sender.location
        if (CreativeServices[HomeService::class.java].canCreateHome(player.uuid)) {
            CreativeServices[HomeService::class.java].createHome(player.uuid, home, homeLoc)
            sender.sendMessage(ChatFormats.CHAT.plus("Votre home $home a bien été créé."))
        }

    }

}