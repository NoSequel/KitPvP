package io.github.nosequel.kitpvp.region.command.adapter;

import io.github.nosequel.kitpvp.region.Region;
import io.github.nosequel.kitpvp.region.RegionHandler;
import lombok.RequiredArgsConstructor;
import me.blazingtide.zetsu.adapters.ParameterAdapter;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@RequiredArgsConstructor
public class RegionTypeAdapter implements ParameterAdapter<Region> {

    private final RegionHandler regionHandler;

    @Override
    public Region process(String source) {
        return this.regionHandler.find(source).orElse(null);
    }

    @Override
    public void processException(CommandSender sender, String source, Exception exception) {
        sender.sendMessage(ChatColor.RED + "Unable to find region by name \"" + source + "\".");
    }
}
