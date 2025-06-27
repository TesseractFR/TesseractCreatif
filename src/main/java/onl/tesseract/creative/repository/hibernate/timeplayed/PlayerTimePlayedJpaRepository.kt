package onl.tesseract.creative.repository.hibernate.timeplayed

import jakarta.annotation.PreDestroy
import onl.tesseract.creative.domain.timeplayed.PlayerTimePlayedInfo
import onl.tesseract.creative.repository.generic.timeplayed.PlayerTimePlayedRepository
import onl.tesseract.creative.repository.hibernate.timeplayed.entity.PlayerTimePlayedInfoEntity
import onl.tesseract.creative.repository.hibernate.timeplayed.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Repository
interface PlayerTimePlayedJpaRepository : JpaRepository<PlayerTimePlayedInfoEntity, UUID>

@Component
open class PlayerTimePlayedJpaRepositoryAdapter(private val repository: PlayerTimePlayedJpaRepository) :
        PlayerTimePlayedRepository {

    private val inMemoryEntities = ConcurrentHashMap<UUID, PlayerTimePlayedInfo>()
    private val toPersist = ConcurrentHashMap.newKeySet<UUID>()


    override fun getById(id: UUID): PlayerTimePlayedInfo? {
        return inMemoryEntities[id] ?: repository.findByIdOrNull(id)
                ?.toDomain()
                ?.also {
                    inMemoryEntities[id] = it
                }
    }

    override fun save(entity: PlayerTimePlayedInfo): PlayerTimePlayedInfo {
        inMemoryEntities[entity.uuid] = entity
        toPersist.add(entity.uuid)
        return entity
    }

    // Méthode de flush à appeler toutes les minutes
    @Scheduled(fixedRate = 60_000)
    fun flushToDatabase() {
        val ids = toPersist.toList()
        toPersist.clear()

        val toFlush = ids.mapNotNull { inMemoryEntities[it] }
        if (toFlush.isNotEmpty()) {
            repository.saveAll(toFlush.map { it.toEntity() })
        }
    }

    @PreDestroy
    fun flushOnShutdown() {
        flushToDatabase()
    }
}
