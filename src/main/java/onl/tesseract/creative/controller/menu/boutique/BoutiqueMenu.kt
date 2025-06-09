package onl.tesseract.creative.controller.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.core.boutique.menu.GlobalBoutiqueMenu
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import org.bukkit.Material
import org.bukkit.entity.Player

class BoutiqueMenu(player: Player, previous: Menu? = null) :

        BoutiqueCoreMenu(
            MenuSize.Three,
            Component.text("Boutique de Tesseract", NamedTextColor.BLUE, TextDecoration.BOLD),
            previous, player) {

    override fun placeButtons(viewer: Player) {
        fill(ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).build())

        addButton(
            12, ItemBuilder(Material.AMETHYST_CLUSTER)
                    .name(Component.text("Tous les serveurs", NamedTextColor.LIGHT_PURPLE))
                    .lore()
                    .append(
                        "Cliquez pour afficher les cosmétiques disponibles sur tous les serveurs.",
                        NamedTextColor.GRAY)
                    .buildLore()
                    .build()
        ) {
            GlobalBoutiqueMenu(player.uniqueId, this).open(viewer)
        }
        addButton(
            14, ItemBuilder(Material.BRICKS)
                    .name(Component.text("Créatif", NamedTextColor.LIGHT_PURPLE))
                    .lore()
                    .append(
                        Component.text(
                            "Cliquez pour afficher les achats disponibles uniquement sur le Créatif.",
                            NamedTextColor.GRAY))
                    .buildLore()
                    .build()
        ) {
            CreativeBoutiqueMenu(player, this).open(viewer)
        }
        addBoutiqueButton(player, size.size - 5)
        addBackButton()
        addCloseButton()
    }
}
