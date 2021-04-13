package io.github.nosequel.kitpvp.kits;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Kit {

    /**
     * Equip the kit to a player
     *
     * @param player the player to equip it to
     */
    public void equip(Player player) {
        player.sendMessage(ChatColor.YELLOW + "You have equipped the " + ChatColor.GREEN + this.getKitName() + ChatColor.YELLOW + " kit.");
        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[] { null, null, null, null });

        player.getInventory().setArmorContents(this.getArmor());
        player.getInventory().setContents(this.getContents());
    }

    /**
     * Get the contents to give to the player
     *
     * @return the contents
     */
    private ItemStack[] getContents() {
        final ItemStack[] items = new ItemStack[27];

        items[0] = this.getSword();

        for(int i = 0; i < items.length; i++) {
            if(items[i] == null) {
                items[i] = this.getHealthType();
            }
        }

        return items;
    }

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
