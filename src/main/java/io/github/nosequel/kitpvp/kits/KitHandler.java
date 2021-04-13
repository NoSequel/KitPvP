package io.github.nosequel.kitpvp.kits;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.kits.impl.DefaultKit;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
public class KitHandler implements Handler {

    private final Set<Kit> kits = new HashSet<>(Arrays.asList(
            new DefaultKit()
    ));
}