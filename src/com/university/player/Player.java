package com.university.player;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.ITrap;
import com.university.player.inventory.InventoryManager;
import com.university.utils.commands.Direction;


public class Player {
    private String name;
    private int powerPoints;
    private Room currentRoom;
    private InventoryManager inventory;
    private boolean isTrapped;
    private boolean isAsleep;

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

        if (isAsleep) {
            System.out.println("* You are asleep and cannot move!");
            return;
        }

        Room nextRoom = currentRoom.getRoomInDirection(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            currentRoom.setVisited(true);
            ITrap trap = currentRoom.getTrap();
            if (trap != null) {
                trap.activate(GameContext.getInstance());
            }
            System.out.printf("* You moved to the %s room!\n", direction);
        } else {
            System.out.printf("* There is no room to the %s.\n* Try to look around\n", direction);
        }
    }

    public void lookAround() {
        System.out.println("* Looking through darkness...");
        System.out.println("* The glimmer of light reveals **blood-stained signs** etched into the walls.");
        System.out.printf("* You are in the **Room %s**\n\n", currentRoom.getLabel());

        currentRoom.describe();

        System.out.println();
        System.out.println("* You manage to see the following rooms:");
        for (Direction direction : currentRoom.getAdjacentRooms().keySet()) {
            System.out.printf("- %s\n", direction);
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

    public int getPowerPoints() {
        return powerPoints;
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

    public boolean isAsleep() {
        return isAsleep;
    }

    public void setAsleep(boolean isAsleep) {
        this.isAsleep = isAsleep;
    }
}
