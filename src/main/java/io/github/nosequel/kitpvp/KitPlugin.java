package io.github.nosequel.kitpvp;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.listener.HealthListener;
import io.github.nosequel.kitpvp.listener.KitListener;
import io.github.nosequel.kitpvp.listener.PlayerListener;
import io.github.nosequel.kitpvp.loadout.LoadoutHandler;
import io.github.nosequel.kitpvp.region.Region;
import io.github.nosequel.kitpvp.region.RegionHandler;
import io.github.nosequel.kitpvp.region.command.RegionCommand;
import io.github.nosequel.kitpvp.region.command.adapter.RegionTypeAdapter;
import io.github.nosequel.kitpvp.region.listener.RegionMoveListener;
import io.github.nosequel.kitpvp.region.selection.RegionSelectionListener;
import io.github.nosequel.kitpvp.scoreboard.ScoreboardProvider;
import io.github.nosequel.scoreboard.ScoreboardHandler;
import me.blazingtide.zetsu.Zetsu;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public class KitPlugin extends JavaPlugin {

    private final HandlerManager handler = new HandlerManager();

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(Region.class);

        this.handler.register(new KitHandler());
        this.handler.register(new LoadoutHandler(this.handler));
        this.handler.register(new RegionHandler(this));

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this.handler), this);
        Bukkit.getPluginManager().registerEvents(new KitListener(this.handler), this);
        Bukkit.getPluginManager().registerEvents(new HealthListener(), this);

        Bukkit.getPluginManager().registerEvents(new RegionMoveListener(this.handler), this);
        Bukkit.getPluginManager().registerEvents(new RegionSelectionListener(this.handler.find(RegionHandler.class)), this);

        new ScoreboardHandler(this, new ScoreboardProvider(this.handler), 2L);

        this.handler.stream().forEach(Handler::load);

        // commands
        final Zetsu zetsu = new Zetsu(this);

        zetsu.registerParameterAdapter(Region.class, new RegionTypeAdapter(this.handler.find(RegionHandler.class)));
        zetsu.registerCommands(new RegionCommand(this.handler.find(RegionHandler.class)));
    }

    @Override
    public void onDisable() {
        this.handler.stream().forEach(Handler::unload);
    }

}