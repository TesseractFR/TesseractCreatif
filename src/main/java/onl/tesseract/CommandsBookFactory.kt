package onl.tesseract

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.appendNewLine
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import java.util.*

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class CommandsBookFactory {
    fun giveGuideBook(player: Player) {
        val book = createGuideBook()
        player.inventory.addItem(book)
        val bookMeta = book.itemMeta as BookMeta
        player.sendMessage(
            Component.text("Vous avez reçu le livre : ", NamedTextColor.DARK_GREEN)
                .append(Objects.requireNonNull(bookMeta.title!!), NamedTextColor.GOLD, TextDecoration.ITALIC)
        )
    }

    private fun createGuideBook(): ItemStack {
        val book = ItemStack(Material.WRITTEN_BOOK)
        val meta = book.itemMeta as BookMeta

        meta.setTitle("Le Build pour les Nuls !")
        meta.author = "Tesseract"

        meta.addPages(
            createFirstPage(),
            createInfoSyntaxePage(),
            createSommairePage(),
            createFirstPlotPage(),
            createSecondePlotPage(),
            createFAWESelectionTitlePage(),
            createFAWESelectionFirstPage(),
            createFAWESelectionSecondePage(),
            createFAWESelectionThirdPage(),
            createFAWESelectionFourthPage(),
            createFAWEModifTitlePage(),
            createFAWEModifFirstPage(),
            createFAWEModifSecondPage(),
            createFAWEModifThirdPage(),
            createFAWEModifFourthPage(),
            createFAWEGenerationTitlePage(),
            createFAWEGenerationFirstPage(),
            createFAWEGenerationSecondPage(),
            createFAWEBrushTitlePage(),
            createFAWEBrushFirstPage(),
            createFAWEBrushSecondePage(),
            createLastPage()
        )

        meta.displayName(Component.text("Le Build pour les Nuls !", NamedTextColor.GOLD))
        book.setItemMeta(meta)
        return book
    }

    private fun createLastPage(): Component {
        return Component.newline()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("Voilà, tu as maintenant toutes les bases pour faire la plus belle construction, alors à toi de jouer ! ;)", NamedTextColor.RED, TextDecoration.BOLD, TextDecoration.ITALIC)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .appendNewLine()
    }

    private fun createFAWEBrushSecondePage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //mask <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée un masque et affecte le brush avec seulement le bloc.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //gmask <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée le masque pour tous les brushs.", NamedTextColor.BLACK)
            .appendNewLine()
            .appendNewLine()
            .appendNewLine()
            .append("D'autres brushs sont disponibles, voir sur internet.", NamedTextColor.GRAY, TextDecoration.ITALIC)
    }

    private fun createFAWEBrushFirstPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //brush sphere <bloc> [rayon] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Dessiner une sphère.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //brush cylinder <bloc> [rayon] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Dessiner un cylindre.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //brush clipboard : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Dessiner la sélection.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //brush smooth : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Lisser la structure.", NamedTextColor.BLACK)
    }

    private fun createFAWEBrushTitlePage(): Component {
        return Component.newline()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_PURPLE)
            .appendNewLine()
            .append("    4 - Brush", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_PURPLE)
            .appendNewLine()
            .append("/!\\ SE MUNIR D'UN PINCEAU ! ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Une fois les commandes exécutées, cliquer avec le pinceau pour dessiner la figure.", NamedTextColor.BLACK)
    }

    private fun createFAWEGenerationSecondPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //hsphere <bloc> <rayon> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée une sphère creuse.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //pyramid <bloc> <hauteur> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée une pyramide pleine.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //hpyramid <bloc> <hauteur> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée une pyramide creuse.", NamedTextColor.BLACK)
    }

    private fun createFAWEGenerationFirstPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //cyl <bloc> <rayon> [hauteur] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée un cylindre rempli (hauteur 1 par défaut).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //hcyl <bloc> <rayon> [hauteur] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée un cylindre creux (hauteur 1 par défaut).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //sphere <bloc> <rayon> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée une sphère pleine.", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createFAWEGenerationTitlePage(): Component {
        return Component.newline()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.GOLD)
            .appendNewLine()
            .append(" 3 - Générations", NamedTextColor.GOLD, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.GOLD)
    }

    private fun createFAWEModifFourthPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //overlay <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Recouvre la sélection de <bloc>.", NamedTextColor.BLACK)
    }

    private fun createFAWEModifThirdPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //hollow [[épaisseur] [bloc]] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Change l'intérieur de la sélection en [bloc] avec une épaisseur de couche externe restante.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //move [dist] [direction] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Bouge la sélection d'une certaine distance, dans une direction (N/E/S/W).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //stack [nombre] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Duplique la sélection [nombre] fois.", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createFAWEModifSecondPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //walls <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Crée des murs en <bloc> sur toute la bordure de la sélection.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //faces <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Délimite toute la sélection par un remplissage des bordures par <bloc>.", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createFAWEModifFirstPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //set <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Remplit la sélection de <bloc>.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //replace [bloc1] <bloc2> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Remplace [bloc1] par <bloc2> dans la sélection.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //center <bloc> ou //middle <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Remplit le milieu de la sélection par <bloc>.", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createFAWEModifTitlePage(): Component {
        return Component.newline()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_RED)
            .appendNewLine()
            .append("2 - Modifications de la sélection", NamedTextColor.DARK_RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_RED)
    }

    private fun createFAWESelectionFourthPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //schematic save <nom> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Sauvegarde la sélection comme schematic, disponible sur le serveur (penser à //copy avant).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //download : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Télécharge le schématic, cliquer sur le lien dans le tchat (penser à //copy avant).", NamedTextColor.BLACK)
    }

    private fun createFAWESelectionThirdPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //undo : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Annule la dernière opération.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //rotate <angle en °> : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Tourne la sélection d'un angle (penser à //copy avant).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //flip [direction] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Retourne la sélection selon une direction (N/E/S/O), ou vers le point de vue du joueur.", NamedTextColor.BLACK)
    }

    private fun createFAWESelectionSecondePage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //paste : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Colle la structure + blocs d'air autour complétant la sélection, écrasant les structures existantes.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //paste -a : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Colle la sélection sans modifier les structures existantes.", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createFAWESelectionFirstPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- //pos1 et //pos2 : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Sélection d'un pavé avec 2 extrémités diagonales.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //wand : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("(Alternative) Reçois la hache de sélection, suivre les indications.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //copy : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Copie la structure là où le joueur se trouve.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- //cut : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Coupe la sélection en la copiant.", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createFAWESelectionTitlePage(): Component {
        return Component.newline()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_AQUA)
            .appendNewLine()
            .append("1 - Sélection et schématics", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_AQUA)
    }

    private fun createSecondePlotPage(): Component {
        return Component.text("", NamedTextColor.BLACK)
            .append("- /p visit <pseudo> [n°] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Visiter un plot de joueur (défaut 1).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p home [n°] : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Téléportation à ton plot (défaut 1).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p merge : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Regroupe 2 plots consécutifs pour en faire un plus grand.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p flag : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Change les paramètres du plot (heure, météo...).", NamedTextColor.BLACK)
    }

    private fun createFirstPlotPage(): Component {
        return Component.text(RED_TIRET_LINE, NamedTextColor.DARK_GREEN)
            .appendNewLine()
            .append("   I - Les plots", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_GREEN)
            .appendNewLine()
            .append("- /mondes : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Menu des mondes.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p (/plot) auto : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Claim le plot vide le plus proche dans le monde actuel.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p claim : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Claim le plot dans lequel tu te trouves (si libre).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p clear : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Vide le plot.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p delete : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Supprime le plot.", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p add : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Ajoute un joueur sur le plot (peut construire si tu es connecté).", NamedTextColor.BLACK)
            .appendNewLine()
            .append("- /p trust : ", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append("Ajoute un joueur sur le plot (peut construire en ton absence).", NamedTextColor.BLACK)
            .appendNewLine()
    }

    private fun createSommairePage(): Component {
        return Component.text(RED_TIRET_LINE, NamedTextColor.BLUE)
            .append("       Sommaire", NamedTextColor.BLUE, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.BLUE)
            .appendNewLine()
            .appendNewLine()
            .append("I - Les plots p.4", NamedTextColor.DARK_GREEN)
            .appendNewLine()
            .appendNewLine()
            .append("II - WorldEdit", NamedTextColor.RED)
            .appendNewLine()
            .append(" 1 - Sélections et ", NamedTextColor.DARK_AQUA)
            .appendNewLine()
            .append(" schématics p.6", NamedTextColor.DARK_AQUA)
            .appendNewLine()
            .append(" 2 - Modifications p.11", NamedTextColor.DARK_RED)
            .appendNewLine()
            .append(" 3 - Générations p.16", NamedTextColor.GOLD)
            .appendNewLine()
            .append(" 4 - Brush p.19", NamedTextColor.DARK_PURPLE)
    }

    private fun createInfoSyntaxePage(): Component {
        return Component.text(RED_TIRET_LINE, NamedTextColor.DARK_RED)
            .appendNewLine()
            .append(" /!\\ INFO SYNTAXE", NamedTextColor.DARK_RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.DARK_RED)
            .appendNewLine()
            .append("Dans les commandes qui vont suivre :", NamedTextColor.BLACK)
            .appendNewLine()
            .appendNewLine()
            .append("- Les arguments ", NamedTextColor.BLACK)
            .append("[...]", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append(" sont en option.", NamedTextColor.BLACK)
            .appendNewLine()
            .appendNewLine()
            .append("- Les arguments ", NamedTextColor.BLACK)
            .append("<...>", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append(" sont ", NamedTextColor.BLACK)
            .append("OBLIGATOIRES ", NamedTextColor.RED, TextDecoration.BOLD)
            .append("!", NamedTextColor.BLACK)
            .appendNewLine()
            .appendNewLine()
            .append("   Bonne lecture ;)", NamedTextColor.GRAY, TextDecoration.ITALIC)
    }

    private fun createFirstPage(): Component {
        return Component.text(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("   LE BUILD POUR", NamedTextColor.RED, TextDecoration.BOLD)
            .appendNewLine()
            .append("     LES NULS !", NamedTextColor.RED, TextDecoration.BOLD)
            .appendNewLine()
            .append(RED_TIRET_LINE, NamedTextColor.RED)
            .appendNewLine()
            .append("Dans ce livre, vous trouverez les ", NamedTextColor.BLACK)
            .append("commandes essentielles", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append(" pour bien démarrer sur le serveur Créatif grâce à nos nombreux outils. Avec cela, vous deviendrez un ", NamedTextColor.BLACK)
            .append("pro builder", NamedTextColor.BLACK, TextDecoration.BOLD)
            .append(" !", NamedTextColor.BLACK)
    }

    companion object {
        @Getter
        private var INSTANCE: CommandsBookFactory? = null

        @JvmStatic
        fun getInstance(): CommandsBookFactory {
            if (INSTANCE == null) {
                INSTANCE = CommandsBookFactory()
            }
            return INSTANCE!!
        }

        const val RED_TIRET_LINE: String = "-------------------"
    }
}
