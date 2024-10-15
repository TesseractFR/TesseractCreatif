package onl.tesseract.entity.player.rank;

import lombok.Getter;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import onl.tesseract.tesseractlib.entity.Title;

@Getter
public enum StaffRank implements Rank{
    GUIDE("Guide", Title.GUIDE,NamedTextColor.YELLOW),
    ANIMATEUR("Animateur", Title.BUILDER, TextColor.fromHexString("#0088FF")),
    MEDIATEUR("Médiateur",Title.ARCHITECTE, TextColor.fromHexString("#F7DC6F")),
    MODERATEUR("Modérateur", Title.MODERATEUR,  NamedTextColor.GOLD),
    ADMINISTRATEUR("Administrateur", Title.ADMINISTRATEUR, NamedTextColor.RED),
            ;
    private final String permGroup;
    private final Title title;
    private final TextColor color;


    StaffRank(String permGroup, Title title, TextColor color) {
        this.permGroup = permGroup;
        this.title = title;
        this.color = color;
    }
}
