package onl.tesseract.command.staff

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import onl.tesseract.command.argument.TimeStringArg
import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Argument
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.service.CreativeServices
import onl.tesseract.tesseractlib.command.argument.IntegerCommandArgument
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import onl.tesseract.tesseractlib.command.argument.PlayerArg
import onl.tesseract.timeplayed.PlayerTimePlayedService
import java.time.Duration

@Command(name = "time")
class TimeCommand : CommandContext() {

    private val timePlayed = CreativeServices[PlayerTimePlayedService::class.java]

    @Command
    fun setPlayedTime(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "unit", clazz = TimeStringArg::class) unit: String,
        @Argument(value = "value", clazz = IntegerCommandArgument::class) value: Int,
        sender: CommandSender
    ) {
        val totalSeconds = convertToSeconds(unit, value)
        if (totalSeconds != -1) {
            timePlayed.setPlayerTimePlayed(player.uniqueId, totalSeconds.toLong())
            sender.sendMessage(Component.text("Le temps de jeu de ${player.name} a été défini à $value $unit(s).", NamedTextColor.YELLOW))
        } else {
            sender.sendMessage(Component.text("Unité de temps invalide. Utilisez day, month, second, minute, hour.", NamedTextColor.RED))
        }
    }

    @Command
    fun addPlayedTime(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "unit", clazz = TimeStringArg::class) unit: String,
        @Argument(value = "value", clazz = IntegerCommandArgument::class) value: Int,
        sender: CommandSender
    ) {
        val totalSeconds = convertToSeconds(unit, value)
        if (totalSeconds != -1) {
            timePlayed.addPlayerTimePlayed(player.uniqueId, totalSeconds.toLong())
            sender.sendMessage(Component.text("Le temps de jeu de ${player.name} a été augmenté de $value $unit(s).", NamedTextColor.YELLOW))
        } else {
            sender.sendMessage(Component.text("Unité de temps invalide. Utilisez day, month, second, minute, hour.", NamedTextColor.RED))
        }
    }

    @Command
    fun removePlayedTime(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        @Argument(value = "unit", clazz = TimeStringArg::class) unit: String,
        @Argument(value = "value", clazz = IntegerCommandArgument::class) value: Int,
        sender: CommandSender
    ) {
        val totalSeconds = convertToSeconds(unit, value)
        if (totalSeconds != -1) {
            val currentTimePlayed = timePlayed.getPlayerTimePlayed(player.uniqueId)
            if (totalSeconds > currentTimePlayed.seconds) {
                sender.sendMessage(Component.text("Erreur : Vous ne pouvez pas retirer plus de temps que ce qui a été joué (${formatChat(currentTimePlayed)}).", NamedTextColor.RED))
            } else {
                timePlayed.addPlayerTimePlayed(player.uniqueId, - totalSeconds.toLong())
                sender.sendMessage(Component.text("Le temps de jeu de ${player.name} a été réduit de $value $unit(s).", NamedTextColor.YELLOW))
            }
        } else {
            sender.sendMessage(Component.text("Unité de temps invalide. Utilisez day, month, second, minute, hour.", NamedTextColor.RED))
        }
    }

    @Command
    fun getTimePlayed(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        sender: CommandSender
    ) {
        val timePlayed = timePlayed.getPlayerTimePlayed(player.uniqueId)
        val timePlayedChat = formatChat(timePlayed)
        sender.sendMessage(Component.text("Le temps de jeu de ${player.name} est de $timePlayedChat.", NamedTextColor.YELLOW))
    }

    @Command
    fun resetTimePlayed(
        @Argument(value = "player", clazz = PlayerArg::class) player: Player,
        sender: CommandSender
    ) {
        timePlayed.setPlayerTimePlayed(player.uniqueId, 0)
        sender.sendMessage(Component.text("Le temps de jeu de ${player.name} a été réinitialisé.", NamedTextColor.YELLOW))
    }

    private fun formatChat(duration: Duration): String {
        val days = duration.toDays()
        val hours = duration.toHours() % 24
        val minutes = duration.toMinutes() % 60
        val seconds = duration.seconds % 60
        val parts = mutableListOf<String>()
        if (days > 0) parts.add("$days day${if (days > 1) "s" else ""}")
        if (hours > 0) parts.add("$hours hour${if (hours > 1) "s" else ""}")
        if (minutes > 0) parts.add("$minutes minute${if (minutes > 1) "s" else ""}")
        if (seconds > 0) parts.add("$seconds second${if (seconds > 1) "s" else ""}")
        return parts.joinToString(" ")
    }

    private fun convertToSeconds(unit: String, value: Int): Int {
        return when (unit.lowercase()) {
            "second", "seconds" -> value
            "minute", "minutes" -> value * 60
            "hour", "hours" -> value * 3600
            "day", "days" -> value * 86400
            "month", "months" -> value * 2592000
            else -> -1
        }
    }
}
