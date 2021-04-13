package io.github.nosequel.kitpvp.kits.menu;

import com.sun.org.apache.xpath.internal.operations.String;
import io.github.nosequel.kitpvp.kits.Kit;
import io.github.nosequel.kitpvp.kits.KitHandler;
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
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class KitSelectorMenu extends Menu {

    private final KitHandler kitHandler;

    public KitSelectorMenu(Player player, KitHandler kitHandler) {
        super(player, "Select a Kit", 36);
        this.kitHandler = kitHandler;
    }

    @Override
    public List<Button> getButtons() {
        final AtomicInteger index = new AtomicInteger();
        final List<Button> buttons = new ArrayList<>();

        for (Kit kit : kitHandler.getKits()) {
            final Function<ClickType, Boolean> action = type -> {
                kit.equip(this.getPlayer());
                this.getPlayer().closeInventory();

                return true;
            };

            buttons.add(new ButtonBuilder(index.getAndIncrement(), kit.getIcon())
                    .displayName(ChatColor.GREEN + kit.getKitName())
                    .lore(
                            Arrays.stream(kit.getDescription())
                                    .map(line -> ChatColor.GRAY + line)
                                    .toArray(java.lang.String[]::new)

                    ).action(action)
            );
        }

        return buttons;
    }

    @Override
    public void onClose(InventoryCloseEvent inventoryCloseEvent) {
        MenuHandler.get().getMenus().remove(inventoryCloseEvent.getPlayer());
    }
}