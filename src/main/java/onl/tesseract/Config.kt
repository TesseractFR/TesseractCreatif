package onl.tesseract;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Classe permettant de charger la config du plugin Créatif
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Config {
    @Getter
    static private final Config instance = new Config();

    String creativeDbHost;

    String creativeDbDatabase;

    String creativeDbUsername;

    String creativeDbPassword;

    int creativeDbPort;

    private Config() {
        File file = new File(onl.tesseract.tesseractlib.Config.getConfigFilepath());
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        this.creativeDbHost = yaml.getString("creative_db_host");
        this.creativeDbDatabase = yaml.getString("creative_db_database");
        this.creativeDbUsername = yaml.getString("creative_db_username");
        this.creativeDbPassword = yaml.getString("creative_db_password");
        this.creativeDbPort = yaml.getInt("creative_db_port");
    }


}
