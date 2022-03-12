package me.cosmic.protectthedirt;

import org.bukkit.plugin.java.JavaPlugin;

public final class ProtectTheDirt extends JavaPlugin {
    public static ProtectTheDirt plugin;
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new DirtBreak(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
