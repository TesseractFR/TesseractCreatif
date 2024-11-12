package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.tesseractlib.util.ItemBuilder
import onl.tesseract.tesseractlib.util.ItemLoreBuilder
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class SpecialBlockMenu(val player: Player, previous: InventoryMenu? = null) :

    InventoryMenu(27, Component.text("Blocs spéciaux", NamedTextColor.DARK_AQUA, TextDecoration.BOLD), previous) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(0, createCommandBlockItem()) {
            player.inventory.addItem(ItemStack(Material.COMMAND_BLOCK))
        }

        addButton(10, createChainCommandBlockItem()) {
            player.inventory.addItem(ItemStack(Material.CHAIN_COMMAND_BLOCK))
        }

        addButton(2, createRepeatingCommandBlockItem()) {
            player.inventory.addItem(ItemStack(Material.REPEATING_COMMAND_BLOCK))
        }

        addButton(12, createCommandBlockMinecartItem()) {
            player.inventory.addItem(ItemStack(Material.COMMAND_BLOCK_MINECART))
        }

        addButton(4, createStructureBlockItem()) {
            player.inventory.addItem(ItemStack(Material.STRUCTURE_BLOCK))
        }

        addButton(14, createJigsawBlockItem()) {
            player.inventory.addItem(ItemStack(Material.JIGSAW))
        }

        addButton(6, createStructureVoidItem()) {
            player.inventory.addItem(ItemStack(Material.STRUCTURE_VOID))
        }

        addButton(16, createBarrierItem()) {
            player.inventory.addItem(ItemStack(Material.BARRIER))
        }

        addButton(8, createLightItem()) {
            player.inventory.addItem(ItemStack(Material.LIGHT))
        }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    private fun createCommandBlockItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.COMMAND_BLOCK)
            .name("Bloc de commande", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createChainCommandBlockItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.CHAIN_COMMAND_BLOCK)
            .name("Bloc de commande en chaîne", NamedTextColor.GREEN, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createRepeatingCommandBlockItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.REPEATING_COMMAND_BLOCK)
            .name("Bloc de commande à répétition", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createCommandBlockMinecartItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.COMMAND_BLOCK_MINECART)
            .name("Wagonnet à bloc de commande", NamedTextColor.DARK_RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createStructureBlockItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.STRUCTURE_BLOCK)
            .name("Bloc de structure", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createJigsawBlockItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.JIGSAW)
            .name("Bloc de puzzle", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createStructureVoidItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.STRUCTURE_VOID)
            .name("Vide de structure", NamedTextColor.BLUE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createBarrierItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.BARRIER)
            .name("Barrière invisible", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createLightItem(): ItemStack {
        val ilb = ItemLoreBuilder()
            .newline()
            .append("AJOUTER DESCRIPTION", NamedTextColor.GRAY)
        return ItemBuilder(Material.LIGHT)
            .name("Lumière", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

}
