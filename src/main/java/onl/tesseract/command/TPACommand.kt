package onl.tesseract.command

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.tesseractlib.command.argument.PlayerArg
import org.bukkit.entity.Player

@Command(
    name = "tpa",
    args = [Argument(value = "joueur", clazz = PlayerArg::class)]
)
class TPACommand : CommandContext() {
    private val tpaManager = TPAManager()

    @CommandBody
    fun onCommand(@Env(key = "joueur") dest: Player, sender: Player): Boolean {
        return tpaManager.tpaRequest(sender, dest)
    }
}
