package onl.tesseract.creative.repository.generic.nickname

import onl.tesseract.creative.domain.nickname.Nickname
import onl.tesseract.creative.repository.generic.Repository
import java.util.UUID

interface NicknameRepository : Repository<Nickname, UUID>