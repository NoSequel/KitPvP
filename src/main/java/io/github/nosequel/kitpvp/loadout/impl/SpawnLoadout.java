package io.github.nosequel.kitpvp.loadout.impl;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.kits.menu.KitSelectorMenu;
import io.github.nosequel.kitpvp.loadout.Loadout;
import io.github.nosequel.kitpvp.loadout.LoadoutHandler;
import io.github.nosequel.kitpvp.loadout.LoadoutType;
import io.github.nosequel.kitpvp.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpawnLoadout extends Loadout {

    private final KitHandler kitHandler;

    public SpawnLoadout(HandlerManager handlerManager) {
        super(handlerManager.find(LoadoutHandler.class));
        this.kitHandler = handlerManager.find(KitHandler.class);
    }

    /**
     * Get the type of the loadout
     *
     * @return the type of the loadout
     */
    @Override
    public LoadoutType getLoadoutType() {
        return LoadoutType.SPAWN;
    }

    /**
     * Equip a new loadout to a player
     *
     * @param player the player to equip it to
     */
    @Override
    public void equipLoadout(Player player) {
        this.getLoadoutHandler().clearPlayer(player);

        player.getInventory().setContents(new ItemStack[]{
                null,
                null,
                null,
                null,
                new ItemBuilder(Material.WATCH)
                        .setDisplayName(ChatColor.GOLD + "Select a Kit")
                        .setAction(event -> new KitSelectorMenu(player, this.kitHandler).updateMenu(), player).get(),
        });
    }
}