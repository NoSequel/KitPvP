package io.github.nosequel.kitpvp;

import io.github.nosequel.kitpvp.handler.Handler;
import io.github.nosequel.kitpvp.handler.HandlerManager;
import io.github.nosequel.kitpvp.killstreak.KillstreakHandler;
import io.github.nosequel.kitpvp.kits.KitHandler;
import io.github.nosequel.kitpvp.level.LevelHandler;
import io.github.nosequel.kitpvp.level.listener.LevelListener;
import io.github.nosequel.kitpvp.listener.DeathListener;
import io.github.nosequel.kitpvp.listener.HealthListener;
import io.github.nosequel.kitpvp.killstreak.listener.KillstreakListener;
import io.github.nosequel.kitpvp.listener.KitListener;
import io.github.nosequel.kitpvp.listener.PlayerListener;
import io.github.nosequel.kitpvp.loadout.LoadoutHandler;
import io.github.nosequel.kitpvp.profile.Profile;
import io.github.nosequel.kitpvp.profile.ProfileHandler;
import io.github.nosequel.kitpvp.profile.listener.ProfileListener;
import io.github.nosequel.kitpvp.region.Region;
import io.github.nosequel.kitpvp.region.RegionHandler;
import io.github.nosequel.kitpvp.region.command.RegionCommand;
import io.github.nosequel.kitpvp.region.command.adapter.RegionTypeAdapter;
import io.github.nosequel.kitpvp.region.listener.RegionMoveListener;
import io.github.nosequel.kitpvp.region.selection.RegionSelectionListener;
import io.github.nosequel.kitpvp.scoreboard.ScoreboardProvider;
import io.github.nosequel.kitpvp.tablist.TablistProvider;
import io.github.nosequel.scoreboard.ScoreboardHandler;
import io.github.nosequel.tab.shared.TabHandler;
import io.github.nosequel.tab.v1_7_r4.v1_7_R4TabAdapter;
import lombok.Getter;
import me.blazingtide.zetsu.Zetsu;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class KitPlugin extends JavaPlugin {

    private final HandlerManager handler = new HandlerManager();

    @Override
    public void onEnable() {
        ConfigurationSerialization.registerClass(Region.class);
        ConfigurationSerialization.registerClass(Profile.class);

        this.handler.register(new KitHandler(this));
        this.handler.register(new LoadoutHandler(this.handler));
        this.handler.register(new RegionHandler(this));
        this.handler.register(new ProfileHandler(this));
        this.handler.register(new KillstreakHandler());
        this.handler.register(new LevelHandler());

        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        Bukkit.getPluginManager().registerEvents(new KitListener(this.handler), this);
        Bukkit.getPluginManager().registerEvents(new HealthListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new KillstreakListener(this.handler), this);

        Bukkit.getPluginManager().registerEvents(new RegionMoveListener(this), this);
        Bukkit.getPluginManager().registerEvents(new RegionSelectionListener(this.handler.find(RegionHandler.class)), this);

        Bukkit.getPluginManager().registerEvents(new LevelListener(this.handler), this);

        Bukkit.getPluginManager().registerEvents(new ProfileListener(this.handler), this);

        new ScoreboardHandler(this, new ScoreboardProvider(this.handler), 2L);
        new TabHandler(new v1_7_R4TabAdapter(), new TablistProvider(this.handler), this, 5L);

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