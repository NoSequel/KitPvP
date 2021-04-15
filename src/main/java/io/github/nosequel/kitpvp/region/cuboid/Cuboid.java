package io.github.nosequel.kitpvp.region.cuboid;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Cuboid {

    @Expose
    private final String worldName;

    @Expose
    private final int minX;
    @Expose
    private final int minY;
    @Expose
    private final int minZ;

    @Expose
    private final int maxX;
    @Expose
    private final int maxY;
    @Expose
    private final int maxZ;

    private List<Location> locations;

    /**
     * Constructor to make a new cuboid object
     *
     * @param location1 the first corner
     * @param location2 the second corner
     */
    public Cuboid(Location location1, Location location2) {
        if (!location1.getWorld().equals(location2.getWorld())) {
            throw new IllegalArgumentException("World \"" + location1.getWorld().getName() + "\" does not equal to world \"" + location2.getWorld() + "\".");
        }

        this.worldName = location1.getWorld().getName();

        this.minX = Math.min(location1.getBlockX(), location2.getBlockX());
        this.minY = Math.min(location1.getBlockY(), location2.getBlockY());
        this.minZ = Math.min(location1.getBlockZ(), location2.getBlockZ());

        this.maxX = Math.max(location1.getBlockX(), location2.getBlockX());
        this.maxY = Math.max(location1.getBlockY(), location2.getBlockY());
        this.maxZ = Math.max(location1.getBlockZ(), location2.getBlockZ());

        this.initialize();
    }

    /**
     * Initialize the fields inside of the class
     */
    public void initialize() {
        this.locations = this.createLocations();
    }

    /**
     * Create all locations within the cuboid
     *
     * @return the locations
     */
    private List<Location> createLocations() {
        final List<Location> locations = new ArrayList<>();

        for (int y = this.minY; y < this.maxY; y++) { // first do Y axis, from top to bottom.
            for (int x = this.minX; x < this.maxX; x++) {
                for (int z = this.minZ; z < this.maxZ; z++) {
                    locations.add(new Location(Bukkit.getWorld(this.worldName), x, y, z));
                }
            }
        }

        return locations;
    }
}