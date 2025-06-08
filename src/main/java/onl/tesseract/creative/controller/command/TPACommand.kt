package onl.tesseract.creative.controller.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.CommandInstanceProvider
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.creative.SpringComponent
import onl.tesseract.creative.service.tpa.TpaService
import onl.tesseract.lib.command.argument.PlayerArg
import org.bukkit.entity.Player

@Command(name = "tpa", args = [Argument(value = "joueur", clazz = PlayerArg::class)])
@SpringComponent
class TPACommand(
    provider: CommandInstanceProvider,
    private val tpaService: TpaService,
) : CommandContext(provider) {

    @CommandBody
    fun onCommand(@Env(key = "joueur") target: Player, sender: Player): Boolean {
        return tpaService.tpaRequest(sender, target)
    }
}
@SpringComponent
@Command(name = "tpahere", args = [Argument(value = "joueur", clazz = PlayerArg::class)])
class TPAHereCommand(
    provider: CommandInstanceProvider,
    private val tpaService: TpaService
    ) : CommandContext() {
    @CommandBody
    fun onCommand(@Env(key = "joueur") target: Player, sender: Player): Boolean {
        return tpaService.tpaHereRequest(sender, target)
    }
}
