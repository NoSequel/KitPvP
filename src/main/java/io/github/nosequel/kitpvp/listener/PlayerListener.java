package io.github.nosequel.kitpvp.listener;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class PlayerListener implements Listener {

    private final KitHandler kitHandler;

    /**
     * Constructor to make a new player listener instance
     *
     * @param handlerManager the manager to get the handlers from
     */
    public PlayerListener(HandlerManager handlerManager) {
        this.kitHandler = handlerManager.find(KitHandler.class);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Optional<Kit> kit = this.kitHandler.getKits().stream().findFirst();

        kit.ifPresent(value -> value.equip(player));
    }
}