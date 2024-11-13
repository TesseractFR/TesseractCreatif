package onl.tesseract.home.persistence

import onl.tesseract.HibernateUtil
import onl.tesseract.home.entity.Home
import onl.tesseract.home.entity.HomePK
import onl.tesseract.persistence.Repository
import java.util.*

/**
 * Abstract persistence layer to read and write home from a data store
 */
interface HomeRepository : Repository<Home, HomePK> {
    fun delete(entity: HomePK)
    fun getAll(uuid: UUID): List<Home>
}

/**
 * Persistence layer to read and write home from hibernate
 */
class HomeHibernateRepository : HomeRepository {
    override fun getById(id: HomePK): Home? {
        HibernateUtil.sessionFactory.openSession().use { session ->
            return session.find(
                Home::class.java, id
            )
        }
    }

    override fun save(entity: Home) {
        HibernateUtil.executeInsideTransaction { session ->
            session.merge(entity)
        }
    }

    override fun delete(entity: HomePK) {
        HibernateUtil.executeInsideTransaction { session ->
            val home = session.find(Home::class.java, entity)
            if (home != null) {
                session.remove(home)
            }
        }
    }

    override fun getAll(uuid: UUID): List<Home> {
        HibernateUtil.sessionFactory.openSession().use { session ->
            val query = session.createQuery("FROM Home h WHERE h.homePK.uuid = :uuid", Home::class.java)
            query.setParameter("uuid", uuid.toString())
            return query.resultList
        }
    }

}