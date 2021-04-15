package io.github.nosequel.kitpvp.kits.ability;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public abstract class Ability {

    private final KitHandler kitHandler;
    private final Map<Player, Long> cooldowns = new HashMap<>();

    /**
     * Get the default duration of the cooldown.
     * <p>
     * This method returns the default duration
     * of the abilities' cooldown in milliseconds.
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
    public boolean handle(Player player) {
        if(!cooldowns.containsKey(player)) {
            cooldowns.put(player, System.currentTimeMillis());
        }

        if(System.currentTimeMillis() < cooldowns.get(player)+this.getCooldownDuration()) {
            player.sendMessage(ChatColor.RED + "You must wait " + ((cooldowns.get(player)+this.getCooldownDuration() - System.currentTimeMillis()) / 1000) + " seconds until you can use this again.");
            return false;
        }

        this.cooldowns.put(player, System.currentTimeMillis());

        return true;
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
