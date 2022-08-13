package io.github.galaxyvn.gresourcepack;

import io.github.galaxyvn.gresourcepack.commands.Commands;
import io.github.galaxyvn.gresourcepack.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GResourcePack extends JavaPlugin {
    public static GResourcePack plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        reloadConfig();

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        getCommand("gresourcepack").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }
}
