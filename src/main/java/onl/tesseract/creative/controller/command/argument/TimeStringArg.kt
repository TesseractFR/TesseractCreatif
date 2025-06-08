package onl.tesseract.creative.controller.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps

class TimeStringArg(name: String) : CommandArgument<String>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<String>) {
        builder.parser { input, _ -> input }
            .tabCompleter { input, _ ->
                val timeUnits = listOf("second", "minute", "hour", "day", "month")
                timeUnits.filter { it.startsWith(input.lowercase()) }
            }
    }
}
