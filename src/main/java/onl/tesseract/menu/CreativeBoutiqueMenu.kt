package onl.tesseract.menu;

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.player.CreativePlayer
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player

class CreativeBoutiqueMenu(val player: TPlayer, previous: InventoryMenu) :

    InventoryMenu(27, Component.text("Menu du Créatif", NamedTextColor.RED, TextDecoration.BOLD), previous) {

        override fun open(viewer: Player) {
            fill(Material.GRAY_STAINED_GLASS_PANE, " ")

            addBackButton()
            addQuitButton()
            super.open(viewer)
        }
    }
