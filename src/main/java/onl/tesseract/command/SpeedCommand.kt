package onl.tesseract.command

import onl.tesseract.command.argument.SpeedCommandArgument
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import kotlin.math.max
import kotlin.math.min

@Command(
    name = "speed",
    playerOnly = true,
    args = [Argument(value = "speed", clazz = SpeedCommandArgument::class)])
class SpeedCommand : CommandContext() {

    private val defaultFlySpeed: Float = 0.1f

    @CommandBody
    fun onCommand(@Env(key = "speed") speed: Float, sender: Player): Boolean {

        val normalizedSpeed = max(0.1f, min(speed, 10f))

        sender.walkSpeed = (0.2f + (normalizedSpeed - 1f) * (1.0f - 0.2f) / 9f).coerceAtMost(1.0f)
        sender.flySpeed = (defaultFlySpeed * normalizedSpeed).coerceAtMost(1.0f)

        sender.sendMessage("§aVotre vitesse a été réglée à $speed.")

        return true
    }
}
