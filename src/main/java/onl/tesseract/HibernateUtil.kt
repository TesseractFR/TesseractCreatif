package onl.tesseract;

import lombok.extern.slf4j.Slf4j;
import onl.tesseract.entity.player.CreativePlayerInfo;
import onl.tesseract.plot.entity.PlayerPlotInfo;
import onl.tesseract.rank.entity.PlayerRankInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.tool.schema.Action;

import java.util.function.Consumer;

@Slf4j
public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Config config = Config.getInstance();
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver");
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://"+config.getCreativeDbHost()+":"+config.getCreativeDbPort()+"/"+config.getCreativeDbDatabase());
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_USER,  config.getCreativeDbUsername());
            configuration.setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, config.getCreativeDbPassword());

            configuration.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
            //configuration.setProperty(AvailableSettings.CACHE_REGION_FACTORY,"org.hibernate.cache.ehcache.EhCacheRegionFactory");
            // Enable Hibernate's automatic session context management
            configuration.setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            // Echo all executed SQL to stdout
            configuration.setProperty(AvailableSettings.SHOW_SQL, "false");
            configuration.setProperty(AvailableSettings.FORMAT_SQL, "true");

            // Drop and re-create the database schema on startup
            configuration.setProperty(AvailableSettings.HBM2DDL_AUTO, Action.ACTION_UPDATE);


            configuration.addAnnotatedClass(CreativePlayerInfo.class);
            configuration.addAnnotatedClass(PlayerPlotInfo.class);
            configuration.addAnnotatedClass(PlayerRankInfo.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    public static void executeInsideTransaction(Consumer<Session> action) {

        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            action.accept(session);
            transaction.commit();
        } catch (Exception e) {
            log.error("Error executing inside transaction", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

