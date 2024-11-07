package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.util.append
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player

class PluginsToolsMenu : InventoryMenu {
    constructor() : super(
        27,
        Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
    )

    constructor(previous: InventoryMenu?) : super(
        27,
        Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
        previous
    )

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(
            0, Material.BOOK,
            Component.text("Schematic Import", NamedTextColor.RED, TextDecoration.BOLD),
            Component.text(
                "Ce plugin vous permet de copier des constructions d'un monde à un autre facilement.",
                NamedTextColor.GRAY
            )
                .append(
                    Component.text(
                        " Cliquez pour vous rediriger vers le site permettant l'import de schématics.",
                        NamedTextColor.GRAY
                    )
                )
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            10, Material.WOODEN_AXE,
            Component.text("FastAsyncWorldEdit (FAWE)", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            Component.text(
                "Avec FAWE, vous pouvez copier et coller de grandes zones de blocs en un clin d'œil, et bien plus encore !",
                NamedTextColor.GRAY
            )
                .append(Component.text("Découvrez les commandes de base dans ", NamedTextColor.GRAY))
                .append(Component.text("Le Build pour les Nuls", NamedTextColor.GOLD, TextDecoration.ITALIC))
                .append(Component.text(", faites ", NamedTextColor.GRAY))
                .append(Component.text("/commandes ", NamedTextColor.RED))
                .append(Component.text("pour le recevoir.", NamedTextColor.GRAY))
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            2, Material.DIAMOND_BOOTS,
            Component.text("Builder Utilities", NamedTextColor.AQUA, TextDecoration.BOLD),
            Component.text(
                "Ce plugin vous donne des outils géniaux comme le speed et le no-clip pour construire sans effort.",
                NamedTextColor.GRAY
            )
                .append(
                    Component.text(
                        " Vous pouvez aussi changer les couleurs des blocs en un instant.",
                        NamedTextColor.GRAY
                    )
                )
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            12, Material.ARMOR_STAND,
            Component.text("ArmorStand Tools (AST)", NamedTextColor.DARK_GREEN, TextDecoration.BOLD),
            Component.text("AST est là pour vous aider à personnaliser vos armorstands.", NamedTextColor.GRAY)
                .append(
                    Component.text(
                        " Amusez-vous à les déplacer et les manipuler comme vous le souhaitez !. Pour ouvrir le menu, faites ",
                        NamedTextColor.GRAY
                    )
                )
                .append(Component.text("/ast", NamedTextColor.RED))
                .append(Component.text(".", NamedTextColor.GRAY))
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            4, Material.PLAYER_HEAD,
            Component.text("Head DataBase (HDB)", NamedTextColor.GOLD, TextDecoration.BOLD),
            Component.text(
                "HDB vous permet d'ajouter des têtes décoratives à vos constructions, les rendant plus colorés et uniques. Faites la commande ",
                NamedTextColor.GRAY
            )
                .append(Component.text("/hdb ", NamedTextColor.RED))
                .append(Component.text("pour ouvrir le menu. ", NamedTextColor.GRAY))
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            14, Material.ARROW,
            Component.text("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD),
            Component.text(
                "Libérez votre créativité avec les outils et pinceaux puissants de VoxelSniper. Façonnez des montagnes, sculptez des grottes et peignez des mondes avec une grande finesse.",
                NamedTextColor.GRAY
            )
                .append(
                    Component.text(
                        "Transformez des blocs en chefs-d'œuvre impressionnants qui émerveilleront les autres !",
                        NamedTextColor.GRAY
                    )
                )
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            6, Material.PAPER,
            Component.text("Arceon", NamedTextColor.GREEN, TextDecoration.BOLD),
            Component.text("Arceon ajoute des options intéressantes à WorldEdit, ", NamedTextColor.GRAY)
                .append(
                    Component.text(
                        "en incluant des fonctionnalités plus complexes pour créer des structures précises en quelques commandes assez simples.",
                        NamedTextColor.GRAY
                    )
                )
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            16, Material.FLINT,
            Component.text("GoBrush", NamedTextColor.YELLOW, TextDecoration.BOLD),
            Component.text("GoBrush vous permet de sculpter le terrain comme un artiste ! ", NamedTextColor.GRAY)
                .append(Component.text("Cliquez avec un silex en main ", NamedTextColor.RED))
                .append(
                    Component.text(
                        "pour ouvrir le menu et créez des collines, des vallées et d'autres formes impressionnantes avec facilité.",
                        NamedTextColor.GRAY
                    )
                )
        ) { event: InventoryClickEvent? -> }
        ) { }

        addButton(
            8, Material.FEATHER,
            Component.text("GoPaint", NamedTextColor.DARK_RED, TextDecoration.BOLD),
            Component.text("GoPaint est l'outil parfait pour modifier la texture du terrain. ", NamedTextColor.GRAY)
                .append(Component.text("Cliquez avec une plume en main ", NamedTextColor.RED))
                .append(
                    Component.text(
                        "pour ouvrir le menu, afin de changer la couleur de votre sol et donner vie à vos créations.",
                        NamedTextColor.GRAY
                    )
                )
        ) { event: InventoryClickEvent? -> }
        ) { }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }
}
