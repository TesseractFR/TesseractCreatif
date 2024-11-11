package onl.tesseract.permpack.persistence

import onl.tesseract.HibernateUtil
import onl.tesseract.permpack.entity.PlayerPermPackInfo
import onl.tesseract.persistence.Repository
import java.util.*


/**
 * Abstract persistence layer to read and write player Perm pack from a data store
 */
interface PlayerPermPackInfoRepository : Repository<PlayerPermPackInfo, UUID>

/**
 * Persistence layer to read and write player Perm pack from hibernate
 */
class PlayerPermPackInfoHibernateRepository : PlayerPermPackInfoRepository {
    override fun getById(id: UUID): PlayerPermPackInfo? {
        HibernateUtil.sessionFactory.openSession().use { session ->
            return session.find(
                PlayerPermPackInfo::class.java, id
            )
        }
    }

    override fun save(entity: PlayerPermPackInfo) {
        HibernateUtil.executeInsideTransaction { session ->
            session.merge(entity)
        }
    }

}