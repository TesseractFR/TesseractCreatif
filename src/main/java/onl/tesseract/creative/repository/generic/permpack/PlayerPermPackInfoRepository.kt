package onl.tesseract.creative.repository.generic.permpack

import onl.tesseract.creative.domain.permpack.PlayerPermPackInfo
import onl.tesseract.creative.repository.generic.Repository
import java.util.UUID

interface PlayerPermPackInfoRepository : Repository<PlayerPermPackInfo, UUID> {
}