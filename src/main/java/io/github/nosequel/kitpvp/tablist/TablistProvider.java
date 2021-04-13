package io.github.nosequel.kitpvp.tablist;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.util.PingUtil;
import io.github.nosequel.tab.shared.entry.TabElement;
import io.github.nosequel.tab.shared.entry.TabElementHandler;
import io.github.nosequel.tab.shared.skin.SkinUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class TablistProvider implements TabElementHandler {

    private final KitHandler kitHandler;

    public TablistProvider(HandlerManager handlerManager) {
        this.kitHandler = handlerManager.find(KitHandler.class);
    }

    @Override
    public TabElement getElement(Player player) {
        final TabElement element = new TabElement();

        element.add(1, 0, ChatColor.GOLD + "Vapor.RIP");
        element.add(1, 1, ChatColor.WHITE.toString() + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());

        element.add(0, 0, ChatColor.GOLD + "Current Kit");
        element.add(0, 1, this.kitHandler.find(player).isPresent() ? this.kitHandler.find(player).get().getKitName() : "None");

        element.add(2, 0, ChatColor.GOLD + "Current Killstreak");
        element.add(2, 1, "0");

        element.add(12, ChatColor.GRAY + "*" + ChatColor.RESET + player.getPlayerListName(), PingUtil.getPing(player), SkinUtil.getSkinDataThrown(player.getUniqueId()));

        int index = 1;

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (index < 60 && !player.equals(target)) {
                if (index % 4 == 0 && index != 0) {
                    index += 1;
                }

                element.add(index + 12, target.getPlayerListName(), PingUtil.getPing(target), SkinUtil.getSkinDataThrown(player.getUniqueId()));
                index++;
            }
        }

        // 1.8+ warning
        element.add(3, 7, ChatColor.DARK_RED + "Warning!");
        element.add(3, 8, ChatColor.WHITE + "We recommend to");
        element.add(3, 9, ChatColor.WHITE + "connect using");
        element.add(3, 10, ChatColor.WHITE + "1.7 for the");
        element.add(3, 11, ChatColor.WHITE + "optimal playing");
        element.add(3, 12, ChatColor.WHITE + "experience.");

        return element;
    }
}