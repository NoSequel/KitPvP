package io.github.nosequel.kitpvp.kits.ability;

import io.github.nosequel.kitpvp.kits.KitHandler;
import org.bukkit.event.Listener;

public abstract class ListenerAbility extends Ability implements Listener {
    public ListenerAbility(KitHandler kitHandler) {
        super(kitHandler);
    }
}