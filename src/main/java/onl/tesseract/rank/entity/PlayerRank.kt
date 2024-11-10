package onl.tesseract.rank.entity

import lombok.Getter
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import onl.tesseract.tesseractlib.entity.Title

@Getter
enum class PlayerRank(
    override val permGroup: String,
    override val title: Title,
    override val color: TextColor,
    val rankPlot: RankPlot,
    val hoursRequired: Long
) : Rank{
    APPRENTI("apprenti", Title.APPRENTI, NamedTextColor.GREEN, RankPlot.APPRENTI_PLOT,0),
    CONCEPTEUR("concepteur", Title.CONCEPTEUR, NamedTextColor.LIGHT_PURPLE, RankPlot.CONCEPTEUR_PLOT,4),
    CREATEUR("createur", Title.CREATEUR, NamedTextColor.DARK_PURPLE, RankPlot.CREATEUR_PLOT,16),
    INGENIEUR("ingenieur", Title.INGENIEUR, NamedTextColor.DARK_BLUE, RankPlot.INGENIEUR_PLOT,48),
    BATISSEUR("batisseur", Title.BATISSEUR, NamedTextColor.BLUE, RankPlot.BATISSEUR_PLOT,168),
    VIRTUOSE("virtuose", Title.VIRTUOSE, NamedTextColor.AQUA, RankPlot.VIRTUOSEUR_PLOT,Long.MAX_VALUE);

}