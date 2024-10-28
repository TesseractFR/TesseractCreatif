package onl.tesseract;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.milkbowl.vault.permission.Permission;
import onl.tesseract.command.MenuCommand;
import onl.tesseract.event.InvisibleItemFrameHandler;
import onl.tesseract.event.IronDoorEventHandler;
import onl.tesseract.event.IronTrapDoorEventHandler;
import onl.tesseract.player.CreativePlayerContainer;
import onl.tesseract.tesseractlib.TesseractLib;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Creatif extends JavaPlugin implements Listener {
    @Getter
    private static Creatif instance;
    @Getter
    private Permission permissions = null;
    @Override
    public void onEnable() {
        Creatif.instance = this;
        TesseractLib.setPlayerContainer(new CreativePlayerContainer());
        // Plugin startup logic
        if (!setupPermissions())
        {
            System.err.println("Could not setup permissions");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Config config = Config.getInstance();
        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(this,this);
        this.getServer().getPluginManager().registerEvents(new InvisibleItemFrameHandler(), this);
        this.getServer().getPluginManager().registerEvents(new IronTrapDoorEventHandler(), this);
        this.getServer().getPluginManager().registerEvents(new IronDoorEventHandler(), this);
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("menu")).setExecutor(new MenuCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static CreativePlayerContainer getPlayerContainer()
    {
        return (CreativePlayerContainer) TesseractLib.getPlayerContainer();
    }
    @EventHandler()
    public void onJoin(PlayerJoinEvent event){
        CreativePlayer creativePlayer;
        if (!event.getPlayer().hasPlayedBefore() || !getPlayerContainer().exists(event.getPlayer().getUniqueId()))
        {
            creativePlayer = getPlayerContainer().newPlayer(event.getPlayer());
            event.getPlayer().teleport(onl.tesseract.tesseractlib.Config.getInstance().getFirstSpawnLocation());
            event.joinMessage(Component.text("Bienvenue ", NamedTextColor.GOLD)
                    .append(Component.text(event.getPlayer().getName(), NamedTextColor.GREEN))
                    .append(Component.text(" sur le Créatif !", NamedTextColor.GOLD)));

        }
        else{
            creativePlayer = getPlayerContainer().get(event.getPlayer());
            TextColor color = creativePlayer.getPlayerRank().getColor();
            creativePlayer.onJoin(event.getPlayer());
            event.joinMessage(Component.text("+ ",NamedTextColor.GREEN)
                    .append(Component.text(event.getPlayer().getName(),color))
                    .append(Component.text( " a rejoint le serveur.",NamedTextColor.GOLD)));
            creativePlayer.updatePermission();
            Bukkit.getServer().getPluginManager().registerEvents(creativePlayer, this);

        }
    }

    private boolean setupPermissions()
    {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp == null)
            return false;
        permissions = rsp.getProvider();
        return true;
    }
}
