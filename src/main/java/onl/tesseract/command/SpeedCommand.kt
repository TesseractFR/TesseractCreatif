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

    private val defaultValue: Float = 0.1F

    @CommandBody
    fun onCommand(@Env(key = "speed") speed: Float, sender: Player): Boolean {

        val speedRatio: Float = (defaultValue * max(0f, min(speed, 100f)))
        sender.registerAttribute(Attribute.GENERIC_FLYING_SPEED)
        sender.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.baseValue = speedRatio.toDouble()
        sender.getAttribute(Attribute.GENERIC_FLYING_SPEED)!!.baseValue = speedRatio.toDouble()
        return true
    }

}