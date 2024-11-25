package onl.tesseract.nickname

import onl.tesseract.nickname.entity.Nickname
import onl.tesseract.nickname.persistence.NicknameRepository
import java.util.UUID

class NicknameService(private val nicknameRepository: NicknameRepository) {

    fun getNickname(playerUUID: UUID): String? {
        val nicknameCreated = getOrCreateNickname(playerUUID)
        return nicknameCreated.nickname
    }

    fun setNickname(playerUUID: UUID, nickname: String?) {
        val nicknameCreated = getOrCreateNickname(playerUUID)
        nicknameCreated.nickname = nickname
        nicknameRepository.save(nicknameCreated)
    }

    private fun getOrCreateNickname(playerUUID: UUID): Nickname {
        return nicknameRepository.getById(playerUUID) ?: Nickname(playerUUID)
    }
}
