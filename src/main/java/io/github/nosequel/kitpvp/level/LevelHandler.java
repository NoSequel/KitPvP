package io.github.nosequel.kitpvp.level;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.profile.Profile;

public class LevelHandler implements Handler {

    private final int experienceRequired = 250;

    /**
     * Get the level of a profile.
     * <p>
     * The level of the player is the player's experience
     * divided by the required experience rounded down.
     *
     * @param profile the profile to get the exp from
     * @return the level of the player
     */
    public int getLevel(Profile profile) {
        return (int) Math.floor((float) profile.getExperience() / (float) experienceRequired);
    }

    /**
     * Get the amount of experience required for the next level.
     *
     * @param profile the profile to get the exp and level from
     * @return the required experience
     */
    public int getNextLevelExperience(Profile profile) {
        return -(profile.getExperience() - (this.getLevel(profile)+1)*this.experienceRequired);
    }
}