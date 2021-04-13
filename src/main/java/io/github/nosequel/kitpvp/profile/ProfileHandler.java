package io.github.nosequel.kitpvp.profile;

import io.github.nosequel.kitpvp.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@RequiredArgsConstructor
public class ProfileHandler implements Handler {

    private final JavaPlugin plugin;
    private final Map<UUID, Profile> profiles = new HashMap<>();

    @Override
    public void load() {
        final FileConfiguration config = plugin.getConfig();
        final ConfigurationSection section = config.getConfigurationSection("profiles");

        if (section != null) {
            for (String key : section.getKeys(false)) {
                final Profile profile = (Profile) section.get(key);

                this.profiles.put(profile.getUuid(), profile);
            }
        } else {
            Bukkit.getLogger().log(Level.WARNING, "No profiles could be loaded from the config.yml.");
        }
    }

    @Override
    public void unload() {
        final FileConfiguration config = plugin.getConfig();

        for (Profile profile : profiles.values()) {
            config.set("profiles." + profile.getUuid().toString(), profile);
        }

        plugin.saveConfig();
    }

    /**
     * Find a profile by a unique identifier
     *
     * @param uuid the unique identifier to find it by
     * @return the optional of the profile
     */
    public Optional<Profile> find(UUID uuid) {
        return Optional.ofNullable(this.profiles.getOrDefault(uuid, null));
    }

    /**
     * Find a profile by a uuid.
     * <p>
     * This method automatically makes & registers the newly created profile.
     *
     * @param uuid the unique identifier of the profile to search it by
     * @param name the name to give the profile if no profile could be found
     * @return the profile
     */
    public Profile findOrMake(UUID uuid, String name) {
        return this.find(uuid).orElseGet(() -> {
            final Profile profile = new Profile(uuid, name);

            this.profiles.put(uuid, profile);
            return profile;
        });
    }
}