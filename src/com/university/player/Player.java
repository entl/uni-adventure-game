package com.university.player;

import com.university.dungeon.room.Room;
import com.university.item.IItem;
import com.university.utils.commands.Direction;

import java.util.List;

public class Player {
    private String name;
    private int powerPoints;
    private Room currentRoom;
    private List<IItem> inventory;

    public Player(String name, Integer powerPoints) {
        this.name = name;
        this.powerPoints = powerPoints;
    }

    public void move(Direction direction) {
        Room nextRoom = currentRoom.getRoomInDirection(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("Player moved to the next room!");
        } else {
            System.out.println("There is no room in that direction!");
        }
    }

    public void lookAround() {
        System.out.println("Player looked around!");
        if (!currentRoom.getItems().isEmpty()) {
            System.out.println("Items in the room:");
            for (IItem item : currentRoom.getItems()) {
                System.out.println(item.getName());
            }
        } else {
            System.out.println("No items in the room.");
        }

        // Print adjacent rooms
        System.out.println("Adjacent rooms:");
        for (Direction direction : currentRoom.getAdjacentRooms().keySet()) {
            System.out.println(direction);
        }
    }

    public void attack() {
        System.out.println("Player attacked!");
    }

    public void use() {
        System.out.println("Player used item!");
    }
}
