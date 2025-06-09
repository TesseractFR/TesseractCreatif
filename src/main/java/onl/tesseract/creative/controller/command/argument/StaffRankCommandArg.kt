package onl.tesseract.creative.controller.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import onl.tesseract.creative.domain.rank.StaffRank

class StaffRankCommandArg(name: String): CommandArgument<StaffRank>(name) {
    override fun define(builder: CommandArgumentBuilderSteps.Parser<StaffRank>) {
        builder.parser { input, _ -> StaffRank.valueOf(input) }
            .tabCompleter { _, _ -> StaffRank.entries.map { it.name } }
            .errorHandler(IllegalArgumentException::class.java, "Rang invalide")
    }
}