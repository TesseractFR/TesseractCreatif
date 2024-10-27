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

                Component.text("-------------------").color(NamedTextColor.DARK_GREEN)
                        .append(Component.newline())
                        .append(Component.text("   I - Les plots").color(NamedTextColor.DARK_GREEN).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.DARK_GREEN))
                        .append(Component.newline())
                        .append(Component.text("- /mondes : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Menu des mondes").color(NamedTextColor.BLACK))
                        .append(Component.newline())
                        .append(Component.text("- /p (/plot) auto : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Claim le plot vide le plus proche dans le monde actuel").color(NamedTextColor.BLACK))
                        .append(Component.newline())
                        .append(Component.text("- /p claim : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Claim le plot dans lequel tu te trouves (si libre)").color(NamedTextColor.BLACK))
                        .append(Component.newline())
                        .append(Component.text("- /p clear : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Vide le plot").color(NamedTextColor.BLACK))
                        .append(Component.newline())
                        .append(Component.text("- /p delete : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Supprime le plot").color(NamedTextColor.BLACK))
                        .append(Component.newline())
                        .append(Component.text("- /p add : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Ajoute un joueur sur le plot (peut construire si tu es connecté)").color(NamedTextColor.BLACK))
                        .append(Component.newline())
                        .append(Component.text("- /p trust : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Ajoute un joueur sur le plot (peut construire en ton absence)").color(NamedTextColor.BLACK))
                        .append(Component.newline()),

                // Page 4 : Les plots - Partie 2
                Component.text("- /p visit <pseudo> [n°] : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Visiter un plot de joueur").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- /p home [n°] : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Téléportation à ton plot").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- /p merge : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Regroupe 2 plots consécutifs pour en faire un plus grand").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- /p flag : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Change les paramètres du plot (heure, météo...)").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false)),

                Component.newline()
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.RED))
                        .append(Component.newline())
                        .append(Component.text("   II - WorldEdit").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.RED))
                        .append(Component.newline())
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.DARK_AQUA))
                        .append(Component.newline())
                        .append(Component.text("1 - Sélection et schématics").color(NamedTextColor.DARK_AQUA).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.DARK_AQUA)),

                // Page 6 : WorldEdit - Sélection et Schématics - Partie 1
                Component.text("- //pos1 et //pos2 (ou hache en bois) : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Sélection d'un pavé avec 2 extrémités diagonales").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //copy : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Copie la structure là où le joueur se trouve").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //cut : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Coupe la sélection en la copiant").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //paste : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Colle la structure + blocs d'air autour, écrasant les structures existantes").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //paste -a : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Colle la sélection sans modifier les structures existantes").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline()),

                // Page 7 : WorldEdit - Sélection et Schématics - Partie 2
                Component.text("- //undo : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Annule la dernière opération").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //rotate <angle en °> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Tourne la sélection d'un angle (penser à //copy avant)").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //flip [direction] : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Retourne la sélection selon une direction (N/E/S/O), ou vers le point de vue du joueur").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false)),

                Component.text("- //schematic save <nom> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Sauvegarde la sélection comme schematic, disponible dans votre dossier de jeu (penser à //copy avant)").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false)),

                Component.newline()
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.RED))
                        .append(Component.newline())
                        .append(Component.text("   III - WorldEdit").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.RED))
                        .append(Component.newline())
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.DARK_RED))
                        .append(Component.newline())
                        .append(Component.text("2 - Modifications de la sélection").color(NamedTextColor.DARK_RED).decoration(TextDecoration.BOLD, true))
                        .append(Component.newline())
                        .append(Component.text("-------------------").color(NamedTextColor.DARK_RED)),

                // Page : WorldEdit - Modifications de la Sélection
                Component.text("- //set <bloc> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Remplit la sélection de <bloc>").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //replace [bloc1] <bloc2> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Remplace [bloc1] par <bloc2> dans la sélection").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //center <bloc> ou //middle <bloc> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Remplit le milieu de la sélection par <bloc>").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline()),

                Component.text("- //walls <bloc> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Crée des murs en <bloc> sur toute la bordure de la sélection").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //faces <bloc> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Délimite toute la sélection par un remplissage des bordures par <bloc>").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline()),

                Component.text("- //hollow [[épaisseur] [bloc]] : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Change l'intérieur de la sélection en [bloc] avec une épaisseur de couche externe restante").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //move [dist] [direction] : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Bouge la sélection d'une certaine distance, dans une direction (N/E/S/W)").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline())
                        .append(Component.text("- //stack [nombre] : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true))
                        .append(Component.text("Duplique la sélection [nombre] fois").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))
                        .append(Component.newline()),

                Component.text("- //overlay <bloc> : ").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, true)
                        .append(Component.text("Recouvre la sélection de <bloc>").color(NamedTextColor.BLACK).decoration(TextDecoration.BOLD, false))

        );



        book.setItemMeta(meta);

        player.sendMessage(Component.text("Vous avez reçu le livre : ").color(NamedTextColor.GREEN)
                .append(Component.text(meta.getTitle()).color(NamedTextColor.GOLD))
        );

        return book;
    }
}
