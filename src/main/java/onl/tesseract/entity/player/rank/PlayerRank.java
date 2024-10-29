package onl.tesseract.entity.player.rank;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import onl.tesseract.tesseractlib.entity.Title;

@Getter
public enum PlayerRank implements Rank{
    APPRENTI("apprenti",Title.APPRENTI, NamedTextColor.GREEN,RankPlot.APPRENTI_PLOT),
    CONCEPTEUR("concepteur",Title.CONCEPTEUR, NamedTextColor.LIGHT_PURPLE,RankPlot.CONCEPTEUR_PLOT),
    CREATEUR("createur",Title.CREATEUR, NamedTextColor.DARK_PURPLE,RankPlot.CREATEUR_PLOT),
    INGENIEUR("ingenieur",Title.INGENIEUR, NamedTextColor.DARK_BLUE,RankPlot.INGENIEUR_PLOT),
    BATISSEUR("batisseur",Title.BATISSEUR, NamedTextColor.BLUE,RankPlot.BATISSEUR_PLOT),
    VIRTUOSE("virtuose",Title.VIRTUOSE,NamedTextColor.AQUA,RankPlot.VIRTUOSEUR_PLOT)

    ;
    private final String permGroup;
    private final Title title;
    private final TextColor color;
    private final RankPlot rankPlot;

    PlayerRank(String permGroup, Title title,TextColor color, RankPlot rankPlot)
    {
        this.permGroup = permGroup;
        this.title = title;
        this.color = color;
        this.rankPlot = rankPlot;
    }

}