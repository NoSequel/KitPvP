package io.github.nosequel.kitpvp.kits.menu;

import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.profile.ProfileHandler;
import io.github.nosequel.menus.MenuHandler;
import io.github.nosequel.menus.button.Button;
import io.github.nosequel.menus.button.ButtonBuilder;
import io.github.nosequel.menus.menu.Menu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KitSelectorMenu extends Menu {

    private final KitHandler kitHandler;
    private final ProfileHandler profileHandler;

    public KitSelectorMenu(Player player, KitHandler kitHandler, ProfileHandler profileHandler) {
        super(player, "Select a Kit", 36);
        this.kitHandler = kitHandler;
        this.profileHandler = profileHandler;
    }

    @Override
    public List<Button> getButtons() {
        final AtomicInteger index = new AtomicInteger();
        final List<Button> buttons = new ArrayList<>();

        final Player player = this.getPlayer();
        final int level = this.profileHandler.findOrMake(player.getUniqueId(), player.getName()).getLevel();

        final List<Kit> kits = new ArrayList<>(kitHandler.getKits());
        kits.sort(Comparator.comparingInt(Kit::getRequiredLevel));

        for (Kit kit : kits) {
            final Function<ClickType, Boolean> action = type -> {
                if (level < kit.getRequiredLevel()) {
                    player.sendMessage(ChatColor.RED + "You must be at least level " + kit.getRequiredLevel() + " to use this kit.");
                    return true;
                }

                kit.equip(this.getPlayer());
                this.getPlayer().closeInventory();

                return true;
            };

            final List<String> lore = Arrays.stream(kit.getDescription()).map(line -> ChatColor.GRAY + line).collect(Collectors.toList());

            if (level < kit.getRequiredLevel()) {
                lore.addAll(Arrays.asList(
                        "",
                        ChatColor.RED + "You must be level " + kit.getRequiredLevel() + " to unlock this kit."
                ));
            }

            buttons.add(new ButtonBuilder(index.getAndIncrement(), kit.getIcon())
                    .displayName((level >= kit.getRequiredLevel() ? ChatColor.GREEN : ChatColor.RED) + kit.getKitName())
                    .lore(lore.toArray(new String[0]))
                    .action(action)
            );
        }

        return buttons;
    }

    @Override
    public void onClose(InventoryCloseEvent inventoryCloseEvent) {
        MenuHandler.get().getMenus().remove(inventoryCloseEvent.getPlayer());
    }
}