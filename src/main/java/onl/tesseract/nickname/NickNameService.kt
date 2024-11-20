package onl.tesseract.nickname

import onl.tesseract.tesseractlib.event.ColoredChat
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException
import java.util.UUID

class NicknameManager() {
    private val FOLDER_PATH = "plugins/Tesseract/nicknames/"
    private val nicknameFile: File = File(FOLDER_PATH, "nicknames.yml")
    private val nicknameConfig: FileConfiguration

    init {
        val folder = File(FOLDER_PATH)
        if (!folder.exists()) {
            try {
                folder.mkdirs()
            } catch (e: IOException) {
                e.printStackTrace()
                throw RuntimeException("Impossible de créer le répertoire : $FOLDER_PATH")
            }
        }

        if (!nicknameFile.exists()) {
            try {
                nicknameFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                throw RuntimeException("Impossible de créer nicknames.yml")
            }
        }
        nicknameConfig = YamlConfiguration.loadConfiguration(nicknameFile)
    }

    fun saveNickname(playerUUID: UUID, nickname: String?) {
        nicknameConfig.set("nicknames.$playerUUID", nickname)
        saveConfig()
    }

    fun loadNickname(playerUUID: UUID, player: Player): String {
        val nickname = nicknameConfig.getString("nicknames.$playerUUID") ?: player.name
        player.setDisplayName(ColoredChat.colorMessage(nickname))
        player.setPlayerListName(ColoredChat.colorMessage(nickname))
        return nickname
    }

    private fun saveConfig() {
        try {
            nicknameConfig.save(nicknameFile)
        } catch (e: IOException) {
            e.printStackTrace()
            throw RuntimeException("Impossible de sauvegarder nicknames.yml")
        }
    }
}
