package onl.tesseract.command;

import onl.tesseract.CommandsBookFactory;
import onl.tesseract.commandBuilder.CommandContext;
import onl.tesseract.commandBuilder.annotation.Command;
import onl.tesseract.commandBuilder.annotation.CommandBody;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command
public class BasicCommandsCommand extends CommandContext {
    @CommandBody
    public boolean onCommand(CommandSender sender)
    {
        if (sender instanceof Player player )
        {
            CommandsBookFactory.getInstance().giveGuideBook(player);
        }
        return true;
    }
}
