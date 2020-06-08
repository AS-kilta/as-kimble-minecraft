package com.asdvek.MinecraftASKimble;

import org.bukkit.plugin.java.JavaPlugin;

public class MinecraftASKimblePlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // register custom commands
        this.getCommand(Const.COMMAND_KIMBLE).setExecutor(new CommandKimble());

        // hello world print
        getLogger().info("onEnable called!");
    }
    @Override
    public void onDisable() {
        getLogger().info("onDisable called!");
    }
}
