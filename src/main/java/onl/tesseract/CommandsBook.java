package onl.tesseract;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
                Component.text("Bienvenue dans le Menu de Build!", NamedTextColor.RED)
                        .append(Component.newline())
                        .append(Component.text("Utilisez ce guide pour les commandes de build.")),

                Component.text("Page 2: Commande /buildmenu\n", NamedTextColor.BLUE)
                        .append(Component.text("Accès rapide au menu de construction.")),

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
