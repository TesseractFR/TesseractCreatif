package onl.tesseract.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import onl.tesseract.dao.CreativePlayerInfoDAO;
import onl.tesseract.entity.player.CreativePlayerInfo;

import java.time.Duration;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CreativePlayerService {
    @Getter
    private static final CreativePlayerService instance = new CreativePlayerService();

    CreativePlayerInfoDAO creativePlayerInfoDAO = CreativePlayerInfoDAO.getInstance();

    public CreativePlayerInfo get(UUID uniqueId) {

        CreativePlayerInfo creativePlayerInfo = creativePlayerInfoDAO.get(uniqueId);

        if (creativePlayerInfo == null) {
            creativePlayerInfo = createCreativePlayerInfo(uniqueId);
        }

        return creativePlayerInfo;
    }


    private CreativePlayerInfo createCreativePlayerInfo(UUID uniqueId) {
        CreativePlayerInfo creativePlayerInfo = new CreativePlayerInfo();
        creativePlayerInfo.setUuid(uniqueId);
        creativePlayerInfo.setTimePlayed(Duration.ZERO);
        creativePlayerInfoDAO.create(creativePlayerInfo);
        return creativePlayerInfo;
    }


}
