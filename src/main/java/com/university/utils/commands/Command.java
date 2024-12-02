package com.university.utils.commands;

import java.util.Arrays;
import java.util.List;

/**
 * The {@code Command} enum represents the different commands that a player can execute in the game.
 * Each command has a description and a list of possible aliases, making it easier to interpret user input.
 */
public enum Command {
    /**
     * Command to move in a direction (e.g., move north).
     */
    MOVE("Move in a direction (e.g., move north)", "move", "go", "walk"),

    /**
     * Command to look around the current room and observe surroundings.
     */
    LOOK_AROUND("Look around the room", "look", "examine", "inspect", "search"),

    /**
     * Command to pick up an item in the room (e.g., pick up sword).
     */
    PICK_UP("Pick up an item (e.g., pick up sword)", "pick", "pickup", "get", "grab"),

    /**
     * Command to use an item from the player's inventory (e.g., use potion).
     */
    USE("Use an item (e.g., use potion)", "use", "apply", "activate", "eat", "drink", "consume", "cast"),


    /**
     * Command to drop an item from the player's inventory (e.g., drop hammer).
     */
    DROP("Drop an item (e.g., drop hammer)", "drop", "discard", "leave"),

    /**
     * Command to show the player's current inventory.
     */
    SHOW_INVENTORY("Show the items in your inventory", "inventory", "items"),

    /**
     * Command to display the player's current stats.
     */
    SHOW_STATS("Show your current stats", "stats", "status", "statistics"),

    /**
     * Command to display the help menu with all available commands.
     */
    HELP("Display the help menu", "help", "commands"),

    /**
     * Command to quit the game.
     */
    QUIT("Quit the game", "quit", "exit"),

    /**
     * Command to show the map of the dungeon.
     */
    SHOW_MAP("Show the map of the dungeon", "map"),

    /**
     * Command to attempt to escape from a trap.
     */
    ESCAPE("Attempt to escape from a trap", "escape", "run"),

    /**
     * Command for unknown or unrecognized input.
     */
    UNKNOWN("Unknown command");

    private final String description;
    private final List<String> aliases;

    /**
     * Constructs a {@code Command} with a description and a list of aliases.
     *
     * @param description The description of the command.
     * @param aliases     The list of possible aliases for the command.
     */
    Command(String description, String... aliases) {
        this.description = description;
        this.aliases = Arrays.asList(aliases);
    }

    /**
     * Returns the description of the command.
     *
     * @return The description of the command.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the list of aliases for the command.
     *
     * @return The list of aliases.
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Finds the corresponding {@code Command} from the given alias.
     * If no matching command is found, it returns {@code UNKNOWN}.
     *
     * @param alias The alias to match.
     * @return The corresponding {@code Command} or {@code UNKNOWN} if no match is found.
     */
    public static Command fromAlias(String alias) {
        for (Command command : values()) {
            if (command.aliases.contains(alias)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}
