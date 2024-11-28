package onl.tesseract.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import onl.tesseract.rank.entity.PlayerRank

class PlayerRankCommandArg(name: String) : CommandArgument<PlayerRank>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<PlayerRank>) {
        builder.parser { input, _ -> PlayerRank.valueOf(input) }
            .tabCompleter { _, _ -> PlayerRank.entries.map { it.name } }
            .errorHandler(IllegalArgumentException::class.java, "Rang invalide")
    }
}
