package onl.tesseract.command;

import onl.tesseract.commandBuilder.CommandContext;
import onl.tesseract.commandBuilder.annotation.Command;
import onl.tesseract.commandBuilder.annotation.CommandBody;
import onl.tesseract.menu.MenuMenu;
import onl.tesseract.menu.SocialsMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command
public class SocialsCommand extends CommandContext {
    @CommandBody
    public boolean onCommand(CommandSender sender)
    {
        if (sender instanceof Player player )
        {
            new SocialsMenu(new MenuMenu()).open(player);
        }

        return true;
    }
}
