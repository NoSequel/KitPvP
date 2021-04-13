package io.github.nosequel.kitpvp.listener;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.KitHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class KitListener implements Listener {

    private final KitHandler kitHandler;

    public KitListener(HandlerManager handlerManager) {
        this.kitHandler = handlerManager.find(KitHandler.class);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        this.kitHandler.getEquipped().remove(player.getUniqueId());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();

        this.kitHandler.getEquipped().remove(player.getUniqueId());
    }
}