package io.github.nosequel.kitpvp.killstreak.impl;

import io.github.nosequel.kitpvp.killstreak.Killstreak;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@RequiredArgsConstructor
public class ItemKillstreak implements Killstreak {

    private final ItemStack[] items;
    private final String killstreakName;
    private final int killAmount;

    /**
     * Get the display name of the killstreak
     *
     * @return the display name
     */
    @Override
    public String getKillstreakName() {
        return this.killstreakName;
    }

    /**
     * Get the amount of kills required to handle the killstreak
     *
     * @return the amount of kills
     */
    @Override
    public Integer getKillAmount() {
        return this.killAmount;
    }

    /**
     * Handle the killstreak for the player.
     * <p>
     * This method does not check if the player has the right amount of kills,
     * this should be checked before the handle method is called.
     *
     * @param player the player
     */
    @Override
    public void handle(Player player) {
        Killstreak.super.handle(player);

        for (ItemStack item : this.items) {
            player.getInventory().addItem(item);
        }
    }
}