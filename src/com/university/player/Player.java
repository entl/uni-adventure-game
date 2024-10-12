package com.university.player;

import com.university.dungeon.room.Room;
import com.university.items.IItem;
import com.university.player.inventory.InventoryManager;
import com.university.utils.commands.Direction;


public class Player {
    private String name;
    private int powerPoints;
    private Room currentRoom;
    private InventoryManager inventory;
    private boolean isTrapped;

    public Player(String name, Integer powerPoints) {
        this.name = name;
        this.powerPoints = powerPoints;
        this.inventory = new InventoryManager();
        this.isTrapped = false;
    }

    public void move(Direction direction) {
        if (isTrapped) {
            System.out.println("* You are trapped and cannot move!");
            return;
        }

        Room nextRoom = currentRoom.getRoomInDirection(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.printf("* You moved to the %s room!\n", direction);
        } else {
            System.out.printf("* There is no room to the %s.\n* Try to look around\n", direction);
        }
    }

    public void lookAround() {
        System.out.println("* Looking through darkness...");
        System.out.println("* The glimmer of light reveals **blood-stained signs** etched into the walls.");
        System.out.printf("* You are in the **Room %s**\n\n", currentRoom.getLabel());

        if (currentRoom.getItems() == null) {
            System.out.println("* There's no items in the room.");
        } else {
            System.out.println("* You can barely see following items in the room:");
            for (IItem item : currentRoom.getItems()) {
                System.out.printf("* %s\n", item.getName());
            }
        }
        System.out.println();
        System.out.println("* You manage to see the following rooms:");
        for (Direction direction : currentRoom.getAdjacentRooms().keySet()) {
            System.out.printf("- %s\n", direction);
        }
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }


    public InventoryManager getInventoryManager() {
        return inventory;
    }

    public void addPowerPoints(int powerPoints) {
        this.powerPoints += powerPoints;
    }

    public void removePowerPoints(int powerPoints) {
        this.powerPoints -= powerPoints;
    }

    public boolean isTrapped() {
        return isTrapped;
    }

    public void setTrapped(boolean isTrapped) {
        this.isTrapped = isTrapped;
    }
}
