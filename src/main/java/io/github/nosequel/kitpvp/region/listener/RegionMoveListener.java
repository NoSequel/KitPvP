package io.github.nosequel.kitpvp.region.listener;

import io.github.nosequel.kitpvp.KitPlugin;
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
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class RegionMoveListener implements Listener {

    private final RegionHandler regionHandler;
    private final KitPlugin plugin;

    public RegionMoveListener(KitPlugin plugin) {
        this.plugin = plugin;
        this.regionHandler = plugin.getHandler().find(RegionHandler.class);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        this.setProtected(event.getPlayer(), true);
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        this.setProtected(event.getPlayer(), !this.regionHandler.find(event.getPlayer().getLocation()).isDamage());
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        final Region regionTo = this.regionHandler.find(event.getTo());
        final Region regionFrom = this.regionHandler.find(event.getFrom());

        if (!regionFrom.equals(regionTo)) {
            if (regionFrom.isDamage() && !regionTo.isDamage()) {
                player.sendMessage(ChatColor.GRAY + "You are now protected");

                this.setProtected(player, true);
                return;
            }

            if (this.isProtected(player) && regionTo.isDamage() && !regionFrom.isDamage()) {
                player.sendMessage(ChatColor.GRAY + "You are no longer protected.");
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();

            if(this.isProtected(player)) {
                if (this.regionHandler.find(player.getLocation()).isDamage()) {
                    if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                        this.setProtected(player, false);
                    }
                }

                event.setCancelled(true);
            }
        }
    }

    /**
     * Check if a player has spawn protection
     *
     * @param player the player to check
     * @return whether the player has spawn protection or not
     */
    private boolean isProtected(Player player) {
        return player.getMetadata("protected").iterator().next().asBoolean();
    }

    /**
     * Set the state of a player's protection
     *
     * @param player     the player to update
     * @param protection the state of the protection
     */
    private void setProtected(Player player, boolean protection) {
        player.removeMetadata("protected", this.plugin);
        player.setMetadata("protected", new FixedMetadataValue(this.plugin, protection));
    }

}