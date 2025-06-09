package onl.tesseract.creative.repository.hibernate.permpack

import onl.tesseract.creative.domain.permpack.PlayerPermPackInfo
import onl.tesseract.creative.repository.generic.permpack.PlayerPermPackInfoRepository
import onl.tesseract.creative.repository.hibernate.permpack.entity.PlayerPermPackInfoEntity
import onl.tesseract.creative.repository.hibernate.permpack.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

import java.util.UUID

@Repository
interface PlayerPermPackInfoJpaRepository : JpaRepository<PlayerPermPackInfoEntity, UUID>

@Component
class PlayerPermPackInfoJPARepositoryAdapter(private val repository: PlayerPermPackInfoJpaRepository) :
        PlayerPermPackInfoRepository {
    override fun getById(id: UUID): PlayerPermPackInfo? {
        return repository.findByIdOrNull(id)
                ?.toDomain()
    }

    override fun save(entity: PlayerPermPackInfo): PlayerPermPackInfo {
        return repository.save(entity.toEntity())
                .toDomain()
    }

}