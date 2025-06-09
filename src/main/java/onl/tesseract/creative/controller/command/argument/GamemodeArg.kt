package onl.tesseract.creative.controller.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import org.bukkit.GameMode

class GamemodeArg(name: String) : CommandArgument<GameMode>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<GameMode>) {
        builder.parser { input, _ ->
            when (input.lowercase()) {
                "0", "s", "survival" -> GameMode.SURVIVAL
                "1", "c", "creative" -> GameMode.CREATIVE
                "2", "a", "adventure" -> GameMode.ADVENTURE
                "3", "sp", "spectator" -> GameMode.SPECTATOR
                else -> throw IllegalArgumentException("Mode de jeu invalide")
            }
        }
            .tabCompleter { input, _ ->
                listOf("survival", "creative", "adventure", "spectator").filter { it.startsWith(input.lowercase()) }
            }
            .errorHandler(IllegalArgumentException::class.java, "Mode de jeu invalide")
    }
}
