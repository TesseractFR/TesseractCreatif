package onl.tesseract.creative.controller.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.util.ItemLoreBuilder
import onl.tesseract.creative.controller.menu.boutique.CreativeBoutiqueMenu
import onl.tesseract.creative.util.HeadConstante.teteApprenti
import onl.tesseract.creative.util.HeadConstante.teteBatisseur
import onl.tesseract.creative.util.HeadConstante.teteConcepteur
import onl.tesseract.creative.util.HeadConstante.teteCreateur
import onl.tesseract.creative.util.HeadConstante.teteIngenieur
import onl.tesseract.creative.util.HeadConstante.teteVirtuose
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class RankMenu(previous: Menu? = null) :

        Menu(MenuSize.Three, Component.text("Grades", NamedTextColor.DARK_GREEN, TextDecoration.BOLD), previous) {

    override fun placeButtons(viewer: Player) {
        fill(
            ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).name(" ")
                    .build())

        addButton(0, createApprentiItem()) {}
        addButton(11, createConcepteurItem()) {}
        addButton(4, createCreateurItem()) {}
        addButton(15, createIngenieurItem()) {}
        addButton(8, createBatisseurItem()) {}
        addButton(22, createVirtuoseItem()) {
            CreativeBoutiqueMenu(viewer, this).open(viewer)
        }

        addBackButton()
        addCloseButton()
    }

    private fun createApprentiItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade initial !", NamedTextColor.RED, TextDecoration.BOLD)
            .newline().newline()
            .append("Nombre de plots maximum : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .newline()
            .append("Monde 100 : ", NamedTextColor.GOLD).append("1", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 250 : ", NamedTextColor.GOLD).append("1", NamedTextColor.WHITE, TextDecoration.BOLD)
        return teteApprenti
            .name("Apprenti", NamedTextColor.GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createConcepteurItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Obtenable après : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .append("4h de jeu", NamedTextColor.RED, TextDecoration.BOLD)
            .newline().newline()
            .append("Nombre de plots maximum : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .newline()
            .append("Monde 100 : ", NamedTextColor.GOLD).append("2", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 250 : ", NamedTextColor.GOLD).append("2", NamedTextColor.WHITE, TextDecoration.BOLD)
        return teteConcepteur
            .name("Concepteur", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createCreateurItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Obtenable après : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .append("16h de jeu", NamedTextColor.RED, TextDecoration.BOLD)
            .newline().newline()
            .append("Nombre de plots maximum : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .newline()
            .append("Monde 100 : ", NamedTextColor.GOLD).append("3", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 250 : ", NamedTextColor.GOLD).append("3", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 500 : ", NamedTextColor.GOLD).append("1", NamedTextColor.WHITE, TextDecoration.BOLD)
        return teteCreateur
            .name("Créateur", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createIngenieurItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Obtenable après : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .append("2j de jeu", NamedTextColor.RED, TextDecoration.BOLD)
            .newline().newline()
            .append("Nombre de plots maximum : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .newline()
            .append("Monde 100 : ", NamedTextColor.GOLD).append("4", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 250 : ", NamedTextColor.GOLD).append("4", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 500 : ", NamedTextColor.GOLD).append("2", NamedTextColor.WHITE, TextDecoration.BOLD)
        return teteIngenieur
            .name("Ingénieur", NamedTextColor.DARK_BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createBatisseurItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Obtenable après : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .append("7j de jeu", NamedTextColor.RED, TextDecoration.BOLD)
            .newline().newline()
            .append("Nombre de plots maximum : ", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .newline()
            .append("Monde 100 : ", NamedTextColor.GOLD).append("5", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 250 : ", NamedTextColor.GOLD).append("5", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 500 : ", NamedTextColor.GOLD).append("3", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 1000 : ", NamedTextColor.GOLD).append("1", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline().newline()
            .append("Obtention de ", NamedTextColor.GREEN, TextDecoration.BOLD).append("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
        return teteBatisseur
            .name("Bâtisseur", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }


    private fun createVirtuoseItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Grade PAYANT !", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .newline()
            .append(" (Clique pour ouvrir la boutique)", NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC)
            .newline().newline()
            .append("Nombre de plots maximum :", NamedTextColor.GRAY, TextDecoration.ITALIC)
            .newline()
            .append("Monde 100 : ", NamedTextColor.GOLD).append("6", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 200 : ", NamedTextColor.GOLD).append("6", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 500 : ", NamedTextColor.GOLD).append("4", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("Monde 1000 : ", NamedTextColor.GOLD).append("2", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline().newline()
            .append("Avantages supplémentaires : ", NamedTextColor.GREEN, TextDecoration.BOLD)
            .newline()
            .append("- Obtention de ", NamedTextColor.GOLD).append("VoxelSniper", NamedTextColor.DARK_AQUA, TextDecoration.BOLD)
            .newline()
            .append("- Commande ", NamedTextColor.GOLD).append("/nick", NamedTextColor.WHITE, TextDecoration.BOLD)
            .newline()
            .append("- Et bien plus encore...", NamedTextColor.GOLD, TextDecoration.ITALIC)
        return teteVirtuose
            .name("Virtuose", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

}
