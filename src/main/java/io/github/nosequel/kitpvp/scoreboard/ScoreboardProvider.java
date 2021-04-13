package io.github.nosequel.kitpvp.scoreboard;

import io.github.nosequel.scoreboard.element.ScoreboardElement;
import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class ScoreboardProvider implements ScoreboardElementHandler {

    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();

        element.setTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "VAPOR" + ChatColor.DARK_RED + " [Alpha]");

        element.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");
        element.add(ChatColor.DARK_RED + "Kills: " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS));
        element.add(ChatColor.DARK_RED + "Deaths: " + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
        element.add(ChatColor.DARK_RED + "Killstreak: " + ChatColor.YELLOW + 0);

        element.add("  ");

        element.add(ChatColor.GRAY.toString() + ChatColor.ITALIC + "vapor.rip");
        element.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");

        return element;
    }
}