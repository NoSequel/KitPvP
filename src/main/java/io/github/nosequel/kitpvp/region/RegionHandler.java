package io.github.nosequel.kitpvp.region;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.region.selection.RegionSelection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Getter
@RequiredArgsConstructor
public class RegionHandler implements Handler {

    private final JavaPlugin plugin;

    private final Map<UUID, RegionSelection> selections = new HashMap<>();
    private final List<Region> regions = new ArrayList<>();

    private final Region wildernessRegion = new Region("Wilderness");

    @Override
    public void load() {
        final FileConfiguration config = plugin.getConfig();
        final ConfigurationSection section = config.getConfigurationSection("regions");

        if(section != null) {
            for(String key : section.getKeys(false)) {
                final Region region = (Region) section.get(key);

                if(region.getCuboid() != null) {
                    region.getCuboid().initialize();
                }

                this.regions.add(region);
            }
        } else {
            Bukkit.getLogger().log(Level.WARNING, "No regions could be loaded from the config.yml, please consider creating new mines using the \"/region\" command.");
        }
    }

    @Override
    public void unload() {
        final FileConfiguration config = plugin.getConfig();

        regions.forEach(region -> config.set("regions." + region.getRegionName(), region));
        plugin.saveConfig();
    }

    /**
     * Find a region by an ame
     *
     * @param name the name of th region
     * @return the region optional
     */
    public Optional<Region> find(String name) {
        return this.regions.stream()
                .filter(region -> region.getRegionName().equals(name))
                .findFirst();
    }

    /**
     * Find a region by a location
     *
     * @param location the location
     * @return the region
     */
    public Region find(Location location) {
        return this.regions.stream()
                .filter(region -> region.getCuboid() != null && region.getCuboid().contains(location))
                .findFirst().orElseGet(() -> this.wildernessRegion);
    }

}
