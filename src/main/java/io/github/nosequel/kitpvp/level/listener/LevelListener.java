package io.github.nosequel.kitpvp.level.listener;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.level.LevelHandler;
import io.github.nosequel.kitpvp.profile.Profile;
import io.github.nosequel.kitpvp.profile.ProfileHandler;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Optional;

public class LevelListener implements Listener {

    private final ProfileHandler profileHandler;
    private final LevelHandler levelHandler;

    /**
     * Constructor to make a new level listener object
     *
     * @param handlerManager the manager to get the handlers from
     */
    public LevelListener(HandlerManager handlerManager) {
        this.profileHandler = handlerManager.find(ProfileHandler.class);
        this.levelHandler = handlerManager.find(LevelHandler.class);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final Optional<Profile> profile = this.profileHandler.find(player.getUniqueId());

        profile.ifPresent(value -> event.setFormat(ChatColor.GRAY + "[" + ChatColor.WHITE + value.getLevel() + "⭐" + ChatColor.GRAY + "] " + ChatColor.RESET + event.getFormat()));
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity().getKiller();

        if (player != null && player.isOnline()) {
            final Optional<Profile> profile = this.profileHandler.find(player.getUniqueId());

            if (profile.isPresent()) {
                profile.get().addExperience(50);

                final int requiredExperience = this.levelHandler.getNextLevelExperience(profile.get());
                final int level = this.levelHandler.getLevel(profile.get());

                if (level != profile.get().getLevel()) {
                    player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "LEVEL UP, " + ChatColor.GRAY +  "you have leveled up to level " + ChatColor.GREEN + level);
                    profile.get().setLevel(level);
                }

                player.sendMessage(ChatColor.GRAY + "You need " + requiredExperience + " more experience for level " + (level + 1) + ".");
            }
        }
    }
}