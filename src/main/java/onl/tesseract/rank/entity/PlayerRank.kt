package onl.tesseract.rank.entity

import lombok.Getter
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import onl.tesseract.tesseractlib.entity.Title

@Getter
enum class PlayerRank(
    val permGroup: String,
    val title: Title,
    val color: TextColor,
    val rankPlot: RankPlot
) {
    APPRENTI("apprenti", Title.APPRENTI, NamedTextColor.GREEN, RankPlot.APPRENTI_PLOT),
    CONCEPTEUR("concepteur", Title.CONCEPTEUR, NamedTextColor.LIGHT_PURPLE, RankPlot.CONCEPTEUR_PLOT),
    CREATEUR("createur", Title.CREATEUR, NamedTextColor.DARK_PURPLE, RankPlot.CREATEUR_PLOT),
    INGENIEUR("ingenieur", Title.INGENIEUR, NamedTextColor.DARK_BLUE, RankPlot.INGENIEUR_PLOT),
    BATISSEUR("batisseur", Title.BATISSEUR, NamedTextColor.BLUE, RankPlot.BATISSEUR_PLOT),
    VIRTUOSE("virtuose", Title.VIRTUOSE, NamedTextColor.AQUA, RankPlot.VIRTUOSEUR_PLOT)
}