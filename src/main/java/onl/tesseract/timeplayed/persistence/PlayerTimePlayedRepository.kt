package onl.tesseract.timeplayed.persistence

import onl.tesseract.HibernateUtil
import onl.tesseract.persistence.Repository
import onl.tesseract.timeplayed.entity.PlayerTimePlayedInfo
import java.util.UUID
/**
 * Abstract persistence layer to read and write player's TimePlayed from a data store
 */
interface PlayerTimePlayedRepository : Repository<PlayerTimePlayedInfo, UUID> {
}

/**
 * Persistence layer to read and write  player's TimePlayed from hibernate
 */
class PlayerTimePlayedHibernateRepository : PlayerTimePlayedRepository {
    override fun getById(id: UUID): PlayerTimePlayedInfo? {
        HibernateUtil.sessionFactory.openSession().use { session ->
            return session.find(
                PlayerTimePlayedInfo::class.java, id
            )
        }
    }

    override fun save(entity: PlayerTimePlayedInfo) {
        HibernateUtil.executeInsideTransaction { session ->
            session.merge(entity)
        }
    }

}