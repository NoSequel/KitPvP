package io.github.nosequel.kitpvp.level.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class LevelListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player killer = event.getEntity().getKiller();

        if(killer != null && killer.isOnline()) {

        }
    }

}
