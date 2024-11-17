package onl.tesseract

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.milkbowl.vault.permission.Permission
import onl.tesseract.command.*
import onl.tesseract.command.home.DelhomeCommand
import onl.tesseract.command.home.HomeCommand
import onl.tesseract.command.home.SetHomeCommand
import onl.tesseract.command.scoreboard.ScoreBoard
import onl.tesseract.player.CreativePlayer
import onl.tesseract.player.CreativePlayerContainer
import onl.tesseract.rank.PlayerRankService
import onl.tesseract.scoreBoard.PlayerBoard
import onl.tesseract.scoreBoard.ScoreBoardCore
import onl.tesseract.service.CreativeServices.Companion.get
import onl.tesseract.service.CreativeServices.Companion.getInstance
import onl.tesseract.tesseractlib.Config
import onl.tesseract.tesseractlib.TesseractLib
import onl.tesseract.timeplayed.PlayerTimePlayedTask
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin

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
        PlayerTimePlayedTask.start(this,1);
        registerEvents()
        registerCommands()
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
        this.getCommand("boutique")?.setExecutor(BoutiqueCommand())
        this.getCommand("sethome")?.setExecutor(SetHomeCommand())
        val delhomeCommand  = DelhomeCommand()
        this.getCommand("delhome")?.setExecutor(delhomeCommand)
        this.getCommand("delhome")?.tabCompleter = delhomeCommand
        val homeCommand  = HomeCommand()
        this.getCommand("home")?.setExecutor(homeCommand)
        this.getCommand("home")?.tabCompleter = homeCommand
        this.getCommand("scoreboard")?.setExecutor(ScoreBoard())
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val creativePlayer: CreativePlayer = playerContainer[event.player] ?: CreativePlayer(event.player)
        if (!event.player.hasPlayedBefore() || !playerContainer.exists(event.player.uniqueId)) {
            event.player.teleport(Config.getInstance().firstSpawnLocation)
            event.joinMessage(
                Component.text("Bienvenue ", NamedTextColor.GOLD)
                    .append(Component.text(event.player.name, NamedTextColor.GREEN))
                    .append(Component.text(" sur le Créatif !", NamedTextColor.GOLD))
            )
        } else {
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
        if(ScoreBoardCore.playerScoreboardStatus[event.player.uniqueId] == true) {
            ScoreBoardCore.showScoreboard(event.player)
        } else {
            ScoreBoardCore.hideScoreboard(event.player)
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
