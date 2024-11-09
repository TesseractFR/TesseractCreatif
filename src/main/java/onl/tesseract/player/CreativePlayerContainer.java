package onl.tesseract.player;

import onl.tesseract.Creatif;
import onl.tesseract.tesseractlib.equipment.Equipment;
import onl.tesseract.tesseractlib.player.PlayerContainer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreativePlayerContainer implements PlayerContainer<CreativePlayer, Equipment> {
    private final Map<UUID, CreativePlayer> playerMap = new HashMap<>();

    @Override
    public CreativePlayer get(OfflinePlayer player) {
        return get(player.getUniqueId());
    }

    @Override
    public CreativePlayer get(UUID player) {
        if (playerMap.containsKey(player)){
            return playerMap.get(player);
        }
        OfflinePlayer player1 = Bukkit.getOfflinePlayer(player);
        CreativePlayer tPlayer = new CreativePlayer(player1);
        playerMap.put(player, tPlayer);
        Creatif.getInstance().getLogger().info("Player " + player1.getName() + " (" + player + ") loaded.");
        return tPlayer;
    }

    @Override
    public CreativePlayer newPlayer(OfflinePlayer player) {
        CreativePlayer CreativePlayer = new CreativePlayer(player);
        CreativePlayer.save();
        return CreativePlayer;
    }

    @Override
    public boolean exists(UUID player) {
        return playerMap.containsKey(player) || get(player) != null;
    }

    @Override
    public Collection<CreativePlayer> getPlayers() {
        return playerMap.values();
    }

    @Override
    public boolean register(CreativePlayer player) {
        return false;
    }

    @Override
    public Equipment loadEquipment(UUID uuid) {
        return null;
    }
}