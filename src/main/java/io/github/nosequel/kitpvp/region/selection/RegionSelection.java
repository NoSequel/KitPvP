package io.github.nosequel.kitpvp.region.selection;

import io.github.nosequel.kitpvp.region.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;

@RequiredArgsConstructor
@Getter
@Setter
public class RegionSelection {

    private final Region region;

    private Location corner1;
    private Location corner2;

}
