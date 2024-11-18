package onl.tesseract.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import org.bukkit.Bukkit

class OnlinePlayerArg(name: String) : CommandArgument<String>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<String>) {
        builder.parser { input, _ -> input }
            .tabCompleter { _, _ ->
                val onlinePlayers = Bukkit.getOnlinePlayers().map { it.name }.toList()
                return@tabCompleter onlinePlayers
            }
            .errorHandler(NullPointerException::class.java, "Joueur introuvable")
    }
}
