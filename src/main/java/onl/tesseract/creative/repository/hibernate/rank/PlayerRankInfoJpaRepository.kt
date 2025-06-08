package onl.tesseract.creative.repository.hibernate.rank

import onl.tesseract.creative.domain.rank.PlayerRankInfo
import onl.tesseract.creative.repository.generic.rank.PlayerRankInfoRepository
import onl.tesseract.creative.repository.hibernate.rank.entity.PlayerRankInfoEntity
import onl.tesseract.creative.repository.hibernate.rank.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PlayerRankInfoJpaRepository : JpaRepository<PlayerRankInfoEntity, UUID>

@Component
class PlayerRankInfoJpaRepositoryAdapter(private val repository: PlayerRankInfoJpaRepository) :
        PlayerRankInfoRepository {
    override fun getById(id: UUID): PlayerRankInfo? {
        return repository.findByIdOrNull(id)
                ?.toDomain()
    }

    override fun save(entity: PlayerRankInfo): PlayerRankInfo {
        return repository.save(entity.toEntity())
                .toDomain()
    }

}