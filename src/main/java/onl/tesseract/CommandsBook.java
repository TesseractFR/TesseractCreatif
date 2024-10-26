package onl.tesseract;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class CommandsBook {

    public static ItemStack createGuideBook(CreativePlayer player) {

        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        meta.setTitle("Le Build pour les Nuls !");
        meta.setAuthor("Tesseract");

        meta.addPages(
                Component.text("-------------------").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, false)
                        .append(Component.newline())
                        .append(Component.text("Bienvenue dans le").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("  guide du Build !").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("Dans ce livre, vous trouverez les ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false)
                                .append(Component.text("commandes essentielles").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                                .append(Component.text(" pour bien démarrer grâce à nos nombreux outils.").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false)))
                        .append(Component.newline())
                        .append(Component.text("Avec cela, vous deviendrez un ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false)
                                .append(Component.text("pro builder").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                                .append(Component.text(" !").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))),

                        Component.text("-------------------").color(NamedTextColor.BLUE).decoration(TextDecoration.BOLD, false)
                        .append(Component.text("       Sommaire", NamedTextColor.BLUE).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.BLUE).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.newline())
                        .append(Component.text("I - Les plots", NamedTextColor.DARK_GREEN).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.newline())
                        .append(Component.text("II - WorldEdit", NamedTextColor.RED).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("  1 - Sélections et ", NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("  schématics", NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("  2 - Modifications", NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("  3 - Générations", NamedTextColor.GOLD).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("  4 - Brush", NamedTextColor.DARK_PURPLE).decoration(TextDecoration.BOLD, false)),

                Component.text("Page 3: ...\n", NamedTextColor.GREEN)
                        .append(Component.text("Ajoutez d'autres instructions ici."))
        );


        book.setItemMeta(meta);

        player.sendMessage(Component.text("Vous avez reçu le livre : ").color(NamedTextColor.GREEN)
                .append(Component.text(meta.getTitle()).color(NamedTextColor.GOLD))
        );

        return book;
    }
}
