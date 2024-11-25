package onl.tesseract

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.milkbowl.vault.permission.Permission
import onl.tesseract.command.*
import onl.tesseract.command.staff.Staff
import onl.tesseract.player.CreativePlayer
import onl.tesseract.player.CreativePlayerContainer
import onl.tesseract.command.home.DelhomeCommand
import onl.tesseract.command.home.HomeCommand
import onl.tesseract.command.home.SetHomeCommand
import onl.tesseract.core.Config
import onl.tesseract.home.HomeService
import onl.tesseract.home.persistence.HomeHibernateRepository
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.permpack.PlayerPermPackService
import onl.tesseract.permpack.persistence.PlayerPermPackInfoHibernateRepository
import onl.tesseract.player.PermissionService
import onl.tesseract.plot.PlayerPlotService
import onl.tesseract.plot.persistence.PlayerPlotInfoHibernateRepository
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.rank.persistence.PlayerRankInfoHibernateRepository
import onl.tesseract.scoreBoard.ScoreBoardCore
import onl.tesseract.timeplayed.PlayerTimePlayedService
import onl.tesseract.timeplayed.PlayerTimePlayedTask
import onl.tesseract.timeplayed.persistence.PlayerTimePlayedHibernateRepository
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

class Creatif : JavaPlugin(), Listener {
    var permissions: Permission? = null
        private set
    override fun onEnable() {
        instance = this
        registerDefaultServices()
        // Plugin startup logic
        if (!setupPermissions()) {
            System.err.println("Could not setup permissions")
            server.pluginManager.disablePlugin(this)
            return
        }
        PlayerTimePlayedTask.start(this,1);
        registerEvents()
        registerCommands()
        ScoreBoardCore.startScoreboard(this)
    }

    private fun registerDefaultServices() {
        val serviceContainer = ServiceContainer.getInstance();
        serviceContainer.registerService(
            PlayerRankService::class.java,
            PlayerRankService(PlayerRankInfoHibernateRepository()))
        serviceContainer.registerService(
            PlayerPlotService::class.java,
            PlayerPlotService(PlayerPlotInfoHibernateRepository()))
        serviceContainer.registerService(
            PlayerTimePlayedService::class.java,
            PlayerTimePlayedService(PlayerTimePlayedHibernateRepository()))
        serviceContainer.registerService(
            PlayerPermPackService::class.java,
            PlayerPermPackService(PlayerPermPackInfoHibernateRepository()))
        serviceContainer.registerService(HomeService::class.java, HomeService(HomeHibernateRepository()))
        serviceContainer.registerService(PermissionService::class.java, PermissionService())
    }

    private fun registerEvents() {
        val pluginManager = server.pluginManager
        pluginManager.registerEvents(this, this)
    }

    private fun registerCommands() {
        this.getCommand("menu")?.setExecutor(MenuCommand())
        this.getCommand("mondes")?.setExecutor(WorldsCommand())
        this.getCommand("grades")?.setExecutor(RankCommand())
        this.getCommand("blocks")?.setExecutor(SpecialBlockCommand())
        this.getCommand("outils")?.setExecutor(PluginsToolsCommand())
        this.getCommand("commandes")?.setExecutor(CommandsBookCommand())
        this.getCommand("staff")?.setExecutor(Staff())
        this.getCommand("boutique")?.setExecutor(BoutiqueCommand())
        this.getCommand("sethome")?.setExecutor(SetHomeCommand())
        val delhomeCommand  = DelhomeCommand()
        this.getCommand("delhome")?.setExecutor(delhomeCommand)
        this.getCommand("delhome")?.tabCompleter = delhomeCommand
        val homeCommand  = HomeCommand()
        this.getCommand("home")?.setExecutor(homeCommand)
        this.getCommand("home")?.tabCompleter = homeCommand
        this.getCommand("scoreboard")?.setExecutor(ScoreBoardCommands())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        if (!event.player.hasPlayedBefore()) {
            event.player.teleport(Config.invoke().firstSpawnLocation)
            event.joinMessage(
                Component.text("Bienvenue ", NamedTextColor.GOLD)
                    .append(Component.text(event.player.name, NamedTextColor.GREEN))
                    .append(Component.text(" sur le Créatif !", NamedTextColor.GOLD))
            )
        } else {
            val color = ServiceContainer[PlayerRankService::class.java].getPlayerRank(event.player.uniqueId).color
            event.joinMessage(
                Component.text("+ ", NamedTextColor.GREEN)
                    .append(Component.text(event.player.name, color))
                    .append(Component.text(" a rejoint le serveur.", NamedTextColor.GOLD))
            )
            ServiceContainer[PermissionService::class.java].updatePermission(event.player.uniqueId)
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
    }
}
