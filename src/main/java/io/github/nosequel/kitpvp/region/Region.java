package io.github.nosequel.kitpvp.region;

import io.github.nosequel.kitpvp.region.cuboid.Cuboid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Region {

    private final String regionName;

    private Cuboid cuboid;

    private boolean gracePeriod = false;
    private boolean damage = true;

}
