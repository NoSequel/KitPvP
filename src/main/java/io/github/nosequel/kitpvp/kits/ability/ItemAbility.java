package io.github.nosequel.kitpvp.kits.ability;

import io.github.nosequel.kitpvp.kits.KitHandler;
import org.bukkit.Material;

public abstract class ItemAbility extends ListenerAbility {

    public ItemAbility(KitHandler kitHandler) {
        super(kitHandler);
    }

    /**
     * Get the material of the item
     *
     * @return the material
     */
    public abstract Material getMaterial();

}
