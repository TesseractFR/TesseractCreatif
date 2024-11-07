package onl.tesseract.command;

import onl.tesseract.CreativePlayer;
import onl.tesseract.commandBuilder.CommandContext;
import onl.tesseract.commandBuilder.annotation.Command;
import onl.tesseract.commandBuilder.annotation.CommandBody;
import onl.tesseract.menu.PluginsToolsMenu;
import onl.tesseract.menu.MenuMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command
public class BuildToolsCommand extends CommandContext {
    @CommandBody
    public boolean onCommand(CommandSender sender)
    {
        if (sender instanceof Player player )
        {
            new PluginsToolsMenu().open(player);
        }

        return true;
    }
}
