package onl.tesseract.creative

import onl.tesseract.lib.exception.ConfigurationException
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

/**
 * Classe permettant de charger la config du plugin Créatif
 */
data class Config(
    val creativeDbHost: String,
    val creativeDbDatabase: String,
    val creativeDbUsername: String,
    val creativeDbPassword: String,
    val creativeDbPort: Int,
) {
    companion object {
        private lateinit var instance: Config

        operator fun invoke(): Config {
            if (this::instance.isInitialized)
                return instance
            instance = load()
            return instance
        }

        fun load(): Config {
            return load(YamlConfiguration.loadConfiguration(File("plugins/Tesseract/config.yml")))
        }

        fun load(yaml: ConfigurationSection): Config {
            return Config(
                creativeDbHost = yaml.getString("creative_db_host")
                        ?: throw ConfigurationException("Missing configuration creative_db_host"),
                creativeDbDatabase = yaml.getString("creative_db_database")
                        ?: throw ConfigurationException("Missing configuration creative_db_database"),
                creativeDbUsername = yaml.getString("creative_db_username")
                        ?: throw ConfigurationException("Missing configuration creative_db_username"),
                creativeDbPassword = yaml.getString("creative_db_password")
                        ?: throw ConfigurationException("Missing configuration creative_db_password"),
                creativeDbPort = yaml.getInt("creative_db_port")
            )
        }
    }
}