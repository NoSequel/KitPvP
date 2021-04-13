package io.github.nosequel.kitpvp.kits.impl.kit;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.kits.ability.ItemAbility;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class LauncherAbilityItem extends ItemAbility {

    public LauncherAbilityItem(KitHandler kitHandler) {
        super(kitHandler);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();

        if (this.isEquipped(player) && event.getItem() != null && event.getItem().getType().equals(this.getMaterial())) {
            this.handle(player);
        }
    }

    /**
     * Get the duration of the cooldown
     *
     * @return the duration
     */
    @Override
    public long getCooldownDuration() {
        return 0;
    }

    /**
     * Get the parent kit of the ability
     *
     * @return the parent kit
     */
    @Override
    public Class<? extends Kit> getParentKit() {
        return LauncherKit.class;
    }

    /**
     * Handle the ability for the player
     *
     * @param player the player to handle it for
     */
    @Override
    public void handle(Player player) {
        super.handle(player);

        for(Entity entity : player.getNearbyEntities(8, 8, 8)) {
            if(entity instanceof Player && !entity.getMetadata("protected").iterator().next().asBoolean()) {
                entity.setVelocity(new Vector().setY(1).multiply(1.7));
            }
        }
    }

    /**
     * Get the material of the item
     *
     * @return the material
     */
    @Override
    public Material getMaterial() {
        return Material.GLOWSTONE_DUST;
    }
}
