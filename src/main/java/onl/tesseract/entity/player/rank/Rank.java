package onl.tesseract.entity.player.rank;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import onl.tesseract.Creatif;
import onl.tesseract.tesseractlib.entity.Title;
import org.bukkit.entity.Player;

public interface Rank {
    static boolean checkPerm(Player p, String perm)
    {
        if (!Creatif.getPermissions().playerHas(p, perm))
        {
            p.sendMessage(Component.text("Vous n'avez pas la permission de faire cela.", NamedTextColor.RED));
            return false;
        }
        return true;
    }

    String getPermGroup();

    Title getTitle();

    TextColor getColor();

}

