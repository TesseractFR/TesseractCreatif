package onl.tesseract.command

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.command.argument.GamemodeArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import org.bukkit.GameMode
import org.bukkit.entity.Player

@Command(
    playerOnly = true,
    name = "gamemode",
    args = [Argument(value = "mode", clazz = GamemodeArg::class)]
)
class GamemodeCommand : CommandContext() {
    @CommandBody
    fun onCommand(@Env(key = "mode") mode: GameMode, sender: Player) {
        if (sender.gameMode == mode) {
            sender.sendMessage(Component.text("Vous êtes déjà en mode ${mode.name}.", NamedTextColor.YELLOW))
            return
        }
        sender.gameMode = mode
        sender.sendMessage(Component.text("Votre mode de jeu a été changé en ${mode.name}.", NamedTextColor.GREEN))
    }
}

