package io.github.nosequel.kitpvp.killstreak;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.killstreak.impl.ItemKillstreak;
import io.github.nosequel.kitpvp.profile.Profile;
import io.github.nosequel.kitpvp.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class KillstreakHandler implements Handler {

    private final List<Killstreak> killstreaks = new ArrayList<>(Arrays.asList(
            new ItemKillstreak(new ItemStack[]{new ItemBuilder(Material.WATCH)
                    .setDisplayName(ChatColor.YELLOW + "Select a Kit")
                    .get()},
                    "Kit Resetter",
                    5
            )
    ));


    /**
     * Handle a player killing a different player
     * <p>
     * This method automatically handles the killstreak
     *
     * @param profile the player who killed the other player
     */
    public void handleKill(Profile profile) {
        profile.incrementKillstreak();
        this.find(profile.getKillstreak()).ifPresent(value -> value.handle(profile.getPlayer()));
    }

    /**
     * Find a killstreak by the required amount of kills
     *
     * @param requiredKills the required amount of kills
     * @return the optional of the killstreak
     */
    public Optional<Killstreak> find(int requiredKills) {
        return this.killstreaks.stream()
                .filter(killstreak -> killstreak.getKillAmount() == requiredKills)
                .findFirst();
    }

}
