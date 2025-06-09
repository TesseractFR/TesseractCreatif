package onl.tesseract.creative.repository.hibernate.timeplayed

import onl.tesseract.creative.repository.generic.timeplayed.PlayerTimePlayedRepository
import onl.tesseract.creative.repository.hibernate.timeplayed.entity.PlayerTimePlayedInfoEntity
import onl.tesseract.creative.repository.hibernate.timeplayed.entity.toEntity
import onl.tesseract.creative.domain.timeplayed.PlayerTimePlayedInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PlayerTimePlayedJpaRepository : JpaRepository<PlayerTimePlayedInfoEntity, UUID>

@Component
class PlayerTimePlayedJpaRepositoryAdapter(private val repository: PlayerTimePlayedJpaRepository) :
        PlayerTimePlayedRepository {
    override fun getById(id: UUID): PlayerTimePlayedInfo? {
        return repository.findByIdOrNull(id)
                ?.toDomain()
    }

    override fun save(entity: PlayerTimePlayedInfo): PlayerTimePlayedInfo {
        return repository.save(entity.toEntity())
                .toDomain()
    }
}
