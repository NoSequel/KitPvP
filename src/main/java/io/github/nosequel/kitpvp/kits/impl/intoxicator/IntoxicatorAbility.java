package io.github.nosequel.kitpvp.kits.impl.intoxicator;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.kits.ability.ItemAbility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class IntoxicatorAbility extends ItemAbility {

    public IntoxicatorAbility(KitHandler kitHandler) {
        super(kitHandler);
    }

    /**
     * Handle the ability for the player
     *
     * @param player the player to handle it for
     */
    @Override
    public boolean handle(Player player) {
        super.handle(player);

        for (Entity entity : player.getNearbyEntities(8, 8, 8)) {
            if (entity instanceof Player) {
                final Player target = (Player) entity;

                target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 12 * 20, 4));
                target.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 12 * 20, 4));
                target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 12 * 20, 4));

                target.sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + "You have been hit by an intoxicator...");
            }
        }

        return true;
    }

    /**
     * Get the material of the item
     *
     * @return the material
     */
    @Override
    public Material getMaterial() {
        return Material.COOKED_BEEF;
    }

    /**
     * Get the default duration of the cooldown.
     * <p>
     * This method returns the default duration
     * of the abilities' cooldown in milliseconds.
     *
     * @return the duration
     */
    @Override
    public long getCooldownDuration() {
        return 15000;
    }

    /**
     * Get the parent kit of the ability
     *
     * @return the parent kit
     */
    @Override
    public Class<? extends Kit> getParentKit() {
        return IntoxicatorKit.class;
    }
}
