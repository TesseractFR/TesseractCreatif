package onl.tesseract.menu.boutique;

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import onl.tesseract.lib.menu.CustomHeadItemBuilder
import onl.tesseract.lib.menu.Menu
import onl.tesseract.lib.menu.MenuSize
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.player.PermissionService
import onl.tesseract.plot.PlayerPlotService
import onl.tesseract.plot.entity.PlotWorld
import onl.tesseract.util.HeadConstante
import org.bukkit.entity.Player

class PlotMenu(player: Player, previous: Menu? = null) : BoutiqueCoreMenu(
    MenuSize.Two,
    Component.text("Achetez de nouveaux plots", NamedTextColor.BLUE, TextDecoration.BOLD),
    previous,
    player) {


    enum class PlotType(val price: Int, val plotWorld: PlotWorld) {
        PLOT100(100, PlotWorld.WORLD_100),
        PLOT250(250, PlotWorld.WORLD_250),
        PLOT500(500, PlotWorld.WORLD_500),
        PLOT1000(1000, PlotWorld.WORLD_1000),
        ;
    }


    override fun placeButtons(viewer: Player) {
        super.placeButtons(viewer)

        addPlotButton(1, HeadConstante.tete100, PlotType.PLOT100)
        addPlotButton(3, HeadConstante.tete250, PlotType.PLOT250)
        addPlotButton(5, HeadConstante.tete500, PlotType.PLOT500)
        addPlotButton(7, HeadConstante.tete1000, PlotType.PLOT1000)

        addBackButton()
        addCloseButton()
    }


    fun addPlotButton(index: Int, head: CustomHeadItemBuilder, plotType: PlotType) {
        addButton(
            index,
            head.name("Plot ${plotType.price} ", NamedTextColor.LIGHT_PURPLE)
                    .lore()
                    .newline()
                    .newline()
                    .append("Prix : ", NamedTextColor.GRAY)
                    .newline()
                    .append("${plotType.price}", NamedTextColor.GOLD)
                    .newline()
                    .newline()
                    .append("--- Clic gauche ---", NamedTextColor.LIGHT_PURPLE)
                    .newline()
                    .append("Acheter en lys d'or", NamedTextColor.AQUA)
                    .newline()
                    .append("--- Clic droit ---", NamedTextColor.LIGHT_PURPLE)
                    .newline()
                    .append("Acheter en lys temporel", NamedTextColor.AQUA)

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
            Component.text("Confirmer l'achat d'un plot ${plotType.price} pour ${plotType.price} lys temporel.")) {
            ServiceContainer[PlayerPlotService::class.java].addPlot(
                player,
                plotType.plotWorld)
            ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
        }
    }

    private fun buyPlotLysDor(plotType: PlotType) {
        confirmBuyLysDor(
            player,
            plotType.price,
            Component.text("Confirmer l'achat d'un plot ${plotType.price} pour ${plotType.price} lys d'or.")) {
            ServiceContainer[PlayerPlotService::class.java].addPlot(
                player,
                plotType.plotWorld)
            ServiceContainer[PermissionService::class.java].updatePermission(player.uniqueId)
        }
    }


}
