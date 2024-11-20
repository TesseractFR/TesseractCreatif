package onl.tesseract.nickname.persistence

import onl.tesseract.HibernateUtil
import onl.tesseract.nickname.entity.Nickname
import onl.tesseract.persistence.Repository
import java.util.UUID

interface NicknameRepository : Repository<Nickname, UUID>

class NicknameHibernateRepository : NicknameRepository {
    override fun getById(id: UUID): Nickname? {
        HibernateUtil.sessionFactory.openSession().use { session ->
            return session.find(Nickname::class.java, id)
        }
    }

    override fun save(entity: Nickname) {
        HibernateUtil.executeInsideTransaction { session ->
            session.merge(entity)
        }
    }
}
