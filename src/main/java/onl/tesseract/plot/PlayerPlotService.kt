package onl.tesseract.plot

import net.milkbowl.vault.permission.Permission
import onl.tesseract.plot.entity.PlotWorld
import onl.tesseract.plot.entity.PlayerPlotInfo
import onl.tesseract.plot.persistence.PlayerPlotInfoRepository
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.service.CreativeServices
import org.bukkit.OfflinePlayer
import java.util.*

/**
 * Service to interact with player's plots.
 */
class PlayerPlotService(private val repository: PlayerPlotInfoRepository) {
    fun getPlayerBonusPlot(uuid: UUID, plotWorld: PlotWorld): Int {
        val plotInfo = getOrCreatePlayerPlotInfo(uuid)
        return when (plotWorld) {
            PlotWorld.WORLD_100 -> plotInfo.nbPlot100
            PlotWorld.WORLD_250 -> plotInfo.nbPlot250
            PlotWorld.WORLD_500 -> plotInfo.nbPlot500
            PlotWorld.WORLD_1000 -> plotInfo.nbPlot1000
        }
    }

    fun getPlayerTotalPlot(uuid: UUID, plotWorld: PlotWorld): Int {
        val playerRank = CreativeServices[PlayerRankService::class.java].getPlayerRank(uuid);
        return getPlayerBonusPlot(uuid, plotWorld) + when (plotWorld) {
            PlotWorld.WORLD_100 -> playerRank.rankPlot.plot100;
            PlotWorld.WORLD_250 -> playerRank.rankPlot.plot250
            PlotWorld.WORLD_500 -> playerRank.rankPlot.plot500;
            PlotWorld.WORLD_1000 -> playerRank.rankPlot.plot1000;
        }
    }

    fun resetPermission(permission: Permission,player: OfflinePlayer) {
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_100);
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_250);
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_500);
        resetPlotWorldPermission(permission, player, PlotWorld.WORLD_1000);
    }

    private fun resetPlotWorldPermission(permission: Permission,player: OfflinePlayer,plotWorld: PlotWorld) {
        permission.playerAdd(
            plotWorld.world, player,
            "plots.plot." + getPlayerTotalPlot(player.uniqueId,plotWorld),
        )
    }

    private fun getOrCreatePlayerPlotInfo(player: UUID): PlayerPlotInfo {
        return repository.getById(player) ?: PlayerPlotInfo(player)
    }
}