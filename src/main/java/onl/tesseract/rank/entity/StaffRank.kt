package onl.tesseract.rank.entity

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
        GUIDE("Guide", serv.getById("GUIDE"), NamedTextColor.YELLOW),
    ANIMATEUR(
        "Animateur", serv.getById("BUILDER"),
        TextColor.fromHexString("#0088FF")!!
    ),
    MEDIATEUR(
        "Médiateur", serv.getById("ARCHITECTE"),
        TextColor.fromHexString("#F7DC6F")!!
    ),
        MODERATEUR("Modérateur", serv.getById("MODERATEUR"), NamedTextColor.GOLD),
        ADMINISTRATEUR("Administrateur", serv.getById("ADMINISTRATEUR"), NamedTextColor.RED);

}
