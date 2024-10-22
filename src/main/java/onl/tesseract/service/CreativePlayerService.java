package onl.tesseract.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import onl.tesseract.dao.CreativePlayerInfoDAO;
import onl.tesseract.dao.PlayerPlotInfoDAO;
import onl.tesseract.dao.PlayerRankInfoDAO;
import onl.tesseract.entity.player.CreativePlayerInfo;
import onl.tesseract.entity.player.PlayerPlotInfo;
import onl.tesseract.entity.player.rank.PlayerRank;
import onl.tesseract.entity.player.rank.PlayerRankInfo;

import java.time.Duration;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CreativePlayerService {
    @Getter
    private static final CreativePlayerService instance = new CreativePlayerService();

    CreativePlayerInfoDAO creativePlayerInfoDAO = CreativePlayerInfoDAO.getInstance();
    PlayerRankInfoDAO playerRankInfoDAO = PlayerRankInfoDAO.getInstance();
    PlayerPlotInfoDAO playerPlotInfoDAO = PlayerPlotInfoDAO.getInstance();

    public CreativePlayerInfo get(UUID uniqueId) {

        CreativePlayerInfo creativePlayerInfo = creativePlayerInfoDAO.get(uniqueId);

        if (creativePlayerInfo == null) {
            creativePlayerInfo = createCreativePlayerInfo(uniqueId);
        }
        if(creativePlayerInfo.getPlayerRankInfo() == null) {
            PlayerRankInfo playerRankInfo = createPlayerRankInfo(uniqueId);
            playerRankInfoDAO.create(playerRankInfo);
            creativePlayerInfo.setPlayerRankInfo(playerRankInfo);
        }
        if(creativePlayerInfo.getPlayerPlotInfo() == null) {
            PlayerPlotInfo playerPlotInfo = createPlayerPlotInfo(uniqueId);
            playerPlotInfoDAO.create(playerPlotInfo);
            creativePlayerInfo.setPlayerPlotInfo(playerPlotInfo);
        }

        return creativePlayerInfo;
    }

    private PlayerPlotInfo createPlayerPlotInfo(UUID uniqueId) {
        PlayerPlotInfo playerPlotInfo = new PlayerPlotInfo();
        playerPlotInfo.setUuid(uniqueId);
        return playerPlotInfo;
    }

    private PlayerRankInfo createPlayerRankInfo(UUID uniqueId) {
        PlayerRankInfo playerRankInfo = new PlayerRankInfo();
        playerRankInfo.setUuid(uniqueId);
        playerRankInfo.setPlayerRank(PlayerRank.APPRENTI);
        playerRankInfo.setStaffRank(null);
        playerRankInfo.setVirtuose(false);
        return playerRankInfo;
    }

    private CreativePlayerInfo createCreativePlayerInfo(UUID uniqueId) {
        CreativePlayerInfo creativePlayerInfo = new CreativePlayerInfo();
        creativePlayerInfo.setUuid(uniqueId);
        creativePlayerInfo.setTimePlayed(Duration.ZERO);
        creativePlayerInfoDAO.create(creativePlayerInfo);
        return creativePlayerInfo;
    }

    public CreativePlayerInfo create(UUID uniqueId) {
        CreativePlayerInfo tPlayerFreebuildInfo = new CreativePlayerInfo();
        tPlayerFreebuildInfo.setUuid(uniqueId);

        return tPlayerFreebuildInfo;
    }


}
