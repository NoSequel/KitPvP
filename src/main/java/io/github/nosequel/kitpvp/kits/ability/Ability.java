package io.github.nosequel.kitpvp.kits.ability;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@Getter
@RequiredArgsConstructor
public abstract class Ability {

    private final KitHandler kitHandler;

    /**
     * Get the duration of the cooldown
     *
     * @return the duration
     */
    public abstract long getCooldownDuration();

    /**
     * Get the parent kit of the ability
     *
     * @return the parent kit
     */
    public abstract Class<? extends Kit> getParentKit();

    /**
     * Handle the ability for the player
     *
     * @param player the player to handle it for
     */
    public void handle(Player player) {
        // todo: cooldown
    }

    /**
     * Check if the player has the kit equipped
     *
     * @param player the player to check
     * @return whether the kit is equipped for the player
     */
    public boolean isEquipped(Player player) {
        return this.kitHandler.find(player).isPresent() && this.kitHandler.find(player).get().getClass().equals(this.getParentKit());
    }
}
