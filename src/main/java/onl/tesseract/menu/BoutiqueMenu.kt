package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.menu.boutique.global.GlobalBoutiqueMenu
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player

class BoutiqueMenu(val player: TPlayer, previous: InventoryMenu? = null) :

    InventoryMenu(27, Component.text("Boutique de Tesseract", NamedTextColor.BLUE, TextDecoration.BOLD), previous) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(
            12, Material.AMETHYST_CLUSTER, Component.text("Tous les serveurs", NamedTextColor.LIGHT_PURPLE) ,
            Component.text("Cliquez pour afficher les cosmétiques disponibles sur tous les serveurs.", NamedTextColor.GRAY)
        ) {
            GlobalBoutiqueMenu(player, this).open(viewer)
        }
        addButton(
            14, Material.ELYTRA, Component.text("Créatif", NamedTextColor.LIGHT_PURPLE),
            Component.text("Cliquez pour afficher les achats disponibles uniquement sur le Créatif.", NamedTextColor.GRAY)
        ) {
            CreativeBoutiqueMenu(player, this).open(viewer)
        }
        // BoutiqueUtil.addBoutiqueButton(this, inventory.size - 5, player)
        addBackButton()
        addQuitButton()
        super.open(viewer)
    }
}
