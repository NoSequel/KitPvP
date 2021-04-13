package io.github.nosequel.kitpvp.loadout;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.loadout.impl.SpawnLoadout;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;

public class LoadoutHandler implements Handler {

    private final HandlerManager handlerManager;

    private Loadout[] loadouts;

    /**
     * Constructor to make a new loadout handler
     *
     * @param handlerManager the manager to get the handlers from
     */
    public LoadoutHandler(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }

    @Override
    public void load() {
        this.loadouts = new Loadout[]{
                new SpawnLoadout(handlerManager),
        };
    }

    /**
     * Find a loadout by a {@link LoadoutType}
     *
     * @param type the loadout type
     * @return the found loadout, or a newly created loadout
     */
    public Loadout find(LoadoutType type) {
        return Arrays.stream(this.loadouts)
                .filter(loadout -> loadout.getLoadoutType().equals(type))
                .findFirst().orElseGet(() -> new Loadout(this) {
                    /**
                     * Get the type of the loadout
                     *
                     * @return the type of the loadout
                     */
                    @Override
                    public LoadoutType getLoadoutType() {
                        return LoadoutType.UNIDENTIFIED;
                    }

                    /**
                     * Equip a new loadout to a player
                     *
                     * @param player the player to equip it to
                     */
                    @Override
                    public void equipLoadout(Player player) {
                        clearPlayer(player);
                    }
                });
    }

    /**
     * Clear a player's data
     *
     * @param player the player to clear it for
     */
    public void clearPlayer(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[]{null, null, null, null});

        // clear the player's potions
        for (PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }
}