package onl.tesseract.command;

import onl.tesseract.CreativePlayer;
import onl.tesseract.commandBuilder.CommandContext;
import onl.tesseract.commandBuilder.annotation.Command;
import onl.tesseract.commandBuilder.annotation.CommandBody;
import onl.tesseract.menu.MenuMenu;
import onl.tesseract.menu.RankMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command
public class RankCommand extends CommandContext {
    @CommandBody
    public boolean onCommand(CommandSender sender)
    {
        if (sender instanceof Player player )
        {
            new RankMenu().open(player);
        }

        return true;
    }
}
