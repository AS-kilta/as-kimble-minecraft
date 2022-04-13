package com.asdvek.MinecraftASKimble.commands;

import com.asdvek.MinecraftASKimble.Const;
import com.asdvek.MinecraftASKimble.logic.KimbleBoard;
import com.asdvek.MinecraftASKimble.logic.KimbleGame;
import com.asdvek.MinecraftASKimble.math.Vec3;
import com.asdvek.MinecraftASKimble.WorldEditor;
import com.asdvek.MinecraftASKimble.ui.BoardView;
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
            Const.COMMAND_DEBUG_TEST_DICE,
            Const.COMMAND_DEBUG_GEN_BOARD,
            Const.COMMAND_DEBUG_CLEAN_BOARD
    };

    // instance of the game logic state
    KimbleGame gameState = new KimbleGame();

    // game state visualization
    Vec3 boardOrigin = new Vec3(0.5, -59.5, 0.5);
    BoardView boardView = new BoardView(boardOrigin, gameState);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Make sure a player executed the command
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        // validate command arguments
        if (args.length == 0) { return true; }

        // subcommand handling
        switch (args[0]) {
            case Const.COMMAND_DEBUG_TEST_DICE:
                {
                    System.out.println("debug naks called");
                    int naksResult = gameState.debugNaks();
                    System.out.println("Debug naks returned " + naksResult);
                    boardView.draw();
                }
                break;
            case Const.COMMAND_DEBUG_GEN_BOARD:
                System.out.println("debug board-generate called");
                boardView.draw();
                break;
            case Const.COMMAND_DEBUG_CLEAN_BOARD:
                System.out.println("debug board-clean called");
                boardView.clean();
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
