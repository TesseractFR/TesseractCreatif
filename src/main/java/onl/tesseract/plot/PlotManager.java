package onl.tesseract.plot;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.milkbowl.vault.permission.Permission;
import onl.tesseract.player.CreativePlayer;
import onl.tesseract.entity.PlotWorld;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlotManager {
    @Getter
    private static final PlotManager instance = new PlotManager();



    public void resetPlotPermissions(Permission permission,CreativePlayer creativePlayer) {
        permission.playerAdd(PlotWorld.WORLD_100.getWorld(),creativePlayer.getOfflinePlayer(),
                "plots.plot."+getPlot100(creativePlayer));
        permission.playerAdd(PlotWorld.WORLD_250.getWorld(),creativePlayer.getOfflinePlayer(),
                "plots.plot."+getPlot250(creativePlayer));
        permission.playerAdd(PlotWorld.WORLD_500.getWorld(),creativePlayer.getOfflinePlayer(),
                "plots.plot."+getPlot500(creativePlayer));
        permission.playerAdd(PlotWorld.WORLD_1000.getWorld(),creativePlayer.getOfflinePlayer(),
                "plots.plot."+getPlot1000(creativePlayer));
    }

    private int getPlot1000(CreativePlayer creativePlayer) {
        return creativePlayer.getPlayerRank().getRankPlot().getPlot1000() + creativePlayer.getBonusPlot1000();
    }

    private int getPlot500(CreativePlayer creativePlayer) {
        return creativePlayer.getPlayerRank().getRankPlot().getPlot500() + creativePlayer.getBonusPlot500();
    }

    private int getPlot250(CreativePlayer creativePlayer) {
        return creativePlayer.getPlayerRank().getRankPlot().getPlot250() + creativePlayer.getBonusPlot250();
    }

    private int getPlot100(CreativePlayer creativePlayer) {
        return creativePlayer.getPlayerRank().getRankPlot().getPlot100() + creativePlayer.getBonusPlot100();
    }
}
