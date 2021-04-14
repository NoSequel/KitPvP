package io.github.nosequel.kitpvp.killstreak.listener;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.killstreak.KillstreakHandler;
import io.github.nosequel.kitpvp.profile.Profile;
import io.github.nosequel.kitpvp.profile.ProfileHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class KillstreakListener implements Listener {

    private final ProfileHandler profileHandler;
    private final KillstreakHandler killstreakHandler;

    public KillstreakListener(HandlerManager handlerManager) {
        this.profileHandler = handlerManager.find(ProfileHandler.class);
        this.killstreakHandler = handlerManager.find(KillstreakHandler.class);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        final Player killer = player.getKiller();

        final Profile playerProfile = this.profileHandler.findOrMake(player.getUniqueId(), player.getName());

        if (killer != null) {
            this.killstreakHandler.handleKill(this.profileHandler.findOrMake(killer.getUniqueId(), killer.getName()));
        }

        playerProfile.setKillstreak(0);
    }
}