package io.github.nosequel.kitpvp.region.listener;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.region.Region;
import io.github.nosequel.kitpvp.region.RegionHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashSet;
import java.util.Set;

public class RegionMoveListener implements Listener {

    private final RegionHandler regionHandler;
    private final Set<Player> damageProtected = new HashSet<>();

    public RegionMoveListener(HandlerManager handlerManager) {
        this.regionHandler = handlerManager.find(RegionHandler.class);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.damageProtected.add(event.getPlayer());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        final Region regionTo = this.regionHandler.find(event.getTo());
        final Region regionFrom = this.regionHandler.find(event.getFrom());

        if (!regionFrom.equals(regionTo)) {
            if (regionFrom.isDamage() && !regionTo.isDamage()) {
                player.sendMessage(ChatColor.GRAY + "You are now protected");
                this.damageProtected.add(player);
                return;
            }

            if (this.damageProtected.contains(player) && regionTo.isDamage() && !regionFrom.isDamage()) {
                player.sendMessage(ChatColor.GRAY + "You are no longer protected.");
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();

            if (this.damageProtected.contains(player)) {
                if (this.regionHandler.find(player.getLocation()).isDamage()) {
                    if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                        this.damageProtected.remove(player);
                    }

                    event.setCancelled(true);
                }
            }
        }
    }
}