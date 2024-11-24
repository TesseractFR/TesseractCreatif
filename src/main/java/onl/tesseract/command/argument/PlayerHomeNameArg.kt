package onl.tesseract.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import onl.tesseract.home.HomeService
import onl.tesseract.lib.service.ServiceContainer

class PlayerHomeNameArg(name: String) : CommandArgument<String>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<String>) {
        builder.parser { input, _ -> input }

            .tabCompleter { _, env ->
                ServiceContainer[HomeService::class.java].getAllHomes(env.senderAsPlayer.uniqueId)
                        .toList()
            }
            .errorHandler(NullPointerException::class.java, "Home introuvable")
    }
}