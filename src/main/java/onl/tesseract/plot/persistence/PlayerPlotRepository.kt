package onl.tesseract.plot.persistence

import onl.tesseract.HibernateUtil
import onl.tesseract.plot.entity.PlayerPlotInfo
import onl.tesseract.persistence.Repository
import java.util.*


/**
 * Abstract persistence layer to read and write playerPlot from a data store
 */
interface PlayerPlotInfoRepository : Repository<PlayerPlotInfo, UUID> {
}

/**
 * Persistence layer to read and write playerPlot from hibernate
 */
class PlayerPlotInfoHibernateRepository : PlayerPlotInfoRepository {
    override fun getById(id: UUID): PlayerPlotInfo? {
        HibernateUtil.getSessionFactory().openSession().use { session ->
            return session.find(
                PlayerPlotInfo::class.java, id
            )
        }
    }

    override fun save(entity: PlayerPlotInfo) {
        HibernateUtil.getSessionFactory().openSession().use { session ->
            session.persist(entity)
        }
    }

}