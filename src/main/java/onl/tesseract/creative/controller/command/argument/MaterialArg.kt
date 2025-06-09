package onl.tesseract.creative.controller.command.argument

import onl.tesseract.commandBuilder.CommandArgument
import onl.tesseract.commandBuilder.CommandArgumentBuilderSteps
import org.bukkit.Material

class MaterialArg(name: String) : CommandArgument<Material>(name) {

    override fun define(builder: CommandArgumentBuilderSteps.Parser<Material>) {
        builder.parser { input, _ -> Material.valueOf(input) }

                .tabCompleter { _, _ ->
                    Material.entries.map { mat -> mat.name }
                            .toList()
                }
                .errorHandler(NullPointerException::class.java, "Matériel introuvable")
    }
}