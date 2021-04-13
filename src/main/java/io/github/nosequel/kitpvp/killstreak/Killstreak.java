package io.github.nosequel.kitpvp.killstreak;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public interface Killstreak {

    /**
     * Get the display name of the killstreak
     *
     * @return the display name
     */
    String getKillstreakName();

    /**
     * Get the amount of kills required to handle the killstreak
     *
     * @return the amount of kills
     */
    Integer getKillAmount();

    /**
     * Handle the killstreak for the player.
     * <p>
     * This method does not check if the player has the right amount of kills,
     * this should be checked before the handle method is called.
     *
     * @param player the player
     */
    default void handle(Player player) {
        Bukkit.broadcastMessage(ChatColor.WHITE + player.getName() + ChatColor.YELLOW + " has reached the " + ChatColor.WHITE + this.getKillstreakName() + " killstreak.");
    }
}
