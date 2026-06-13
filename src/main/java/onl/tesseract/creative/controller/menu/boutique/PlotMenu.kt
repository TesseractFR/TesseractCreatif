package onl.tesseract.creative.controller.menu.boutique

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.creative.domain.plot.PlotWorld
import onl.tesseract.creative.service.player.PermissionService
import onl.tesseract.creative.service.plot.PlayerPlotService
import onl.tesseract.creative.util.HeadConstante
import onl.tesseract.creative.util.permissionService
import onl.tesseract.creative.util.playerPlotService
import onl.tesseract.lib.menu.CustomHeadItemBuilder
import onl.tesseract.lib.menu.ItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import org.bukkit.Material
import org.bukkit.entity.Player

class PlotMenu(
    player: Player, previous: Menu? = null,
) : BoutiqueCoreMenu(
    MenuSize.Three,
    Component.text("Achetez de nouveaux plots", NamedTextColor.BLUE, TextDecoration.BOLD),
    previous,
    player
) {

    private val playerPlotService: PlayerPlotService = playerPlotService()
    private val permissionService: PermissionService = permissionService()

    enum class PlotType(val price: Int, val plotWorld: PlotWorld) {
        PLOT100(100, PlotWorld.WORLD_100),
        PLOT250(225, PlotWorld.WORLD_250),
        PLOT500(450, PlotWorld.WORLD_500),
        PLOT1000(900, PlotWorld.WORLD_1000),
        ;
    }


    override fun placeButtons(viewer: Player) {
        super.placeButtons(viewer)
        for (slot in listOf(1, 4, 7, 9, 12, 14, 17, 20, 24)) {
            addButton(
                slot,
                ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).name(" ", NamedTextColor.WHITE)
                    .build()) {}
        }
        addPlotButton(10, HeadConstante.tete100, PlotType.PLOT100)
        addPlotButton(12, HeadConstante.tete250, PlotType.PLOT250)
        addPlotButton(14, HeadConstante.tete500, PlotType.PLOT500)
        addPlotButton(16, HeadConstante.tete1000, PlotType.PLOT1000)

        addBackButton()
        addCloseButton()
    }


    fun addPlotButton(index: Int, head: CustomHeadItemBuilder, plotType: PlotType) {
        addButton(
            index,
            head.name("Plot ${plotType.plotWorld.world} ", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD)
                    .lore()
                    .newline()
                    .append("Prix : ", NamedTextColor.GRAY)
                    .newline()
                    .append("${plotType.price} ", NamedTextColor.YELLOW,
                        setOf(TextDecoration.BOLD, TextDecoration.ITALIC))
                    .append("lys d'or/temporels", NamedTextColor.YELLOW,TextDecoration.ITALIC)
                    .newline()
                    .newline()
                    .append("--- Clic gauche ---", NamedTextColor.LIGHT_PURPLE)
                    .newline()
                    .append("Acheter en lys d'or", NamedTextColor.AQUA)
                    .newline()
                    .append("--- Clic droit ---", NamedTextColor.LIGHT_PURPLE)
                    .newline()
                    .append("Acheter en lys temporels", NamedTextColor.AQUA)

                    .buildLore()
                    .build()) { event ->
            run {
                if (event.isRightClick) {
                    buyPlotLysTemporel(plotType)
                } else if (event.isLeftClick) {
                    buyPlotLysDor(plotType)
                }
            }
        }
    }

    private fun buyPlotLysTemporel(plotType: PlotType) {
        confirmBuyLysTemporel(
            player,
            plotType.price,
            Component.text("Confirmer l'achat d'un plot ${plotType.price} pour ${plotType.price} lys temporels.")) {
            playerPlotService.addPlot(
                player,
                plotType.plotWorld)
            permissionService.updatePermission(player.uniqueId)
        }
    }

    private fun buyPlotLysDor(plotType: PlotType) {
        confirmBuyLysDor(
            player,
            plotType.price,
            Component.text("Confirmer l'achat d'un plot ${plotType.price} pour ${plotType.price} lys d'or.")) {
            playerPlotService.addPlot(
                player,
                plotType.plotWorld)
            permissionService.updatePermission(player.uniqueId)
        }
    }


}
