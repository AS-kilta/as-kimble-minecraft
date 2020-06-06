package com.asdvek.MinecraftASKimble;

import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftASKimblePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("onEnable called!");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable called!");
    }
}
