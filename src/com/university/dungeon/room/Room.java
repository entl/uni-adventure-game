package com.university.dungeon.room;

import com.university.items.IItem;
import com.university.utils.commands.Direction;

import java.util.HashMap;
import java.util.List;

public class Room {
    private String label;
    private List<IItem> items;
    private HashMap<Direction, Room> adjacentRooms;
    private boolean isVisited;
    private final boolean isEntrance;
    private final boolean isExit;
    private final boolean isTreasureRoom;
    private final boolean isWall;

    public Room(String label, List<IItem> items, boolean isEntrance, boolean isExit, boolean isTreasureRoom, boolean isWall) {
        this.label = label;
        this.items = items;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.isTreasureRoom = isTreasureRoom;
        this.isVisited = false;
        this.isWall = isWall;
    }

    public IItem pickUpItem(String itemName) {
        System.out.println("Item picked up!");
        for (IItem item : items) {
            if (item.getName().equals(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public Room getRoomInDirection(Direction direction) {
        return adjacentRooms.get(direction);
    }

    public void setAdjacentRooms(HashMap<Direction, Room> adjacentRooms) {
        this.adjacentRooms = adjacentRooms;
    }

    public List<IItem> getItems() {
        return items;
    }

    public void setItems(List<IItem> items) {
        this.items = items;
    }

    public boolean removeItem(IItem item) {
        if (items.contains(item)) {
            items.remove(item);
            return true;
        }
        return false;
    }

    public void addItem(IItem item) {
        items.add(item);
    }

    public HashMap<Direction, Room> getAdjacentRooms() {
        return adjacentRooms;
    }

    public boolean isEntrance() {
        return isEntrance;
    }

    public boolean isExit() {
        return isExit;
    }

    public boolean isTreasureRoom() {
        return isTreasureRoom;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public boolean isWall() {
        return isWall;
    }

    public String getLabel() {
        return label;
    }
}
