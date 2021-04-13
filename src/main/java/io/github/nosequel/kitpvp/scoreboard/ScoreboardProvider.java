package io.github.nosequel.kitpvp.scoreboard;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.profile.Profile;
import io.github.nosequel.kitpvp.profile.ProfileHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.Optional;

public class ScoreboardProvider implements ScoreboardElementHandler {

    private final ProfileHandler profileHandler;

    /**
     * Constructor to make a new scoreboard provider
     *
     * @param handlerManager the handler to get the handlers from
     */
    public ScoreboardProvider(HandlerManager handlerManager) {
        this.profileHandler = handlerManager.find(ProfileHandler.class);
    }

    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();
        final Profile profile = this.profileHandler.findOrMake(player.getUniqueId(), player.getName());

        element.setTitle(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "VAPOR RIP");

        element.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");
        element.add(ChatColor.DARK_AQUA + "Kills: " + ChatColor.AQUA + player.getStatistic(Statistic.PLAYER_KILLS));
        element.add(ChatColor.DARK_AQUA + "Deaths: " + ChatColor.AQUA + player.getStatistic(Statistic.DEATHS));

        if(profile.getKillstreak() != 0) {
            element.add(ChatColor.DARK_AQUA + "Killstreak: " + ChatColor.AQUA + profile.getKillstreak() + ChatColor.GRAY.toString() + ChatColor.BOLD + " ｜ " + ChatColor.AQUA + profile.getHighestKillstreak());
        } else {
            element.add(ChatColor.DARK_AQUA + "Highest Killstreak: " + ChatColor.AQUA + profile.getHighestKillstreak());
        }

        element.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");

        return element;
    }
}