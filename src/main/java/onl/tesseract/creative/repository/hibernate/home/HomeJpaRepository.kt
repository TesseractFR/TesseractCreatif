package onl.tesseract.creative.repository.hibernate.home

import onl.tesseract.creative.domain.home.Home
import onl.tesseract.creative.repository.generic.home.HomeRepository
import onl.tesseract.creative.repository.hibernate.home.entity.HomeEntity
import onl.tesseract.creative.repository.hibernate.home.entity.HomeEntityPK
import onl.tesseract.creative.repository.hibernate.home.entity.toEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HomeJpaRepository : JpaRepository<HomeEntity, HomeEntityPK> {
    fun findAllByHomePKUuid(uuid: UUID): List<HomeEntity>
}

@Component
class HomeJpaRepositoryAdapter(private val homeJpaRepository: HomeJpaRepository) : HomeRepository {
    override fun getById(id: Pair<UUID, String>): Home? {
        return homeJpaRepository.findByIdOrNull(HomeEntityPK(id.first, id.second))
                ?.toDomain()
    }

    override fun save(entity: Home): Home {
        return homeJpaRepository.save(entity.toEntity())
                .toDomain()
    }

    override fun delete(home: Home) {
        homeJpaRepository.delete(home.toEntity())
    }

    override fun getAll(uuid: UUID): Set<Home> {
        return homeJpaRepository.findAllByHomePKUuid(uuid)
                .stream()
                .map {
                    it.toDomain()
                }
                .toList()
                .toSet();
    }
}