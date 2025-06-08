package onl.tesseract.creative

import org.bukkit.Bukkit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.Properties
import javax.sql.DataSource

@SpringBootApplication(scanBasePackages = ["onl.tesseract.creative"])
@EnableJpaRepositories(
    "onl.tesseract.creative.repository.hibernate",
    entityManagerFactoryRef = "creativeEntityManagerFactory")
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
    @Autowired
    open fun creativeEntityManagerFactory(
        entityManagerFactoryBuilder: EntityManagerFactoryBuilder,
        @Qualifier("creativeDataSource") ds: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        val build = entityManagerFactoryBuilder
                .dataSource(ds)
                .packages("onl.tesseract.creative.repository.hibernate")
                .persistenceUnit("default")
                .build()
        build.entityManagerInterface = null
        build.jpaVendorAdapter = HibernateJpaVendorAdapter()
        val jpaProperties = Properties()
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update")
        jpaProperties.setProperty("spring.jpa.show-sql", "false")
        jpaProperties.setProperty("hibernate.show_sql", "true")
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

    @Bean(name = ["bukkitScheduler"])
    open fun bukkitScheduler(): TaskScheduler {
        return BukkitTaskScheduler(PLUGIN_INSTANCE, Bukkit.getScheduler())
    }

}