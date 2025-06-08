package onl.tesseract.creative.controller.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import onl.tesseract.creative.PLUGIN_INSTANCE
import onl.tesseract.creative.service.home.HomeService


class PlayerHomeNameArg(name: String) : CommandArgument<String>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<String>) {
        builder.parser { input, _ -> input }

            .tabCompleter { _, env ->
                homeService().getAllHomes(env.senderAsPlayer.uniqueId)
                        .map { it.name }
                        .toList()
            }
            .errorHandler(NullPointerException::class.java, "Home introuvable")
    }

    private fun homeService(): HomeService =
        PLUGIN_INSTANCE.springContext.getBean(HomeService::class.java)
}