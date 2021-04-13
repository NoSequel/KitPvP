package io.github.nosequel.kitpvp.profile.listener;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.profile.Profile;
import io.github.nosequel.kitpvp.profile.ProfileHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class ProfileListener implements Listener {

    private final ProfileHandler profileHandler;

    /**
     * Constructor to make a new profile listener object
     *
     * @param handlerManager the manager to find the handlers from
     */
    public ProfileListener(HandlerManager handlerManager) {
        this.profileHandler = handlerManager.find(ProfileHandler.class);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final Profile profile = this.profileHandler.findOrMake(player.getUniqueId(), player.getName());

        Bukkit.getLogger().log(Level.INFO, "Profile with UUID" + profile.getUuid() + " has been loaded.");
    }
}