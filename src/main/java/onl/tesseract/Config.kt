package onl.tesseract

import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import javax.naming.ConfigurationException

/**
 * Classe permettant de charger la config du plugin Créatif
 */
data class Config(
    val creativeDbHost: String,
    val creativeDbDatabase: String,
    val creativeDbUsername: String,
    val creativeDbPassword: String,
    val creativeDbPort: Int
) {

    companion object {

        private fun load(): Config {
            val file = File("plugins/Tesseract/config.yml")
            val yaml = YamlConfiguration.loadConfiguration(file)
            return Config(
                yaml.getString("creative_db_host")
                    ?: throw ConfigurationException("Missing configuration creative_db_host"),
                yaml.getString("creative_db_database")
                    ?: throw ConfigurationException("Missing configuration creative_db_database"),
                yaml.getString("creative_db_username")
                    ?: throw ConfigurationException("Missing configuration creative_db_username"),
                yaml.getString("creative_db_password")
                    ?: throw ConfigurationException("Missing configuration creative_db_password"),
                yaml.getInt("creative_db_port")
            )
        }

        private var INSTANCE: Config? = null

        @JvmStatic
        fun getInstance(): Config {
            if (INSTANCE == null) {
                INSTANCE = load()
            }
            return INSTANCE!!
        }


    }

}

