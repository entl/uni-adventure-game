package com.university.player;

import com.university.dungeon.room.Room;
import com.university.item.IItem;
import com.university.player.inventory.InventoryManager;
import com.university.utils.commands.Direction;

import java.util.List;

public class Player {
    private String name;
    private int powerPoints;
    private Room currentRoom;
    private InventoryManager inventory;

    public Player(String name, Integer powerPoints) {
        this.name = name;
        this.powerPoints = powerPoints;
        this.inventory = new InventoryManager();
    }

    public void move(Direction direction) {
        Room nextRoom = currentRoom.getRoomInDirection(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.printf("* You moved to the %s room!\n", direction);
        } else {
            System.out.printf("* There is no room to the %s.\n* Try to look around\n", direction);
        }
    }

    public void lookAround() {
        System.out.println("* Look through darkness...");

        if (currentRoom.getItems() == null) {
            System.out.println("* There's no items in the room.");
        } else {
            System.out.println("* You can barely see these items in the room:");
            for (IItem item : currentRoom.getItems()) {
                System.out.printf("* %s\n", item.getName());
            }
        }
        System.out.println("* You manage to see the following rooms:");
        for (Direction direction : currentRoom.getAdjacentRooms().keySet()) {
            System.out.printf("* To the %s\n", direction);
        }
    }

    public void attack() {
        System.out.println("Player attacked!");
    }

    public void pickUp(IItem item) {
        if (currentRoom.removeItem(item)) {
            System.out.printf("* You picked up %s\n", item.getName());
            inventory.addItem(item);
        } else {
            System.out.println("* Item not found in the room!");
        }
    }

    public void drop(IItem item) {
        if (inventory.removeItem(item)) {
            currentRoom.addItem(item);
            System.out.printf("* You dropped %s\n", item.getName());
        } else {
            System.out.println("* Item not found in the inventory!");
        }
    }

    public void use(IItem item) {
        if (inventory.hasItem(item)) {
            item.use();
            inventory.removeItem(item);
        } else {
            System.out.println("* Item not found in the inventory!");
        }
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public IItem getItemFromName(String itemName) {
        return inventory.getItemByName(itemName);
    }

    public List<IItem> getInventory() {
        return inventory.getInventory();
    }
}
