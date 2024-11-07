package onl.tesseract.player;

import lombok.Getter;
import net.milkbowl.vault.permission.Permission;
import onl.tesseract.Creatif;
import onl.tesseract.entity.player.CreativePlayerInfo;
import onl.tesseract.rank.entity.PlayerRank;
import onl.tesseract.rank.entity.Rank;
import onl.tesseract.rank.entity.StaffRank;
import onl.tesseract.plot.PlotManager;
import onl.tesseract.service.CreativePlayerService;
import onl.tesseract.tesseractlib.player.TPlayer;
import org.bukkit.OfflinePlayer;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

public class CreativePlayer extends TPlayer {

    private final UUID uuid;
    @Getter
    private final CreativePlayerInfo creativePlayerInfo;

    public CreativePlayer(OfflinePlayer player) {
        super(player);
        uuid = player.getUniqueId();
        creativePlayerInfo = CreativePlayerService.getInstance().get(uuid);
    }

    public PlayerRank getPlayerRank() {
        return this.creativePlayerInfo.getPlayerRankInfo().getPlayerRank();
    }

    public void updatePermission() {
        Permission permissions = Creatif.getInstance().getPermissions();
        //Unset Perm
        Consumer<Rank> playerUnrankConsumer = rank -> permissions.playerRemoveGroup(null, getOfflinePlayer(), rank.getPermGroup());
        Arrays.stream(PlayerRank.values()).forEach(playerUnrankConsumer);
        Arrays.stream(StaffRank.values()).forEach(playerUnrankConsumer);
        PlotManager.getInstance().resetPlotPermissions(permissions, this);
        //
    }

    public int getBonusPlot100() {
        return creativePlayerInfo.getPlayerPlotInfo().getNbPlot100();
    }

    public int getBonusPlot250() {
        return creativePlayerInfo.getPlayerPlotInfo().getNbPlot250();
    }

    public int getBonusPlot500() {
        return creativePlayerInfo.getPlayerPlotInfo().getNbPlot500();
    }

    public int getBonusPlot1000() {
        return creativePlayerInfo.getPlayerPlotInfo().getNbPlot1000();
    }


}
