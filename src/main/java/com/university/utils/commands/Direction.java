package com.university.utils.commands;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH("north", "n", "forward"),
    SOUTH("south", "s", "back"),
    EAST("east", "e", "right"),
    WEST("west", "w", "left");

    private List<String> aliases;

    Direction(String... aliases) {
        this.aliases = Arrays.asList(aliases);
    }

    public List<String> getAliases() {
        return aliases;
    }

    public static Direction fromAlias(String alias) {
        for (Direction direction : values()) {
            if (direction.aliases.contains(alias)) {
                return direction;
            }
        }
        return null;
    }
}
