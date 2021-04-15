package io.github.nosequel.kitpvp.kits.ability;

import io.github.nosequel.kitpvp.kits.KitHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class ItemAbility extends ListenerAbility {

    public ItemAbility(KitHandler kitHandler) {
        super(kitHandler);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (this.isEquipped(player) && event.getItem() != null && event.getItem().getType().equals(this.getMaterial())) {
            this.handle(player);
            event.setCancelled(true);
        }
    }
    /**
     * Get the material of the item
     *
     * @return the material
     */
    public abstract Material getMaterial();

}
