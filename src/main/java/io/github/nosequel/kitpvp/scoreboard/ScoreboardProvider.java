package io.github.nosequel.kitpvp.scoreboard;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.scoreboard.element.ScoreboardElement;
import io.github.nosequel.scoreboard.element.ScoreboardElementHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Optional;

public class ScoreboardProvider implements ScoreboardElementHandler {

    private final KitHandler kitHandler;

    /**
     * Constructor to make a new scoreboard provider
     *
     * @param handlerManager the handler to get the handlers from
     */
    public ScoreboardProvider(HandlerManager handlerManager) {
        this.kitHandler = handlerManager.find(KitHandler.class);
    }

    @Override
    public ScoreboardElement getElement(Player player) {
        final ScoreboardElement element = new ScoreboardElement();

        element.setTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "VAPOR RIP");

        element.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");
        element.add(ChatColor.DARK_RED + "Kills: " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS));
        element.add(ChatColor.DARK_RED + "Deaths: " + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
        element.add(ChatColor.DARK_RED + "Killstreak: " + ChatColor.YELLOW + 0);

        if(kitHandler != null) {
            this.kitHandler.find(player).ifPresent(kit ->
                    element.add(ChatColor.DARK_RED + "Kit: " + ChatColor.YELLOW + kit.getKitName())
            );
        }

        element.add("  ");

        element.add(ChatColor.GRAY.toString() + ChatColor.ITALIC + "vapor.rip");
        element.add(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------");

        return element;
    }
}