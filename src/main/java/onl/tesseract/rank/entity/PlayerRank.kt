package onl.tesseract.rank.entity

import lombok.Getter
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import onl.tesseract.core.title.Title
import onl.tesseract.core.title.TitleService
import onl.tesseract.lib.menu.CustomHeadItemBuilder
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.util.HeadConstante

private val serv = ServiceContainer[TitleService::class.java]
@Getter
enum class PlayerRank(
    override val permGroup: String,
    override val title: Title,
    override val color: TextColor,
    val headBuilder: CustomHeadItemBuilder,
    val rankPlot: RankPlot,
    val hoursRequired: Long,
) : Rank{
    APPRENTI(
        "default",
        serv.getById("APPRENTI"),
        NamedTextColor.GREEN,
        HeadConstante.teteApprenti,
        RankPlot.APPRENTI_PLOT,
        0),
    CONCEPTEUR(
        "concepteur",
        serv.getById("CONCEPTEUR"),
        NamedTextColor.LIGHT_PURPLE,
        HeadConstante.teteConcepteur,
        RankPlot.CONCEPTEUR_PLOT,
        4),
    CREATEUR(
        "createur",
        serv.getById("CREATEUR"),
        NamedTextColor.DARK_PURPLE,
        HeadConstante.teteCreateur,
        RankPlot.CREATEUR_PLOT,
        16),
    INGENIEUR(
        "ingenieur",
        serv.getById("INGENIEUR"),
        NamedTextColor.DARK_BLUE,
        HeadConstante.teteIngenieur,
        RankPlot.INGENIEUR_PLOT,
        48),
    BATISSEUR(
        "batisseur",
        serv.getById("BATISSEUR"),
        NamedTextColor.BLUE,
        HeadConstante.teteBatisseur,
        RankPlot.BATISSEUR_PLOT,
        168),
    VIRTUOSE(
        "virtuose",
        serv.getById("VIRTUOSE"),
        NamedTextColor.AQUA,
        HeadConstante.teteVirtuose,
        RankPlot.VIRTUOSEUR_PLOT,
        Long.MAX_VALUE);

}