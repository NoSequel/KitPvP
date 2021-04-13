package io.github.nosequel.kitpvp.loadout;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
@Getter
public abstract class Loadout {

    private final LoadoutHandler loadoutHandler;

    /**
     * Get the type of the loadout
     *
     * @return the type of the loadout
     */
    public abstract LoadoutType getLoadoutType();

    /**
     * Equip a new loadout to a player
     *
     * @param player the player to equip it to
     */
    public abstract void equipLoadout(Player player);

}