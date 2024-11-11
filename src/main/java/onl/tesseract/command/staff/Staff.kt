package onl.tesseract.command.staff

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.Perm


@Command(
    name = "staff",
    permission = Perm(value = "tesseract.staff", mode = Perm.Mode.AUTO),
    subCommands = [
        RankCommand::class
    ]
)
class Staff : CommandContext()
