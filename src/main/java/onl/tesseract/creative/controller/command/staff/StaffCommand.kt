package onl.tesseract.creative.controller.command.staff

import onl.tesseract.commandBuilder.CommandContext
import onl.tesseract.commandBuilder.annotation.Command
import onl.tesseract.commandBuilder.annotation.Perm
import onl.tesseract.creative.CreativeCommandInstanceProvider
import onl.tesseract.creative.SpringComponent


@Command(
    name = "staff",
    permission = Perm(value = "tesseract.staff", mode = Perm.Mode.AUTO),
    subCommands = [
        RankCommand::class,
        TimeCommand::class
    ]
)
class StaffCommand(commandInstanceProvider: CreativeCommandInstanceProvider) :
        CommandContext(commandInstanceProvider) {}
