package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import onl.tesseract.lib.command.argument.PlayerArg
import org.bukkit.entity.Player

@Command(
    playerOnly = true,
    name = "tp",
    args = [
        Argument(value = "target", clazz = PlayerArg::class),
        Argument(
            value = "dest",
            clazz = PlayerArg::class,
            optional = true),
    ]
)
class TPCommand : CommandContext() {
    @CommandBody
    fun onCommand(@Env(key = "target") target: Player, @Env(key = "dest") dest: Player?, sender: Player) {
        if (dest != null) {
            if (!sender.hasPermission("tesseract.creatif.tp.other")) {
                sender.sendMessage(
                    Component.text(
                        "Vous n'avez pas la permission de téléporter d'autres joueurs.",
                        NamedTextColor.RED))

                return
            }
            target.teleport(dest.location)
            return
        }
        sender.teleport(target.location);

    }
}