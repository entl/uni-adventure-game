package com.university.utils.commands;

import java.util.Arrays;
import java.util.List;

public enum Command {
    MOVE("Move in a direction (e.g., move north)", "move", "go", "walk"),
    LOOK_AROUND("Look around the room", "look", "examine", "inspect", "search"),
    PICK_UP("Pick up an item (e.g., pick up sword)", "pick", "pickup", "get", "grab"),
    USE("Use an item (e.g., use potion)", "use", "apply", "activate", "eat", "drink", "consume"),
    DROP("Drop an item (e.g., drop hammer)", "drop", "discard", "leave"),
    SHOW_INVENTORY("Show the items in your inventory", "inventory", "items"),
    SHOW_STATS("Show your current stats", "stats", "status", "statistics"),
    HELP("Display the help menu", "help", "commands"),
    QUIT("Quit the game", "quit", "exit"),
    SHOW_MAP("Show the map of the dungeon", "map"),
    ESCAPE("Attempt to escape from a trap", "escape", "run"),
    UNKNOWN("Unknown command");

    private final String description;
    private final List<String> aliases;

    Command(String description, String... aliases) {
        this.description = description;
        this.aliases = Arrays.asList(aliases);
    }

    public String getDescription() {
        return description;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public static Command fromAlias(String alias) {
        for (Command command : values()) {
            if (command.aliases.contains(alias)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}
