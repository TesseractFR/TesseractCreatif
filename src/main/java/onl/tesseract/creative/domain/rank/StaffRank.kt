package onl.tesseract.creative.domain.rank

import lombok.Getter
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import onl.tesseract.core.title.Title
import onl.tesseract.core.title.TitleService
import onl.tesseract.lib.service.ServiceContainer

private val serv = ServiceContainer[TitleService::class.java]

@Getter
enum class StaffRank(override val permGroup: String, override val title: Title, override val color: TextColor) : Rank
    {
        GUIDE("guide", serv.getById("GUIDE"), NamedTextColor.YELLOW),
        BUILDER(
            "builder", serv.getById("BUILDER"),
        TextColor.fromHexString("#0088FF")!!
    ),
        ARCHITECTE(
            "architecte", serv.getById("ARCHITECTE"),
        TextColor.fromHexString("#A815FD")!!
    ),
        MODERATEUR("moderateur", serv.getById("MODERATEUR"), NamedTextColor.GOLD),
        ADMINISTRATEUR("administrateur", serv.getById("ADMINISTRATEUR"), NamedTextColor.RED);

}
