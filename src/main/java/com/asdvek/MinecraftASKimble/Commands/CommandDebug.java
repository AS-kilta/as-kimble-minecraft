package com.asdvek.MinecraftASKimble.Commands;

import com.asdvek.MinecraftASKimble.Const;
import com.asdvek.MinecraftASKimble.WorldEditor;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: extract common parsing functionality to another object and extend from there

public class CommandDebug implements CommandExecutor, TabCompleter {
    private final String[] subcommands = {
            Const.COMMAND_DEBUG_GEN,
            Const.COMMAND_DEBUG_BREAK,
    };

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
            return true;
        }

        // subcommand handling
        switch (args[0]) {
            case Const.COMMAND_DEBUG_GEN:
                {
                    System.out.println("debug gen called");
                    // WorldEditor.replaceBlock(0.0, 4.0, 0.0, Material.DIAMOND_BLOCK);
                    WorldEditor.replaceVolume(0.0, 0.0, 0.0, 10.0, 10.0, 10.0, Material.DIAMOND_BLOCK);
//                    World world = Bukkit.getServer().getWorld("world");
//                    System.out.println(world);
//                    Location loc = new Location(world, 0.0, 4.0, 0.0);
//                    Block block = world.getBlockAt(loc);
//                    block.setType(Material.DIAMOND_BLOCK);
                }
                break;
            case Const.COMMAND_DEBUG_BREAK:
                {
                    System.out.println("debug break called");
                    WorldEditor.clearVolume(0.0, 0.0, 0.0, 10.0, 10.0, 10.0);
//                    World world = Bukkit.getServer().getWorld("world");
//                    System.out.println(world);
//                    Location loc = new Location(world, 0.0, 4.0, 0.0);
//                    Block block = world.getBlockAt(loc);
//                    block.setType(Material.AIR);
                }
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {
            return new ArrayList<String>(Arrays.asList(subcommands));
        }
        return new ArrayList<String>();
    }
}
