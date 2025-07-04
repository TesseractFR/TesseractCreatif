package onl.tesseract.creative.service.plot

import net.milkbowl.vault.permission.Permission
import onl.tesseract.creative.service.rank.PlayerRankService
import onl.tesseract.creative.domain.plot.PlayerPlotInfo
import onl.tesseract.creative.domain.plot.PlotWorld
import onl.tesseract.creative.repository.generic.plot.PlayerPlotInfoRepository
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player
import org.springframework.stereotype.Service
import java.util.UUID

/**
 * Service to interact with player's plots.
 */
@Service
class PlayerPlotService(
    private val repository: PlayerPlotInfoRepository,
    private val playerRankService: PlayerRankService,
) {
    fun getPlayerBonusPlot(uuid: UUID, plotWorld: PlotWorld): Int {
        val plotInfo = getOrCreatePlayerPlotInfo(uuid)
        return when (plotWorld) {
            PlotWorld.WORLD_100 -> plotInfo.nbPlot100
            PlotWorld.WORLD_250 -> plotInfo.nbPlot250
            PlotWorld.WORLD_500 -> plotInfo.nbPlot500
            PlotWorld.WORLD_1000 -> plotInfo.nbPlot1000
            PlotWorld.WORLD_EVENT -> 0
        }
    }

    fun getPlayerTotalPlot(uuid: UUID, plotWorld: PlotWorld): Int {
        val playerRank = playerRankService.getPlayerRank(uuid);
        return getPlayerBonusPlot(uuid, plotWorld) + when (plotWorld) {
            PlotWorld.WORLD_100 -> playerRank.rankPlot.plot100;
            PlotWorld.WORLD_250 -> playerRank.rankPlot.plot250
            PlotWorld.WORLD_500 -> playerRank.rankPlot.plot500;
            PlotWorld.WORLD_1000 -> playerRank.rankPlot.plot1000;
            PlotWorld.WORLD_EVENT -> 1
        }
    }

    fun resetPermission(permission: Permission, player: OfflinePlayer) {
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_100);
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_250);
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_500);
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_1000);
    }

    private fun resetPlotWorldPermission(permission: Permission, player: OfflinePlayer, plotWorld: PlotWorld) {

        val playerTotalPlot = getPlayerTotalPlot(player.uniqueId, plotWorld)
        for (i in 0 until playerTotalPlot) {
            permission.playerRemove(
                plotWorld.world, player,
                "plots.plot.$i")
        }
        permission.playerAdd(
            plotWorld.world, player,
            "plots.plot.$playerTotalPlot")
    }

    private fun getOrCreatePlayerPlotInfo(player: UUID): PlayerPlotInfo {
        return repository.getById(player) ?: PlayerPlotInfo(player)
    }

    fun addPlot(player: Player, plotWorld: PlotWorld) {
        val plotInfo = getOrCreatePlayerPlotInfo(player.uniqueId)
        when (plotWorld) {
            PlotWorld.WORLD_100 -> plotInfo.nbPlot100 += 1;
            PlotWorld.WORLD_250 -> plotInfo.nbPlot250 += 1;
            PlotWorld.WORLD_500 -> plotInfo.nbPlot500 += 1;
            PlotWorld.WORLD_1000 -> plotInfo.nbPlot1000 += 1;
            PlotWorld.WORLD_EVENT -> throw IllegalArgumentException();
        }
        repository.save(plotInfo)
    }
}