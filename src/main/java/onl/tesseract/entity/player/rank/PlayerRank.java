package onl.tesseract.entity.player.rank;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import onl.tesseract.tesseractlib.entity.Title;

@Getter
public enum PlayerRank implements Rank{
    APPRENTI("Apprenti",Title.APPRENTI, NamedTextColor.GREEN),
    CONCEPTEUR("Concepteur",Title.CONCEPTEUR, NamedTextColor.LIGHT_PURPLE),
    CREATEUR("Createur",Title.CREATEUR, NamedTextColor.DARK_PURPLE),
    INGENIEUR("Ingenieur",Title.INGENIEUR, NamedTextColor.DARK_BLUE),
    BATISSEUR("Batisseur",Title.BATISSEUR, NamedTextColor.BLUE),


    ;
    private final String permGroup;
    private final Title title;
    private final TextColor color;

    PlayerRank(String permGroup, Title title,TextColor color)
    {
        this.permGroup = permGroup;
        this.title = title;
        this.color = color;
    }

}