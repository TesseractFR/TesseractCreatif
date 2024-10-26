package onl.tesseract.command;

import onl.tesseract.CommandsBook;
import onl.tesseract.CreativePlayer;
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
            CreativePlayer creativePlayer = (CreativePlayer) CreativePlayer.get(player);
            creativePlayer.getBukkitPlayer().getInventory().addItem(CommandsBook.createGuideBook(creativePlayer));
        }
        return true;
    }
}
