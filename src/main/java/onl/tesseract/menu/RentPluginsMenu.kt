package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.player.TPlayer
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class RentPluginsMenu(val player: TPlayer, previous: InventoryMenu) :

    InventoryMenu(27, Component.text("Louer des plugins et outils", NamedTextColor.DARK_GREEN, TextDecoration.BOLD), previous) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(
            0, Material.PAPER,
            Component.text("Arceon", NamedTextColor.GREEN, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            1, tetePlayerParticles,
            Component.text("PlayerParticles", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            2, Material.BRUSH,
            Component.text("MetaBrush", NamedTextColor.DARK_BLUE, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addButton(
            3, Material.ARMOR_STAND,
            Component.text("DisplayEntities", NamedTextColor.DARK_RED, TextDecoration.BOLD),
            Component.text("AJOUTER DESCRIPTION", NamedTextColor.GRAY, TextDecoration.ITALIC)
        ) { }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    companion object {
        val tetePlayerParticles: ItemStack = getCustomHead(
            "",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQ2MWQ5ZDA2YzBiZjRhN2FmNGIxNmZkMTI4MzFlMmJlMGNmNDJlNmU1NWU5YzBkMzExYTJhODk2NWEyM2IzNCJ9fX0=",
            "4461d9d06c0bf4a7af4b16fd12831e2be0cf42e6e55e9c0d311a2a8965a23b34"
        )
    }
}
