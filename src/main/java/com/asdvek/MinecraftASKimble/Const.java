package com.asdvek.MinecraftASKimble;

/**
 * Global constants used for configuration.
 */

public class Const {
    /* command strings and their respective subcommand strings */

    // kimble
    // Manage a kimble game instance
    public static final String COMMAND_KIMBLE = "kimble";
    public static final String COMMAND_KIMBLE_START    = "start";       // start a new game
    public static final String COMMAND_KIMBLE_STOP     = "stop";        // stop game
    public static final String COMMAND_KIMBLE_SPECTATE = "spectate";    // spectate a given game
    public static final String COMMAND_KIMBLE_LEAVE    = "leave";       // leave spectator mode

    // debug
    // General purpose debugging
    public static final String COMMAND_DEBUG = "debug";
    public static final String COMMAND_DEBUG_GEN = "gen";           // generate blocks
    public static final String COMMAND_DEBUG_BREAK = "break";       // break blocks

    // temporary debugging commands
    public static final String COMMAND_DEBUG_TEST_DICE = "naks";    // call DiceView's draw() function

    // command feedback messages
    public static final String MSG_NOT_IMPLEMENTED = "Command has not been implemented!";
    public static final String MSG_INVALID_COMMAND = "Command does not exist!";

    /* world name (for programmatic world editing) */
    public static final String WORLD_NAME = "world";
}
