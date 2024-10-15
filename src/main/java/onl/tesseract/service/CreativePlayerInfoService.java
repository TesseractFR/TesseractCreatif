package onl.tesseract.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import onl.tesseract.dao.CreativePlayerInfoDAO;
import onl.tesseract.entity.player.CreativePlayerInfo;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CreativePlayerInfoService {
    @Getter
    private static final CreativePlayerInfoService instance = new CreativePlayerInfoService();

    CreativePlayerInfoDAO dao = CreativePlayerInfoDAO.getInstance();


    public CreativePlayerInfo get(UUID uniqueId) {
        CreativePlayerInfo tPlayerFreebuildInfo = dao.get(uniqueId);
        if (tPlayerFreebuildInfo == null) {
            tPlayerFreebuildInfo = new CreativePlayerInfo();
            tPlayerFreebuildInfo.setUuid(uniqueId);
            dao.create(tPlayerFreebuildInfo);
            log.info("Created new tPlayerFreebuildInfo: {}", tPlayerFreebuildInfo.getUuid().toString());
        }
        return tPlayerFreebuildInfo;
    }
}
