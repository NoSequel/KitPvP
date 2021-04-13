package io.github.nosequel.kitpvp;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.listener.KitListener;
import io.github.nosequel.kitpvp.listener.PlayerListener;
import io.github.nosequel.kitpvp.loadout.LoadoutHandler;
import io.github.nosequel.kitpvp.scoreboard.ScoreboardProvider;
import io.github.nosequel.scoreboard.ScoreboardHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPlugin extends JavaPlugin {

    private final HandlerManager handler = new HandlerManager();

    @Override
    public void onEnable() {
        this.handler.register(new KitHandler());
        this.handler.register(new LoadoutHandler(this.handler));

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this.handler), this);
        Bukkit.getPluginManager().registerEvents(new KitListener(this.handler), this);

        new ScoreboardHandler(this, new ScoreboardProvider(this.handler), 2L);

        this.handler.stream().forEach(Handler::load);
    }
}