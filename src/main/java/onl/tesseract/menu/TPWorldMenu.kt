package onl.tesseract.menu

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.plot.entity.PlotWorld
import onl.tesseract.tesseractlib.util.ItemBuilder
import onl.tesseract.tesseractlib.util.ItemLoreBuilder
import onl.tesseract.tesseractlib.util.menu.InventoryMenu
import onl.tesseract.world.WorldManager
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class TPWorldMenu(previous: InventoryMenu? = null) :

    InventoryMenu(27, Component.text("Menu des téléportations", NamedTextColor.BLUE, TextDecoration.BOLD), previous) {

    override fun open(viewer: Player) {
        fill(Material.GRAY_STAINED_GLASS_PANE, " ")

        addButton(
            0, createWorld100Item()) {
            teleport(viewer, PlotWorld.WORLD_100)
        }

        addButton(
            11, createWorld250Item()) {
            teleport(viewer, PlotWorld.WORLD_250)
        }

        addButton(
            4, createWorld500Item()) {
            teleport(viewer, PlotWorld.WORLD_500)
        }

        addButton(
            15, createWorld1000Item()) {
            teleport(viewer, PlotWorld.WORLD_1000)
        }

        addButton(8, createEventWorldItem()) {
            teleport(viewer, PlotWorld.WORLD_EVENT)
        }

        addBackButton()
        addQuitButton()
        super.open(viewer)
    }

    private fun teleport(viewer: Player, plotWorld: PlotWorld) {
        viewer.teleport(WorldManager.instance.getWorldSpawn(plotWorld))
    }

    private fun createWorld100Item(): ItemStack {
        val tete100 = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDFlNzhmZjQ3NjNlOWFkMWE5OThjNzI4ZjcxZmE1ZGJiZDYxNjRhMjdjYTFmMGU0MjMyYzQxZDc0MjA4MTgwYSJ9fX0=", "41e78ff4763e9ad1a998c728f71fa5dbbd6164a27ca1f0e4232c41d74208180a")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour vous téléporter dans le monde 100x100.", NamedTextColor.GRAY)
        return ItemBuilder(tete100)
            .name("Monde 100x100", NamedTextColor.AQUA, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createWorld250Item(): ItemStack {
        val tete250 = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDNlMWM1ZTY1ZTEzNTU5YmY2MGUxNTRmMTdmNmFmM2E4ZTU2MDhhNDk4N2VjZDFlMGZhZTc1MWM2ZjgyNzI2In19fQ==", "d3e1c5e65e13559bf60e154f17f6af3a8e5608a4987ecd1e0fae751c6f82726")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour vous téléporter dans le monde 250x250.", NamedTextColor.GRAY)
        return ItemBuilder(tete250)
            .name("Monde 250x250", NamedTextColor.YELLOW, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createWorld500Item(): ItemStack {
        val tete500 = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQ4NTlmN2IzY2RmZGFkNDcxODI4ODRlMTI3ZjQ2MWZlOGY5ZmM1MmY3ZDE1MDQyN2MxMTcwNzliMDkyNGUzIn19fQ==", "4d859f7b3cdfdad47182884e127f461fe8f9fc52f7d150427c117079b0924e3")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour vous téléporter dans le monde 500x500.", NamedTextColor.GRAY)
        return ItemBuilder(tete500)
            .name("Monde 500x500", NamedTextColor.GOLD, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createWorld1000Item(): ItemStack {
        val tete1000 = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWU0ZGNmNzFiMGI2ODUyYjU0MWNkMjNhMzFiODg0NjlhZjU4NDI5YWQ1MjJmNTI4MjhmM2E4MGIzNjI5ZWYyIn19fQ==", "5e4dcf71b0b6852b541cd23a31b88469af58429ad522f52828f3a80b3629ef2")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour vous téléporter dans le monde 1000x1000.", NamedTextColor.GRAY)
        return ItemBuilder(tete1000)
            .name("Monde 1000x1000", NamedTextColor.LIGHT_PURPLE, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

    private fun createEventWorldItem(): ItemStack {
        val teteEvent = getCustomHead("", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFhZDZmZmFmNmZiOWE4ZWVhOGYzZGJlYTZkZGYzNDcyYTBhNTQ2YjVlMTk0YmQ1NWI0MzNiZDlkMTU4OTMwIn19fQ==", "faad6ffaf6fb9a8eea8f3dbea6ddf3472a0a546b5e194bd55b433bd9d158930")
        val ilb = ItemLoreBuilder()
            .newline()
            .append("Cliquez pour vous téléporter dans le monde Event.", NamedTextColor.GRAY)
        return ItemBuilder(teteEvent)
            .name("Monde Event", NamedTextColor.RED, TextDecoration.BOLD)
            .lore(ilb.get())
            .build()
    }

}
