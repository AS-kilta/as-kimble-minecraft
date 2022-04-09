package com.asdvek.MinecraftASKimble.commands;

import com.asdvek.MinecraftASKimble.Const;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.ui.popomatic.DiceView;
import com.asdvek.MinecraftASKimble.ui.popomatic.Dome;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

// TODO: extract common parsing functionality to another object and extend from there

public class CommandDebug implements CommandExecutor, TabCompleter {
    private final String[] subcommands = {
            Const.COMMAND_DEBUG_GEN,
            Const.COMMAND_DEBUG_BREAK,
            Const.COMMAND_DEBUG_TEST_DICE
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

        // FIXME: remove once done debugging
        Vec3 popomaticLocation = new Vec3(0.5, -56.5, 100.5);

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
            case Const.COMMAND_DEBUG_TEST_DICE:
                {
                    System.out.println("debug naks called");

                    // generate random dice pop for testing the DiceView
                    Random rand = new Random();
                    Integer samplePop = Math.abs(rand.nextInt()) % 6 + 1;
                    System.out.println("Debug naks returned " + samplePop.toString());

                    DiceView diceView = new DiceView(samplePop, popomaticLocation);
                    diceView.draw();
                    Dome dome = new Dome(popomaticLocation);
                    dome.draw();
                }
                break;
            case Const.COMMAND_DEBUG_CLEAN_DICE:
                {
                    System.out.println("debug clean called");
                    Vec3 cleanStartCorner = Vec3.add(popomaticLocation, new Vec3(-15, -3, -15));
                    Vec3 cleanEndCorner = Vec3.add(popomaticLocation, new Vec3(15, 15, 15));
                    WorldEditor.clearVolume(cleanStartCorner, cleanEndCorner);
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
