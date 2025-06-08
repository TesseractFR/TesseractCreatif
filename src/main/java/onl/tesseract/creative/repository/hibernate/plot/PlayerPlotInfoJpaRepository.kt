package onl.tesseract.creative.repository.hibernate.plot

import onl.tesseract.creative.domain.plot.PlayerPlotInfo
import onl.tesseract.creative.repository.generic.plot.PlayerPlotInfoRepository
import onl.tesseract.creative.repository.hibernate.plot.entity.PlayerPlotInfoEntity
import onl.tesseract.creative.repository.hibernate.plot.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PlayerPlotInfoJpaRepository : JpaRepository<PlayerPlotInfoEntity, UUID>

@Component
class PlayerPlotInfoJpaRepositoryAdapter(private val repository: PlayerPlotInfoJpaRepository) :
        PlayerPlotInfoRepository {
    override fun getById(id: UUID): PlayerPlotInfo? {
        return repository.findByIdOrNull(id)
                ?.toDomain()
    }

    override fun save(entity: PlayerPlotInfo): PlayerPlotInfo {
        return repository.save(entity.toEntity())
                .toDomain()
    }
}