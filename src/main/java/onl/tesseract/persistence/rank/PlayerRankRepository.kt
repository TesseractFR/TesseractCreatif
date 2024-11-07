package onl.tesseract.persistence.rank

import onl.tesseract.HibernateUtil
import onl.tesseract.rank.entity.PlayerRankInfo
import onl.tesseract.persistence.Repository
import java.util.*

/**
 * Abstract persistence layer to read and write playerRank from a data store
 */
interface PlayerRankInfoRepository : Repository<PlayerRankInfo, UUID> {
}

/**
 * Persistence layer to read and write playerRank from hibernate
 */
class PlayerRankInfoHibernateRepository : PlayerRankInfoRepository {
    override fun getById(id: UUID): PlayerRankInfo? {
        HibernateUtil.getSessionFactory().openSession().use { session ->
            return session.find(
                PlayerRankInfo::class.java, id
            )
        }
    }

    override fun save(entity: PlayerRankInfo) {
        HibernateUtil.getSessionFactory().openSession().use { session ->
            session.persist(entity)
        }
    }

}