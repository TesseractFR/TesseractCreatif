package onl.tesseract.command;

import onl.tesseract.commandBuilder.CommandContext;
import onl.tesseract.commandBuilder.annotation.Command;
import onl.tesseract.commandBuilder.annotation.CommandBody;
import org.bukkit.entity.Player;

@Command
public class MenuCommand extends CommandContext {
    @CommandBody
    void onCommand(Player sender){
        //Todo ouvrir MenuMenu
    }
}
