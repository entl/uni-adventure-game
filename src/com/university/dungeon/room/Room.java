package com.university.dungeon.room;

import com.university.gameElements.chests.IChest;
import com.university.gameElements.traps.ITrap;
import com.university.items.IItem;
import com.university.utils.commands.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a room in the dungeon. Each room can contain items, traps, chests, and
 * information about adjacent rooms in different directions.
 */
public class Room {
    private String label;
    private List<IItem> items;
    private HashMap<Direction, Room> adjacentRooms;
    private boolean isVisited;
    private final boolean isEntrance;
    private final boolean isExit;
    private final boolean isTreasureRoom;
    private final boolean isWall;
    private final ITrap trap;
    private final IChest chest;

    /**
     * Constructor for creating a new Room instance.
     *
     * @param label         The name/label of the room.
     * @param isEntrance    Whether the room is the dungeon's entrance.
     * @param isExit        Whether the room is the dungeon's exit.
     * @param isTreasureRoom Whether the room contains treasure.
     * @param isWall        Whether the room is a wall.
     * @param trap          An optional trap that may be in the room.
     * @param chest         An optional chest that may be in the room.
     */
    public Room(String label, boolean isEntrance, boolean isExit, boolean isTreasureRoom, boolean isWall, ITrap trap, IChest chest) {
        this.label = label;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.isTreasureRoom = isTreasureRoom;
        this.isVisited = false;
        this.isWall = isWall;
        this.trap = trap;
        this.chest = chest;
        this.items = new ArrayList<>();
    }

    /**
     * Retrieves the room in a specified direction.
     *
     * @param direction The direction to check for an adjacent room.
     * @return The room in the specified direction, or null if there is no adjacent room.
     */
    public Room getRoomInDirection(Direction direction) {
        return adjacentRooms.get(direction);
    }

    /**
     * Sets the adjacent rooms for this room.
     *
     * @param adjacentRooms A HashMap of adjacent rooms with their directions.
     */
    public void setAdjacentRooms(HashMap<Direction, Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    /**
     * Returns the list of items present in this room.
     *
     * @return A List of IItem objects representing the items in the room.
     */
    public List<IItem> getItems() {
        return items;
    }

    /**
     * Retrieves an item by its name.
     *
     * @param itemName The name of the item to search for.
     * @return The IItem object if found, otherwise null.
     */
    public IItem getItemByName(String itemName) {
        for (IItem item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Removes an item from the room.
     *
     * @param item The item to remove from the room.
     * @return True if the item was successfully removed, otherwise false.
     */
    public boolean removeItem(IItem item) {
        if (items.contains(item)) {
            items.remove(item);
            return true;
        }
        return false;
    }

    /**
     * Adds an item to the room.
     *
     * @param item The IItem object to add to the room.
     */
    public void addItem(IItem item) {
        items.add(item);
    }

    /**
     * Sets items to the room.
     *
     * @param items The list of IItem objects to add to the room.
     */
    public void setItems(List<IItem> items) {
        this.items = items;
    }


    /**
     * Returns a map of adjacent rooms and their directions.
     *
     * @return A HashMap where the key is the direction and the value is the room in that direction.
     */
    public HashMap<Direction, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    /**
     * Checks if the room is the dungeon entrance.
     *
     * @return True if the room is the entrance, otherwise false.
     */
    public boolean isEntrance() {
        return isEntrance;
    }

    /**
     * Checks if the room is the dungeon exit.
     *
     * @return True if the room is the exit, otherwise false.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Checks if the room contains treasure.
     *
     * @return True if the room is a treasure room, otherwise false.
     */
    public boolean isTreasureRoom() {
        return isTreasureRoom;
    }

    /**
     * Checks if the room has been visited by the player.
     *
     * @return True if the room has been visited, otherwise false.
     */
    public boolean isVisited() {
        return isVisited;
    }

    /**
     * Sets the room's visited status.
     *
     * @param visited True if the room has been visited, otherwise false.
     */
    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    /**
     * Checks if the room is a wall.
     *
     * @return True if the room is a wall, otherwise false.
     */
    public boolean isWall() {
        return isWall;
    }

    /**
     * Returns the label (name) of the room.
     *
     * @return A string representing the room's label.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Retrieves the trap present in the room, if any.
     *
     * @return The ITrap object if there is a trap, otherwise null.
     */
    public ITrap getTrap() {
        return trap;
    }

    /**
     * Retrieves the chest present in the room, if any.
     *
     * @return The IChest object if there is a chest, otherwise null.
     */
    public IChest getChest() {
        return chest;
    }

    /**
     * Provides a description of the room, including its items and whether there is a chest.
     */
    public void describe() {
        if (getItems().isEmpty()) {
            System.out.println("* There's no items in the room.");
        } else {
            System.out.println("* You can barely see the following items in the room:");
            for (IItem item : getItems()) {
                System.out.printf("- %s\n", item.getDisplayName());
            }
        }

        if (getChest() != null) {
            System.out.println("* You see a chest in the room.");
        }
    }
}
