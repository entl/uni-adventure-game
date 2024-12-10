package com.university.utils.commands;


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
