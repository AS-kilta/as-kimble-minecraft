package com.asdvek.MinecraftASKimble.commands;

import com.asdvek.MinecraftASKimble.Const;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// TODO: extract common parsing functionality to another object and extend from there
// TODO: Proper subcommand handling
// TODO: Proper subcommand cli argument verification

public class CommandKimble implements CommandExecutor, TabCompleter {
    private final String[] subcommands = {
            Const.COMMAND_KIMBLE_START,
            Const.COMMAND_KIMBLE_STOP,
            Const.COMMAND_KIMBLE_SPECTATE,
            Const.COMMAND_KIMBLE_LEAVE,
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
            usage(player);
            return true;
        }

        // subcommand handling
        switch (args[0]) {
            case Const.COMMAND_KIMBLE_START:
                // FIXME:   Validate start subcommand args and start a new game.
                //          Currently just gives a diamond the the player who invoked the command.
                ItemStack diamond = new ItemStack(Material.DIAMOND);
                player.getInventory().addItem(diamond);
                break;
            case Const.COMMAND_KIMBLE_STOP:
                // FIXME: Validate start subcommand args and stop a game if appropriate
                ItemStack emerald = new ItemStack(Material.EMERALD);
                player.getInventory().addItem(emerald);
                break;
            case Const.COMMAND_KIMBLE_SPECTATE:
                // FIXME:   Validate start subcommand args and teleport player to spectate a given game
                player.sendMessage(Const.MSG_NOT_IMPLEMENTED);
                break;
            case Const.COMMAND_KIMBLE_LEAVE:
                // FIXME: Validate start subcommand args and remove player from spectator area
                player.sendMessage(Const.MSG_NOT_IMPLEMENTED);
                break;
            default:
                // FIXME: Invalid command entered, handle accordingly
                player.sendMessage(Const.MSG_INVALID_COMMAND);
                break;
        }

        return true;
    }

    // print usage message
    private void usage(Player player) {
        player.sendMessage(String.format(String.format("Usage: /%s <subcommand>", Const.COMMAND_KIMBLE)));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1)
        {
            return new ArrayList<String>(Arrays.asList(subcommands));
        }
        return new ArrayList<String>();
    }
}
