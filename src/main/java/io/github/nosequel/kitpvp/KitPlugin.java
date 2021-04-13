package io.github.nosequel.kitpvp;

import io.github.nosequel.kitpvp.handler.HandlerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPlugin extends JavaPlugin {

    private final HandlerManager handler = new HandlerManager();

    @Override
    public void onEnable() {

    }
}