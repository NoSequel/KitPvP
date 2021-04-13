package io.github.nosequel.kitpvp.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Profile implements ConfigurationSerializable {

    private final UUID uuid;
    private final String name;

    private int experience;

    private int killstreak;
    private int highestKillstreak;

    /**
     * Constructor to make a new profile object
     *
     * @param uuid the unique identifier of the profile
     * @param name the name of the player
     */
    public Profile(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    /**
     * Get the player of the profile.
     * <p>
     * This should only be used if the player's object isn't
     * already accessible from where the method is called.
     * <p>
     * This gets the player using <code>Bukkit.getPlayer(uuid)</code>
     *
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    /**
     * Add a certain amount of experience to the profile's current experience
     *
     * @param experience the experience of the player
     */
    public void addExperience(int experience) {
        this.experience+=experience;
    }

    /**
     * Increment the killstreak amount of the profile.
     * <p>
     * This also automatically updates the player's highest
     * killstreak if the current killstreak is higher than the previous
     * highest killstreak.
     */
    public void incrementKillstreak() {
        if(this.killstreak++ >= this.highestKillstreak) {
            this.highestKillstreak = this.killstreak;
        }
    }

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
            put("uuid", uuid.toString());
            put("name", name);

            put("experience", experience);

            put("highestKillstreak", highestKillstreak);
        }};
    }


    /**
     * Required method to deserialize
     *
     * @param args map to deserialize
     * @return serialized region
     * @throws IllegalArgumentException if the args doesn't provide the correct arguments
     */
    public static Profile deserialize(Map<String, Object> args) {
        if (!args.containsKey("uuid") || !args.containsKey("name")) {
            throw new IllegalArgumentException("Provided map in Mine#deserialize didn't have the right pairs");
        }

        final UUID uuid = UUID.fromString((String) args.get("uuid"));
        final String name = (String) args.get("name");
        final Profile profile = new Profile(uuid, name);

        profile.setExperience((Integer) args.getOrDefault("experience", 0));
        profile.setHighestKillstreak((Integer) args.getOrDefault("highestKillstreak", 0));

        return profile;
    }
}