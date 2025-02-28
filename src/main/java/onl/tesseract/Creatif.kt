package onl.tesseract

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.milkbowl.vault.permission.Permission
import onl.tesseract.chat.ColoredChatListener
import onl.tesseract.command.*
import onl.tesseract.command.home.DelhomeCommand
import onl.tesseract.command.home.HomeCommand
import onl.tesseract.command.home.SetHomeCommand
import onl.tesseract.command.staff.StaffCommand
import onl.tesseract.core.Config
import onl.tesseract.core.command.BackCommand
import onl.tesseract.core.event.ColoredChat
import onl.tesseract.home.HomeService
import onl.tesseract.home.persistence.HomeHibernateRepository
import onl.tesseract.lib.chat.ChatEntryService
import onl.tesseract.lib.service.ServiceContainer
import onl.tesseract.lib.task.TaskScheduler
import onl.tesseract.lib.util.append
import onl.tesseract.nickname.NicknameListener
import onl.tesseract.nickname.NicknameService
import onl.tesseract.nickname.persistence.NicknameHibernateRepository
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
        serviceContainer.registerService(
            NicknameService::class.java,
            NicknameService(NicknameHibernateRepository()))

        val chatEntryService = ChatEntryService(TaskScheduler(this))
        serviceContainer.registerService(ChatEntryService::class.java, chatEntryService)
        server.pluginManager.registerEvents(chatEntryService, this)
    }

    private fun registerEvents() {
        val pluginManager = server.pluginManager
        pluginManager.registerEvents(this, this)
        pluginManager.registerEvents(NicknameListener(), this)
        pluginManager.registerEvents(BackCommand(), this)
        pluginManager.registerEvents(ColoredChatListener(), this)
    }

    private fun registerCommands() {
        this.getCommand("menu")?.setExecutor(MenuCommand())
        this.getCommand("mondes")?.setExecutor(WorldsMenuCommand())
        this.getCommand("grades")?.setExecutor(RankMenuCommand())
        this.getCommand("blocks")?.setExecutor(SpecialBlockMenuCommand())
        this.getCommand("speed")
                ?.setExecutor(SpeedCommand())
        this.getCommand("outils")?.setExecutor(PluginsToolsMenuCommand())
        this.getCommand("commandes")?.setExecutor(CommandsBookCommand())
        val staffCommand = StaffCommand()
        this.getCommand("staff")
                ?.setExecutor(staffCommand)
        this.getCommand("staff")
                ?.setExecutor(staffCommand)
        this.getCommand("boutique")?.setExecutor(BoutiqueCommand())
        this.getCommand("sethome")?.setExecutor(SetHomeCommand())
        val delhomeCommand  = DelhomeCommand()
        this.getCommand("delhome")?.setExecutor(delhomeCommand)
        this.getCommand("delhome")?.tabCompleter = delhomeCommand
        val homeCommand  = HomeCommand()
        this.getCommand("home")?.setExecutor(homeCommand)
        this.getCommand("home")?.tabCompleter = homeCommand
        this.getCommand("scoreboard")?.setExecutor(ScoreBoardCommands())
        this.getCommand("tpa")?.setExecutor(TPACommand())
        this.getCommand("tp")
                ?.setExecutor(TPCommand())
        this.getCommand("nick")?.setExecutor(NickCommand())
        this.getCommand("top")?.setExecutor(TopCommand())
        this.getCommand("back")?.setExecutor(BackCommand())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val nicknameService = ServiceContainer[NicknameService::class.java]
        val nickname = nicknameService.getNickname(event.player.uniqueId)
        val displayNameComponent = if (nickname != null) {
            ColoredChat.colorComponent(Component.text(nickname))
        } else {
            val rankService = ServiceContainer[PlayerRankService::class.java]
            val color = rankService.getStaffRank(event.player.uniqueId)?.color
                    ?: rankService.getPlayerRank(event.player.uniqueId).color
            Component.text(event.player.name, color)
        }

        if (!event.player.hasPlayedBefore()) {
            event.player.teleport(Config.invoke().firstSpawnLocation)
            event.joinMessage(
                Component.text("Bienvenue ", NamedTextColor.GOLD)
                    .append(event.player.name, NamedTextColor.GREEN)
                    .append(" sur le Créatif !", NamedTextColor.GOLD)
            )
        } else {
            event.joinMessage(
                Component.text("+ ", NamedTextColor.GREEN)
                    .append(displayNameComponent)
                    .append(" a rejoint le serveur.", NamedTextColor.GOLD)
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
