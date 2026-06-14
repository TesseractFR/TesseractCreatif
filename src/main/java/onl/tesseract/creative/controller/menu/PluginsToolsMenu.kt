package onl.tesseract.creative.controller.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.util.ItemLoreBuilder
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class PluginsToolsMenu(previous: Menu? = null) :

    Menu(MenuSize.Three,
        Component.text("Outils/Plugins du serveur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD),
        previous) {

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                .build())
        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", NamedTextColor.WHITE)
                    .build()) {}
        }

        addButton(12, createFreePluginsItem()) {
            FreePluginsMenu(this).open(viewer)
        }
        addButton(14, createPaidPluginsItem()) {
            PaidPluginsMenu(this).open(viewer)
        }

        addBackButton()
        addCloseButton()
    }

    private fun createFreePluginsItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Découvrez tous les plugins et outils gratuits disponibles sur le serveur.", NamedTextColor.GRAY)

        return ItemBuilder(Material.BOOKSHELF)
            .name("Plugins gratuits", NamedTextColor.GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createPaidPluginsItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Découvrez les plugins et outils payants pour aller encore plus loin dans vos constructions.", NamedTextColor.GRAY)

        return ItemBuilder(Material.GOLD_INGOT)
            .name("Plugins payants", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }
}