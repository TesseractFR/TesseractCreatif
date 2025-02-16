package onl.tesseract.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps

class SpeedCommandArgument(name: String) : CommandArgument<Float>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<Float>) {
        builder.parser { input, _ -> input.toFloat() }
                .tabCompleter { _, _ -> listOf("0.2", "0.5", "1", "2", "3", "4", "5", "10", "20", "50", "100") }
                .errorHandler(IllegalArgumentException::class.java, "Chiffre invalide")
    }
}
