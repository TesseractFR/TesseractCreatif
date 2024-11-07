package onl.tesseract.rank.entity

import lombok.Getter
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextColor
import onl.tesseract.tesseractlib.entity.Title

@Getter
enum class StaffRank(val permGroup: String, val title: Title, val color: TextColor)
    {
    GUIDE("Guide", Title.GUIDE, NamedTextColor.YELLOW),
    ANIMATEUR(
        "Animateur", Title.BUILDER,
        TextColor.fromHexString("#0088FF")!!
    ),
    MEDIATEUR(
        "Médiateur", Title.ARCHITECTE,
        TextColor.fromHexString("#F7DC6F")!!
    ),
    MODERATEUR("Modérateur", Title.MODERATEUR, NamedTextColor.GOLD),
    ADMINISTRATEUR("Administrateur", Title.ADMINISTRATEUR, NamedTextColor.RED);

}
