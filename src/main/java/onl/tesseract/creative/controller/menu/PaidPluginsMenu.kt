package onl.tesseract.creative.controller.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.controller.menu.boutique.CreativeBoutiqueMenu
import onl.tesseract.creative.controller.menu.boutique.PrestigeMenu
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.creative.controller.menu.boutique.RentPluginsMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class PaidPluginsMenu(previous: Menu? = null) :

    Menu(
        MenuSize.Three,
        Component.text("Plugins payants", NamedTextColor.GOLD, TextDecoration.BOLD),
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

        addButton(11, createArceonItem()) {
            CreativeBoutiqueMenu(viewer, this).open(viewer)
        }
        addButton(13, createMetaBrushItem()) {
            PrestigeMenu(viewer, this).open(viewer)
        }
        addButton(15, createEzEditsItem()) {
            PrestigeMenu(viewer, this).open(viewer)
        }

        addBackButton()
        addCloseButton()
    }

    private fun createArceonItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Disponible avec le grade ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .append("VIRTUOSE ", NamedTextColor.AQUA, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("Arceon ajoute des options intéressantes à WorldEdit, ", NamedTextColor.GRAY)
            .append("en incluant des fonctionnalités plus complexes pour créer des structures précises en quelques commandes assez simples.", NamedTextColor.GRAY)
        return ItemBuilder(Material.PAPER)
            .name("Arceon", NamedTextColor.GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createMetaBrushItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Disponible avec le grade ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .append("PRESTIGE ", NamedTextColor.GOLD, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("Metabrush permet d'ajouter des commandes puissantes pour améliorer vos capacités de construction.", NamedTextColor.GRAY)
            .append(" Ce plugin est parfait pour les constructions techniques avancées.", NamedTextColor.GRAY)
        return ItemBuilder(Material.BRUSH)
            .name("Metabrush", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createEzEditsItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Disponible avec le grade ", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .append("PRESTIGE ", NamedTextColor.GOLD, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("EzEdits est une extension de WE/FAWE qui ajoute de nombreuses nouvelles commandes,", NamedTextColor.GRAY)
            .append(" motifs, masques et outils pour vous aider à réaliser des créations incroyables !", NamedTextColor.GRAY)
        return ItemBuilder(Material.CLOCK)
            .name("EzEdits", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }
}