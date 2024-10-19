package onl.tesseract;

import lombok.Getter;
import onl.tesseract.entity.player.CreativePlayerInfo;
import onl.tesseract.entity.player.rank.Rank;
import onl.tesseract.service.CreativePlayerService;
import onl.tesseract.tesseractlib.player.TPlayer;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class CreativePlayer extends TPlayer {

    private final UUID uuid;
    @Getter
    private final CreativePlayerInfo creativePlayerInfo;

    public CreativePlayer(OfflinePlayer player) {
        super(player);
        uuid = player.getUniqueId();
        creativePlayerInfo = CreativePlayerService.getInstance().get(uuid);
    }

    public Rank getRank() {
        return this.creativePlayerInfo.getPlayerRankInfo().getPlayerRank();
    }

    public void updatePermission() {
    }
}
