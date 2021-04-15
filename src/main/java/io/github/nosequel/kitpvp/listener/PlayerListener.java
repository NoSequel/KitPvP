package io.github.nosequel.kitpvp.listener;

import io.github.nosequel.kitpvp.KitPlugin;
import io.github.nosequel.kitpvp.loadout.Loadout;
import io.github.nosequel.kitpvp.loadout.LoadoutHandler;
import io.github.nosequel.kitpvp.loadout.LoadoutType;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.logging.Level;

public class PlayerListener implements Listener {

    private final LoadoutHandler loadoutHandler;

    /**
     * Constructor to make a new player listener instance
     *
     * @param plugin the manager to get the handlers from
     */
    public PlayerListener(KitPlugin plugin) {
        this.loadoutHandler = plugin.getHandler().find(LoadoutHandler.class);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Loadout loadout = this.loadoutHandler.find(LoadoutType.SPAWN);

        if (loadout.getLoadoutType().equals(LoadoutType.UNIDENTIFIED)) {
            Bukkit.getLogger().log(Level.WARNING, "Unable to find loadout with type SPAWN");
        }

        loadout.equipLoadout(player);
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        final Player player = event.getPlayer();
        final Loadout loadout = this.loadoutHandler.find(LoadoutType.SPAWN);

        if (loadout.getLoadoutType().equals(LoadoutType.UNIDENTIFIED)) {
            Bukkit.getLogger().log(Level.WARNING, "Unable to find loadout with type SPAWN");
        }

        loadout.equipLoadout(player);
        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        final Player player = event.getPlayer();

        if(!player.hasPermission("kitpvp.build") || player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        final Player player = event.getPlayer();

        if(!player.hasPermission("kitpvp.build") || player.getGameMode().equals(GameMode.CREATIVE)) {
            event.setCancelled(true);
        }
    }

}