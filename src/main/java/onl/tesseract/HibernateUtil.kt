package onl.tesseract

import lombok.extern.slf4j.Slf4j
import onl.tesseract.timeplayed.entity.PlayerTimePlayedInfo
import onl.tesseract.plot.entity.PlayerPlotInfo
import onl.tesseract.rank.entity.PlayerRankInfo
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.Transaction
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.AvailableSettings
import org.hibernate.cfg.Configuration
import org.hibernate.service.ServiceRegistry
import org.hibernate.tool.schema.Action
import java.util.function.Consumer

@Slf4j
object HibernateUtil {
    @JvmField
    val sessionFactory: SessionFactory = buildSessionFactory()

    private fun buildSessionFactory(): SessionFactory {
        try {
            val configuration = Configuration()
            val config = Config.getInstance()
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver")
            configuration.setProperty(
                AvailableSettings.JAKARTA_JDBC_URL,
                "jdbc:mysql://" + config.creativeDbHost + ":" + config.creativeDbPort + "/" + config.creativeDbDatabase
            )
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_USER, config.creativeDbUsername)
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, config.creativeDbPassword)

            configuration.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
            //configuration.setProperty(AvailableSettings.CACHE_REGION_FACTORY,"org.hibernate.cache.ehcache.EhCacheRegionFactory");
            // Enable Hibernate's automatic session context management
            configuration.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread")

            // Echo all executed SQL to stdout
            configuration.setProperty(AvailableSettings.SHOW_SQL, "false")
            configuration.setProperty(AvailableSettings.FORMAT_SQL, "true")

            // Drop and re-create the database schema on startup
            configuration.setProperty(AvailableSettings.HBM2DDL_AUTO, Action.ACTION_UPDATE)


            configuration.addAnnotatedClass(PlayerTimePlayedInfo::class.java)
            configuration.addAnnotatedClass(PlayerPlotInfo::class.java)
            configuration.addAnnotatedClass(PlayerRankInfo::class.java)

            val serviceRegistry: ServiceRegistry =
                StandardServiceRegistryBuilder().applySettings(configuration.properties).build()
            return configuration.buildSessionFactory(serviceRegistry)
        } catch (ex: Exception) {
            System.err.println("Initial SessionFactory creation failed.$ex")
            throw ExceptionInInitializerError(ex)
        }
    }


    fun executeInsideTransaction(action: Consumer<Session>) {
        var transaction: Transaction? = null
        try {
            sessionFactory.openSession().use { session ->
                transaction = session.beginTransaction()
                action.accept(session)
                transaction!!.commit()
            }
        } catch (e: Exception) {
            System.err.println("Error executing inside transaction.$e")
            transaction!!.rollback()

        }
    }
}

