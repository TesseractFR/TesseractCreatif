package onl.tesseract

import lombok.AccessLevel
import lombok.Getter
import lombok.experimental.FieldDefaults
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

/**
 * Classe permettant de charger la config du plugin Créatif
 */
class Config private constructor() {
    val creativeDbHost: String?

    val creativeDbDatabase: String?

    val creativeDbUsername: String?

    val creativeDbPassword: String?

    val creativeDbPort: Int

    init {
        val file = File(onl.tesseract.tesseractlib.Config.getConfigFilepath())
        val yaml = YamlConfiguration.loadConfiguration(file)
        this.creativeDbHost = yaml.getString("creative_db_host")
        this.creativeDbDatabase = yaml.getString("creative_db_database")
        this.creativeDbUsername = yaml.getString("creative_db_username")
        this.creativeDbPassword = yaml.getString("creative_db_password")
        this.creativeDbPort = yaml.getInt("creative_db_port")
    }


    companion object {
        val instance = Config()
    }
}
