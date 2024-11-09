package onl.tesseract

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.milkbowl.vault.permission.Permission
import onl.tesseract.command.MenuCommand
import onl.tesseract.event.InvisibleItemFrameHandler
import onl.tesseract.event.IronDoorEventHandler
import onl.tesseract.event.IronTrapDoorEventHandler
import onl.tesseract.player.CreativePlayer
import onl.tesseract.player.CreativePlayerContainer
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.service.CreativeServices.Companion.get
import onl.tesseract.service.CreativeServices.Companion.getInstance
import onl.tesseract.tesseractlib.Config
import onl.tesseract.tesseractlib.TesseractLib
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class Creatif : JavaPlugin(), Listener {
    var permissions: Permission? = null
        private set
    override fun onEnable() {
        instance = this
        getInstance().registerDefaultServices()
        TesseractLib.setPlayerContainer(CreativePlayerContainer())
        // Plugin startup logic
        if (!setupPermissions()) {
            System.err.println("Could not setup permissions")
            server.pluginManager.disablePlugin(this)
            return
        }
        registerEvents()
        registerCommands()
    }

    private fun registerEvents() {
        val pluginManager = server.pluginManager
        pluginManager.registerEvents(this, this)
        pluginManager.registerEvents(InvisibleItemFrameHandler(), this)
        pluginManager.registerEvents(IronTrapDoorEventHandler(), this)
        pluginManager.registerEvents(IronDoorEventHandler(), this)
    }

    private fun registerCommands() {
        this.getCommand("menu")?.setExecutor(MenuCommand())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val creativePlayer: CreativePlayer
        if (!event.player.hasPlayedBefore() || !playerContainer.exists(event.player.uniqueId)) {
            event.player.teleport(Config.getInstance().firstSpawnLocation)
            event.joinMessage(
                Component.text("Bienvenue ", NamedTextColor.GOLD)
                    .append(Component.text(event.player.name, NamedTextColor.GREEN))
                    .append(Component.text(" sur le Créatif !", NamedTextColor.GOLD))
            )
        } else {
            creativePlayer = playerContainer[event.player]
            val color = get(
                PlayerRankService::class.java
            ).getPlayerRank(event.player.uniqueId).color
            creativePlayer.onJoin(event.player)
            event.joinMessage(
                Component.text("+ ", NamedTextColor.GREEN)
                    .append(Component.text(event.player.name, color))
                    .append(Component.text(" a rejoint le serveur.", NamedTextColor.GOLD))
            )
            creativePlayer.updatePermission()
            Bukkit.getServer().pluginManager.registerEvents(creativePlayer, this)
        }
    }

    private fun setupPermissions(): Boolean {
        val rsp = server.servicesManager.getRegistration(
            Permission::class.java
        )
        if (rsp == null) return false
        permissions = rsp.provider
        return true
    }



    companion object {
        @JvmStatic
        var instance: Creatif? = null
            private set
        val playerContainer: CreativePlayerContainer
            get() = TesseractLib.getPlayerContainer() as CreativePlayerContainer
    }
}
