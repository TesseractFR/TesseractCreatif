package onl.tesseract.creative.repository.hibernate.nickname

import onl.tesseract.creative.domain.nickname.Nickname
import onl.tesseract.creative.repository.generic.nickname.NicknameRepository
import onl.tesseract.creative.repository.hibernate.nickname.entity.NicknameEntity
import onl.tesseract.creative.repository.hibernate.nickname.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NicknameJpaRepository : JpaRepository<NicknameEntity, UUID>

@Component
class NicknameJpaRepositoryAdapter(private val nicknameRepository: NicknameJpaRepository) : NicknameRepository {
    override fun getById(id: UUID): Nickname? {
        return nicknameRepository.findByIdOrNull(id)
                ?.toDomain()
    }

    override fun save(entity: Nickname): Nickname {
        return nicknameRepository.save(entity.toEntity())
                .toDomain()
    }
}
