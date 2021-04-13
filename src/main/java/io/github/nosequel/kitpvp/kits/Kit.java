package io.github.nosequel.kitpvp.kits;

import org.bukkit.inventory.ItemStack;

public abstract class Kit {

    /**
     * Get the name of the kit
     *
     * @return the name
     */
    public abstract String getKitName();

    /**
     * Get the description of the kit
     *
     * @return the description
     */
    public abstract String[] getDescription();

    /**
     * Get the sword to give to the player
     *
     * @return the sword to give
     */
    public abstract ItemStack getSword();

    /**
     * Get the armor to equip to the player
     *
     * @return the armor to equip
     */
    public abstract ItemStack[] getArmor();

    /**
     * Get the type of the item for health
     *
     * @return the item
     */
    public abstract ItemStack getHealthType();

}
