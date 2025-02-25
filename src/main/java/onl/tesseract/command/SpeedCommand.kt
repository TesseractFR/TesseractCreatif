package onl.tesseract.command

import onl.tesseract.command.argument.SpeedCommandArgument
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.CommandBody
import onl.tesseract.commandBuilder.annotation.Env
import org.bukkit.entity.Player
import kotlin.math.max
import kotlin.math.min

@Command(name = "speed", playerOnly = true)
class SpeedCommand : CommandContext() {

    private val defaultWalkSpeed: Float = 0.2f
    private val defaultFlySpeed: Float = 0.1f

    @Command(name = "walk", args = [Argument(value = "speed", clazz = SpeedCommandArgument::class)])
    fun walkSpeedCommand(@Env(key = "speed") speed: Float, sender: Player): Boolean {
        val normalizedSpeed = max(0.1f, min(speed, 10f))
        sender.walkSpeed = (defaultWalkSpeed + (normalizedSpeed - 1f) * (1.0f - defaultWalkSpeed) / 9f)
            .coerceAtMost(1.0f)
        sender.sendMessage("§aVotre vitesse de marche a été réglée à $speed.")
        return true
    }

    @Command(name = "fly", args = [Argument(value = "speed", clazz = SpeedCommandArgument::class)])
    fun flySpeedCommand(@Env(key = "speed") speed: Float, sender: Player): Boolean {
        val normalizedSpeed = max(0.1f, min(speed, 10f))
        sender.flySpeed = (defaultFlySpeed * normalizedSpeed).coerceAtMost(1.0f)
        sender.sendMessage("§aVotre vitesse de vol a été réglée à $speed.")
        return true
    }
}
