package com.asdvek.MinecraftASKimble;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

// TODO: Proper subcommand handling
// TODO: Proper subcommand cli argument verification

public class CommandKimble implements CommandExecutor {
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
            usage();
            return false;
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
            default:
                // FIXME: Invalid command entered, handle accordingly
                ItemStack flesh = new ItemStack(Material.ROTTEN_FLESH);
                player.getInventory().addItem(flesh);
                break;
        }

        return true;
    }

    // print usage message
    private void usage() {
        // TODO: send a private message with usage instructions to player who invoked the command
    }
}
