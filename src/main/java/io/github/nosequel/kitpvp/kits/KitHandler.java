package io.github.nosequel.kitpvp.kits;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.kits.ability.Ability;
import io.github.nosequel.kitpvp.kits.ability.ListenerAbility;
import io.github.nosequel.kitpvp.kits.impl.DefaultKit;
import io.github.nosequel.kitpvp.kits.impl.intoxicator.IntoxicatorKit;
import io.github.nosequel.kitpvp.kits.impl.launcher.LauncherKit;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Getter
public class KitHandler implements Handler {

    private final JavaPlugin plugin;

    private final Map<UUID, Kit> equipped = new HashMap<>();
    private final Set<Kit> kits = new HashSet<>();

    /**
     * Constructor to make a new kit handler
     *
     * @param plugin the plugin to register it to
     */
    public KitHandler(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        this.register(new DefaultKit(this));
        this.register(new LauncherKit(this));
        this.register(new IntoxicatorKit(this));
    }

    /**
     * Register a kit to the list of kits
     *
     * @param kit the kit to register
     */
    private void register(Kit kit) {
        if(kit instanceof AbilityKit) {
            final Ability[] abilities = ((AbilityKit) kit).getAbilities();

            for(Ability ability : abilities) {
                if(ability instanceof ListenerAbility) {
                    Bukkit.getPluginManager().registerEvents((Listener) ability, this.plugin);
                }
            }
        }

        this.kits.add(kit);
    }

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