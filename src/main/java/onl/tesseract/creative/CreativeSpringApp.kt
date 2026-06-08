package onl.tesseract.creative

import jakarta.persistence.EntityManagerFactory
import org.bukkit.Bukkit
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.PlatformTransactionManager
import java.util.*
import javax.sql.DataSource

@SpringBootApplication(scanBasePackages = ["onl.tesseract.creative"])
@EnableJpaRepositories(
    "onl.tesseract.creative.repository.hibernate",
    entityManagerFactoryRef = "creativeEntityManagerFactory",
    transactionManagerRef = "creativeTransactionManager")
@EnableScheduling
open class CreativeSpringApp {

    @Bean
    open fun creativeConfig(): Config = Config()

    @Bean(name = ["creativeDataSource"])
    @Primary
    open fun creativeDataSource(config: Config): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver")
        dataSource.url = "jdbc:mysql://${config.creativeDbHost}:${config.creativeDbPort}/${config.creativeDbDatabase}"
        dataSource.username = config.creativeDbUsername
        dataSource.password = config.creativeDbPassword
        return dataSource
    }

    @Bean(name = ["creativeEntityManagerFactory"])
    @Primary
    open fun creativeEntityManagerFactory(
        @Qualifier("creativeDataSource") ds: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        val build = LocalContainerEntityManagerFactoryBean()
        build.setDataSource(ds)
        build.setPackagesToScan("onl.tesseract.creative.repository.hibernate")
        build.setPersistenceUnitName("default")
        build.entityManagerInterface = null
        build.setEntityManagerFactoryInterface(EntityManagerFactory::class.java)
        build.jpaVendorAdapter = HibernateJpaVendorAdapter()
        val jpaProperties = Properties()
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update")
        jpaProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
        jpaProperties.setProperty("spring.jpa.show-sql", "false")
        jpaProperties.setProperty("hibernate.show_sql", "false")
        jpaProperties.setProperty("logging.level.org.hibernate.SQL", "DEBUG")
        jpaProperties.setProperty("logging.level.org.hibernate.type.descriptor.sql.BasicBinder", "TRACE")
        jpaProperties.setProperty(
            "hibernate.cache.region.factory_class",
            "org.hibernate.cache.jcache.JCacheRegionFactory")
        jpaProperties.setProperty("hibernate.generate_statistics", "false")
        jpaProperties.setProperty("hibernate.cache.use_second_level_cache", "true")
        jpaProperties.setProperty("hibernate.cache.use_query_cache", "true")
        build.setJpaProperties(jpaProperties)
        return build
    }

    @Bean(name = ["creativeTransactionManager"])
    @Primary
    open fun creativeTransactionManager(
        @Qualifier("creativeEntityManagerFactory") entityManagerFactory: EntityManagerFactory,
    ): PlatformTransactionManager {
        return JpaTransactionManager(entityManagerFactory)
    }

    @Bean(name = ["bukkitScheduler"])
    open fun bukkitScheduler(): TaskScheduler {
        return BukkitTaskScheduler(PLUGIN_INSTANCE, Bukkit.getScheduler())
    }

}
