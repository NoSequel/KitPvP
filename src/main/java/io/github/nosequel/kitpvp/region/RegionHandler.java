package io.github.nosequel.kitpvp.region;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.region.selection.RegionSelection;
import lombok.Getter;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Getter
public class RegionHandler implements Handler {

    private final Map<UUID, RegionSelection> selections = new HashMap<>();
    private final List<Region> regions = new ArrayList<>();
    private final Region wildernessRegion = new Region("Wilderness");

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
