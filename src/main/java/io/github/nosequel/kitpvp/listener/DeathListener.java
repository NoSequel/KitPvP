package io.github.nosequel.kitpvp.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        final Player killer = player.getKiller();

        event.setDeathMessage(null);

        if (killer != null) {
            player.sendMessage(ChatColor.DARK_PURPLE + "You have been killed by " + ChatColor.LIGHT_PURPLE + killer.getName() + ChatColor.DARK_PURPLE + ".");
            killer.sendMessage(ChatColor.DARK_PURPLE + "You have killed " + ChatColor.LIGHT_PURPLE + killer.getName() + ChatColor.DARK_PURPLE + " and have gained 5 points.");
        } else {
            player.sendMessage(ChatColor.DARK_PURPLE + "You have died due to natural causes.");
        }
    }
}