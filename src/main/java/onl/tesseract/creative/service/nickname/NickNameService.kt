package onl.tesseract.creative.service.nickname

import onl.tesseract.creative.domain.nickname.Nickname
import onl.tesseract.creative.repository.generic.nickname.NicknameRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class NicknameService(private val nicknameRepository: NicknameRepository) {

    fun getNickname(playerUUID: UUID): String? {
        val nickname = getOrCreateNickname(playerUUID)
        return nickname.nickname
    }

    fun setNickname(playerUUID: UUID, nickname: String?) {
        val nicknameCreated = getOrCreateNickname(playerUUID)
        nicknameCreated.nickname = nickname
        nicknameRepository.save(nicknameCreated)
    }

    private fun getOrCreateNickname(playerUUID: UUID): Nickname {
        return nicknameRepository.getById(playerUUID) ?: Nickname(playerUUID, null)
    }
}
