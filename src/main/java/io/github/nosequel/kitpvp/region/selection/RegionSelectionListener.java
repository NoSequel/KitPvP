package io.github.nosequel.kitpvp.region.selection;

import io.github.nosequel.kitpvp.region.Region;
import io.github.nosequel.kitpvp.region.RegionHandler;
import io.github.nosequel.kitpvp.region.cuboid.Cuboid;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

@RequiredArgsConstructor
public class RegionSelectionListener implements Listener {

    private final RegionHandler selectionHandler;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final RegionSelection selection = this.selectionHandler.getSelections().get(player.getUniqueId());

        if (selection != null && event.getClickedBlock() != null) {
            final Location blockLocation = event.getClickedBlock().getLocation();

            switch (event.getAction()) {
                case LEFT_CLICK_BLOCK: {
                    if (player.isSneaking()) {
                        if (selection.getCorner1() == null || selection.getCorner2() == null) {
                            player.sendMessage(ChatColor.RED + "Either corner 1 or corner 2 is not set.");
                            return;
                        }

                        final Region region = selection.getRegion();

                        this.selectionHandler.getSelections().remove(player.getUniqueId());

                        region.setCuboid(new Cuboid(selection.getCorner1(), selection.getCorner2()));
                        player.sendMessage(ChatColor.GREEN + "You have finished the mine selection, " + region.getRegionName() + "'s region has been updated, and the old region has been removed.");
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "You have set the first position! " + ChatColor.GREEN + "(" + blockLocation.toString() + ")");
                        selection.setCorner1(blockLocation);
                    }
                }
                break;
                case RIGHT_CLICK_BLOCK: {
                    player.sendMessage(ChatColor.YELLOW + "You have set the second position! " + ChatColor.GREEN + "(" + blockLocation.toString() + ")");
                    selection.setCorner2(blockLocation);
                }
                break;
            }

            event.setCancelled(true);
        }
    }
}