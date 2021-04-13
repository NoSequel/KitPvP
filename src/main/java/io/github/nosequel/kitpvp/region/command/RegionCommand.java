package io.github.nosequel.kitpvp.region.command;

import io.github.nosequel.kitpvp.region.Region;
import io.github.nosequel.kitpvp.region.RegionHandler;
import io.github.nosequel.kitpvp.region.selection.RegionSelection;
import lombok.RequiredArgsConstructor;
import me.blazingtide.zetsu.model.annotations.Command;
import me.blazingtide.zetsu.permission.impl.PermissionNode;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@RequiredArgsConstructor
public class RegionCommand {

    private final RegionHandler regionHandler;

    @Command(labels = "region", description = "A general region command")
    public void region(Player player) {
        player.sendMessage(new String[]{
                ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 52),
                ChatColor.RED + "/region create <name>",
                ChatColor.RED + "/region delete <region>",
                ChatColor.RED + "/region select <region>",
                ChatColor.RED + "/region damage <region> <true|false>",
                ChatColor.RED + "/region graceperiod <region> <true|false>",
                ChatColor.BLUE + ChatColor.STRIKETHROUGH.toString() + StringUtils.repeat("-", 52),
        });
    }

    @Command(labels = "region create", description = "Create a new region")
    @PermissionNode("region.create")
    public void create(Player player, String name) {
        if (this.regionHandler.find(name).isPresent()) {
            player.sendMessage(ChatColor.RED + "A region with that name already exists.");
            return;
        }

        this.regionHandler.getRegions().add(new Region(name));
        player.sendMessage(ChatColor.GOLD + "You have created a new region.");
    }

    @Command(labels = "region delete", description = "Delete an existing region")
    @PermissionNode("region.delete")
    public void delete(Player player, Region region) {
        this.regionHandler.getRegions().remove(region);
        player.sendMessage(ChatColor.RED + "You have deleted a region.");
    }

    @Command(labels = "region select", description = "Select the region for the region ???")
    @PermissionNode("region.select")
    public void select(Player player, Region region) {
        this.regionHandler.getSelections().put(player.getUniqueId(), new RegionSelection(region));

        player.sendMessage(new String[] {
                "",
                ChatColor.GREEN + "You are currently selecting the region for the " + ChatColor.YELLOW + region.getRegionName() + ChatColor.YELLOW + " mine,",
                ChatColor.GRAY + " - " + ChatColor.GREEN + "Left-click a block to set the first location,",
                ChatColor.GRAY + " - " + ChatColor.GREEN + "Right-click a block to set the second location,",
                ChatColor.GRAY + " - " + ChatColor.GREEN + "Shift-left-click a block to finish the selection,",
                ChatColor.GRAY + "To cancel your mine selection task, execute \"/mine cancelselect\"",
                ""
        });
    }

    @Command(labels = "region damage", description = "Toggle the damage state of the region")
    @PermissionNode("region.damage")
    public void damage(Player player, Region region, Boolean toggled) {
        region.setDamage(toggled);
        player.sendMessage(ChatColor.GOLD + "You have toggled the damage state of the region to " + ChatColor.RED + toggled);
    }

    @Command(labels = "region graceperiod", description = "Toggle the grace period of the region")
    @PermissionNode("region.graceperiod")
    public void graceperiod(Player player, Region region, Boolean toggled) {
        region.setGracePeriod(toggled);
        player.sendMessage(ChatColor.GOLD + "You have toggled the grace period state of the region to " + ChatColor.RED + toggled);
    }
}
