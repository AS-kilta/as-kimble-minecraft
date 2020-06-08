package com.asdvek.MinecraftASKimble;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// TODO: extract common parsing functionality to another object and extend from there

public class CommandDebug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Make sure a player executed the command
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        // validate command arguments
        if (args.length == 0)
        {
            return false;
        }

        // subcommand handling
        switch (args[0]) {
            case Const.COMMAND_DEBUG_GEN:
                // FIXME: add block generator call
                System.out.println("debug gen called");
                World world = Bukkit.getServer().getWorld("world");
                System.out.println(world);
                Location loc = new Location(world, 0.0, 4.0, 0.0);
                Block block = world.getBlockAt(loc);
                block.setType(Material.DIAMOND_BLOCK);
                break;
            default:
                break;
        }

        return true;
    }
}
