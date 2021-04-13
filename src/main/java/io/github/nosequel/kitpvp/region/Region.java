package io.github.nosequel.kitpvp.region;

import io.github.nosequel.kitpvp.KitConstants;
import io.github.nosequel.kitpvp.region.cuboid.Cuboid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class Region implements ConfigurationSerializable {

    private final String regionName;

    private Cuboid cuboid;

    private boolean damage = true;

    /**
     * Creates a Map representation of this class.
     * <p>
     * This class must provide a method to restore this class, as defined in
     * the {@link ConfigurationSerializable} interface javadocs.
     *
     * @return Map containing the current state of this class
     */
    @Override
    public Map<String, Object> serialize() {
        return new HashMap<String, Object>() {{
            put("regionName", regionName);
            put("damage", damage);

            if(cuboid != null) {
                put("cuboid", KitConstants.GSON.toJson(cuboid));
            }
        }};
    }

    /**
     * Required method to deserialize
     *
     * @param args map to deserialize
     * @return serialized region
     * @throws IllegalArgumentException if either corner1 or corner2 isn't in the map to deserialize
     */
    public static Region deserialize(Map<String, Object> args) {
        if (!args.containsKey("regionName") || !args.containsKey("damage")) {
            throw new IllegalArgumentException("Provided map in Mine#deserialize didn't have the right pairs");
        }

        final String name = (String) args.get("regionName");
        final Region region = new Region(name);

        region.setDamage((Boolean) args.get("damage"));

        if(args.containsKey("cuboid")) {
            region.setCuboid(KitConstants.GSON.fromJson((String) args.get("cuboid"), Cuboid.class));
        }

        return region;
    }
}