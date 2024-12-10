package com.university.utils.commands;

/**
 * The {@code CommandContext} class represents the context of a command, which includes
 * additional information such as the item name or direction associated with the command.
 */
public class CommandContext {
    private final String itemName;
    private final Direction direction;

    public CommandContext(String itemName, Direction direction) {
        this.itemName = itemName;
        this.direction = direction;
    }

    public CommandContext(Direction direction) {
        this.itemName = null;
        this.direction = direction;
    }

    public CommandContext(String itemName) {
        this.itemName = itemName;
        this.direction = null;
    }

    public String getItemName() {
        return itemName;
    }

    public Direction getDirection() {
        return direction;
    }

}
