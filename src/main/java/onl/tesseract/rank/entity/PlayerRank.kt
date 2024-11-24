package onl.tesseract.rank.entity

import lombok.Getter
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import onl.tesseract.core.title.Title
import onl.tesseract.core.title.TitleService
import onl.tesseract.lib.service.ServiceContainer

private val serv = ServiceContainer[TitleService::class.java]
@Getter
enum class PlayerRank(
    override val permGroup: String,
    override val title: Title,
    override val color: TextColor,
    val rankPlot: RankPlot,
    val hoursRequired: Long
) : Rank{
    APPRENTI("apprenti", serv.getById("APPRENTI"), NamedTextColor.GREEN, RankPlot.APPRENTI_PLOT, 0),
    CONCEPTEUR("concepteur", serv.getById("CONCEPTEUR"), NamedTextColor.LIGHT_PURPLE, RankPlot.CONCEPTEUR_PLOT, 4),
    CREATEUR("createur", serv.getById("CREATEUR"), NamedTextColor.DARK_PURPLE, RankPlot.CREATEUR_PLOT, 16),
    INGENIEUR("ingenieur", serv.getById("INGENIEUR"), NamedTextColor.DARK_BLUE, RankPlot.INGENIEUR_PLOT, 48),
    BATISSEUR("batisseur", serv.getById("BATISSEUR"), NamedTextColor.BLUE, RankPlot.BATISSEUR_PLOT, 168),
    VIRTUOSE("virtuose", serv.getById("VIRTUOSE"), NamedTextColor.AQUA, RankPlot.VIRTUOSEUR_PLOT, Long.MAX_VALUE);

}