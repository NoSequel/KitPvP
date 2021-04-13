package io.github.nosequel.kitpvp.kits;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.kits.impl.DefaultKit;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Getter
public class KitHandler implements Handler {

    private final Map<UUID, Kit> equipped = new HashMap<>();
    private final Set<Kit> kits = new HashSet<>(Arrays.asList(
            new DefaultKit(this)
    ));

    /**
     * Find a kit by a player
     *
     * @param player the player to get the kit by
     * @return the optional of the kit
     */
    public Optional<Kit> find(Player player) {
        return Optional.ofNullable(this.equipped.getOrDefault(player.getUniqueId(), null));
    }

}