package onl.tesseract

import lombok.AccessLevel
import lombok.Getter
import lombok.NoArgsConstructor
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
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
                .append(
                    Component.text(
                        Objects.requireNonNull(bookMeta.title!!),
                        NamedTextColor.GOLD,
                        TextDecoration.ITALIC
                    )
                )
        )
    }


    fun createGuideBook(): ItemStack {
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

    private fun createLastPage(): TextComponent {
        return Component.newline()
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(
                Component.text(
                    "Voilà, tu as maintenant toutes les bases pour faire la plus belle construction, alors à toi de jouer ! ;)",
                    NamedTextColor.RED,
                    TextDecoration.BOLD,
                    TextDecoration.ITALIC
                )
            )
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.newline())
    }

    private fun createFAWEBrushSecondePage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //mask <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée un masque et affecte le brush avec seulement le bloc.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //gmask <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée le masque pour tous les brushs.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.newline())
            .append(
                Component.text(
                    "D'autres brushs sont disponibles, voir sur internet.",
                    NamedTextColor.GRAY,
                    TextDecoration.ITALIC
                )
            )
    }

    private fun createFAWEBrushFirstPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //brush sphere <bloc> [rayon] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Dessiner une sphère.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //brush cylinder <bloc> [rayon] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Dessiner un cylindre.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //brush clipboard : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Dessiner la sélection.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //brush smooth : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Lisser la structure.", NamedTextColor.BLACK))
    }

    private fun createFAWEBrushTitlePage(): TextComponent {
        return Component.newline()
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.text("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_PURPLE))
            .append(Component.newline())
            .append(Component.text("    4 - Brush", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_PURPLE))
            .append(Component.newline())
            .append(Component.text("/!\\ SE MUNIR D'UN PINCEAU ! ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Une fois les commandes exécutées, cliquer avec le pinceau pour dessiner la figure.",
                    NamedTextColor.BLACK
                )
            )
    }

    private fun createFAWEGenerationSecondPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //hsphere <bloc> <rayon> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée une sphère creuse.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //pyramid <bloc> <hauteur> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée une pyramide pleine.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //hpyramid <bloc> <hauteur> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée une pyramide creuse.", NamedTextColor.BLACK))
    }

    private fun createFAWEGenerationFirstPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //cyl <bloc> <rayon> [hauteur] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée un cylindre rempli (hauteur 1 par défaut).", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //hcyl <bloc> <rayon> [hauteur] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée un cylindre creux (hauteur 1 par défaut).", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //sphere <bloc> <rayon> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Crée une sphère pleine.", NamedTextColor.BLACK))
            .append(Component.newline())
    }

    private fun createFAWEGenerationTitlePage(): TextComponent {
        return Component.newline()
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.text("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.GOLD))
            .append(Component.newline())
            .append(Component.text(" 3 - Générations", NamedTextColor.GOLD, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.GOLD))
    }

    private fun createFAWEModifFourthPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //overlay <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Recouvre la sélection de <bloc>.", NamedTextColor.BLACK))
    }

    private fun createFAWEModifThirdPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //hollow [[épaisseur] [bloc]] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Change l'intérieur de la sélection en [bloc] avec une épaisseur de couche externe restante.",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
            .append(Component.text("- //move [dist] [direction] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Bouge la sélection d'une certaine distance, dans une direction (N/E/S/W).",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
            .append(Component.text("- //stack [nombre] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Duplique la sélection [nombre] fois.", NamedTextColor.BLACK))
            .append(Component.newline())
    }

    private fun createFAWEModifSecondPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //walls <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Crée des murs en <bloc> sur toute la bordure de la sélection.",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
            .append(Component.text("- //faces <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Délimite toute la sélection par un remplissage des bordures par <bloc>.",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
    }

    private fun createFAWEModifFirstPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //set <bloc> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Remplit la sélection de <bloc>.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //replace [bloc1] <bloc2> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Remplace [bloc1] par <bloc2> dans la sélection.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(
                Component.text(
                    "- //center <bloc> ou //middle <bloc> : ",
                    NamedTextColor.BLACK,
                    TextDecoration.BOLD
                )
            )
            .append(Component.text("Remplit le milieu de la sélection par <bloc>.", NamedTextColor.BLACK))
            .append(Component.newline())
    }

    private fun createFAWEModifTitlePage(): TextComponent {
        return Component.newline()
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.text("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_RED))
            .append(Component.newline())
            .append(Component.text("2 - Modifications de la sélection", NamedTextColor.DARK_RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_RED))
    }

    private fun createFAWESelectionFourthPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //schematic save <nom> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text("Sauvegarde la sélection comme schematic, disponible dans votre dossier de jeu (penser à //copy avant).")
                    .color(
                        NamedTextColor.BLACK
                    )
            )
    }

    private fun createFAWESelectionThirdPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //undo : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Annule la dernière opération.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //rotate <angle en °> : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Tourne la sélection d'un angle (penser à //copy avant).", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //flip [direction] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Retourne la sélection selon une direction (N/E/S/O), ou vers le point de vue du joueur.",
                    NamedTextColor.BLACK
                )
            )
    }

    private fun createFAWESelectionSecondePage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //paste : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Colle la structure + blocs d'air autour complétant la sélection, écrasant les structures existantes.",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
            .append(Component.text("- //paste -a : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Colle la sélection sans modifier les structures existantes.", NamedTextColor.BLACK))
            .append(Component.newline())
    }

    private fun createFAWESelectionFirstPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- //pos1 et //pos2 : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Sélection d'un pavé avec 2 extrémités diagonales.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //wand : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "(Alternative) Reçois la hache de sélection, suivre les indications.",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
            .append(Component.text("- //copy : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Copie la structure là où le joueur se trouve.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- //cut : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Coupe la sélection en la copiant.", NamedTextColor.BLACK))
            .append(Component.newline())
    }

    private fun createFAWESelectionTitlePage(): TextComponent {
        return Component.newline()
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.text("   II - WorldEdit", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_AQUA))
            .append(Component.newline())
            .append(Component.text("1 - Sélection et schématics", NamedTextColor.DARK_AQUA, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_AQUA))
    }

    private fun createSecondePlotPage(): TextComponent {
        return Component.text("", NamedTextColor.BLACK)
            .append(Component.text("- /p visit <pseudo> [n°] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Visiter un plot de joueur (défaut 1).", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p home [n°] : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Téléportation à ton plot (défaut 1).", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p merge : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Regroupe 2 plots consécutifs pour en faire un plus grand.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p flag : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Change les paramètres du plot (heure, météo...).", NamedTextColor.BLACK))
    }

    private fun createFirstPlotPage(): TextComponent {
        return Component.text(RED_TIRET_LINE, NamedTextColor.DARK_GREEN)
            .append(Component.newline())
            .append(Component.text("   I - Les plots", NamedTextColor.DARK_GREEN, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_GREEN))
            .append(Component.newline())
            .append(Component.text("- /mondes : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Menu des mondes.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p (/plot) auto : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Claim le plot vide le plus proche dans le monde actuel.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p claim : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Claim le plot dans lequel tu te trouves (si libre).", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p clear : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Vide le plot.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p delete : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text("Supprime le plot.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.text("- /p add : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Ajoute un joueur sur le plot (peut construire si tu es connecté).",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
            .append(Component.text("- /p trust : ", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(
                Component.text(
                    "Ajoute un joueur sur le plot (peut construire en ton absence).",
                    NamedTextColor.BLACK
                )
            )
            .append(Component.newline())
    }

    private fun createSommairePage(): TextComponent {
        return Component.text(RED_TIRET_LINE, NamedTextColor.BLUE)
            .append(Component.text("       Sommaire", NamedTextColor.BLUE, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.BLUE))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text("I - Les plots p.4", NamedTextColor.DARK_GREEN))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text("II - WorldEdit", NamedTextColor.RED))
            .append(Component.newline())
            .append(Component.text(" 1 - Sélections et ", NamedTextColor.DARK_AQUA))
            .append(Component.newline())
            .append(Component.text(" schématics p.6", NamedTextColor.DARK_AQUA))
            .append(Component.newline())
            .append(Component.text(" 2 - Modifications p.11", NamedTextColor.DARK_RED))
            .append(Component.newline())
            .append(Component.text(" 3 - Générations p.16", NamedTextColor.GOLD))
            .append(Component.newline())
            .append(Component.text(" 4 - Brush p.19", NamedTextColor.DARK_PURPLE))
    }

    private fun createInfoSyntaxePage(): TextComponent {
        return Component.text(RED_TIRET_LINE, NamedTextColor.DARK_RED)
            .append(Component.newline())
            .append(Component.text(" /!\\ INFO SYNTAXE", NamedTextColor.DARK_RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.DARK_RED))
            .append(Component.newline())
            .append(Component.text("Dans les commandes qui vont suivre :", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text("- Les arguments ", NamedTextColor.BLACK))
            .append(Component.text("[...]", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text(" sont en option.", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text("- Les arguments ", NamedTextColor.BLACK))
            .append(Component.text("<...>", NamedTextColor.BLACK, TextDecoration.BOLD))
            .append(Component.text(" sont ", NamedTextColor.BLACK))
            .append(Component.text("OBLIGATOIRES ", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.text("!", NamedTextColor.BLACK))
            .append(Component.newline())
            .append(Component.newline())
            .append(Component.text("   Bonne lecture ;)", NamedTextColor.GRAY, TextDecoration.ITALIC))
    }

    private fun createFirstPage(): TextComponent {
        return Component.text(RED_TIRET_LINE, NamedTextColor.RED)
            .append(Component.newline())
            .append(Component.text("   LE BUILD POUR", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text("     LES NULS !", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.newline())
            .append(Component.text(RED_TIRET_LINE, NamedTextColor.RED))
            .append(Component.newline())
            .append(
                Component.text("Dans ce livre, vous trouverez les ", NamedTextColor.BLACK)
                    .append(Component.text("commandes essentielles", NamedTextColor.BLACK, TextDecoration.BOLD))
                    .append(
                        Component.text(
                            " pour bien démarrer sur le serveur Créatif grâce à nos nombreux outils. Avec cela, vous deviendrez un ",
                            NamedTextColor.BLACK
                        )
                    )
                    .append(Component.text("pro builder", NamedTextColor.BLACK, TextDecoration.BOLD))
                    .append(Component.text(" !", NamedTextColor.BLACK))
            )
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
