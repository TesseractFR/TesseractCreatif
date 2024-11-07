package onl.tesseract.command;

import onl.tesseract.CreativePlayer;
import onl.tesseract.commandBuilder.CommandContext;
import onl.tesseract.commandBuilder.annotation.Command;
import onl.tesseract.commandBuilder.annotation.CommandBody;
import onl.tesseract.menu.MenuMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command
public class MenuCommand extends CommandContext {
    @CommandBody
    public boolean onCommand(CommandSender sender)
    {
        if (sender instanceof Player player) {
            if (CreativePlayer.get(player) instanceof CreativePlayer creativePlayer) {
                new MenuMenu(creativePlayer).open(player);
            }
        }
        return true;
    }
}
