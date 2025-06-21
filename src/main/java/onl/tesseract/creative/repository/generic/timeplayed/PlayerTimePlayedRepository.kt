package onl.tesseract.creative.repository.generic.timeplayed

import onl.tesseract.creative.repository.generic.Repository
import onl.tesseract.creative.domain.timeplayed.PlayerTimePlayedInfo
import java.util.UUID

interface PlayerTimePlayedRepository : Repository<PlayerTimePlayedInfo, UUID>