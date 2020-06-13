package com.asdvek.MinecraftASKimble.Commands;

import com.asdvek.MinecraftASKimble.Const;
import com.asdvek.MinecraftASKimble.Vec3;
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
import java.util.Vector;

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
                    final double blockSize = 16;
                    Vec3 vOrigin = new Vec3(0.5, 4.5, 0.5);
                    Vec3 vOffset = new Vec3(blockSize-1); // ending point is inclusive so subtract 1 from offset

                    WorldEditor.replaceVolume(vOrigin, Vec3.add(vOrigin, vOffset), Material.DIAMOND_BLOCK);

                    // replace block core with bedrock :)
                    WorldEditor.replaceVolume(
                            Vec3.add(vOrigin, new Vec3(1.0)),
                            Vec3.sub(Vec3.add(vOrigin, vOffset), new Vec3(1.0)),
                            Material.BEDROCK
                    );
                }
                break;
            case Const.COMMAND_DEBUG_BREAK:
                {
                    System.out.println("debug break called");
                    final double blockSize = 16;
                    Vec3 vOrigin = new Vec3(0.5, 4.5, 0.5);
                    Vec3 vOffset = new Vec3(blockSize-1); // ending point is inclusive so subtract 1 from offset
                    WorldEditor.clearVolume(vOrigin, Vec3.add(vOrigin, vOffset));
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
