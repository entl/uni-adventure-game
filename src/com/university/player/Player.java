package com.university.player;

import com.university.dungeon.room.Room;
import com.university.player.inventory.InventoryManager;
import com.university.utils.ui.UIManager;
import com.university.utils.commands.Direction;
import com.university.utils.ui.GameNarrator;

/**
 * The {@code Player} class represents the player in the game.
 * The player can move between rooms, interact with items, and be affected by traps and other conditions like sleep or being trapped.
 */
public class Player {
    private int powerPoints;
    private Room currentRoom;
    private InventoryManager inventory;
    private boolean isTrapped;
    private boolean isAsleep;

    /**
     * Constructs a player with the specified amount of power points.
     * The player starts with an empty inventory and is neither trapped nor asleep.
     *
     * @param powerPoints The initial number of power points for the player.
     */
    public Player(Integer powerPoints) {
        this.powerPoints = powerPoints;
        this.inventory = new InventoryManager();
        this.isTrapped = false;
    }

    /**
     * Moves the player in the specified direction if the player is not trapped or asleep.
     * If the destination room contains a trap, the trap is activated.
     *
     * @param direction The direction in which to move the player.
     */
    public void move(Direction direction) {
        currentRoom = currentRoom.getRoomInDirection(direction);
        currentRoom.setVisited(true);
        lookAround();
    }

    /**
     * Allows the player to look around the current room and see the items and adjacent rooms.
     * Describes the room and any items or chests present.
     */
    public void lookAround() {
        UIManager.getInstance().displayMessage(GameNarrator.lookAround(currentRoom));
    }

    /**
     * Returns the current room the player is in.
     *
     * @return The current room of the player.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the player's current room.
     *
     * @param currentRoom The room to set as the player's current room.
     */
    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    /**
     * Returns the player's inventory manager, which is responsible for handling the player's items.
     *
     * @return The inventory manager of the player.
     */
    public InventoryManager getInventoryManager() {
        return inventory;
    }

    /**
     * Returns the player's current power points.
     *
     * @return The player's current power points.
     */
    public int getPowerPoints() {
        return powerPoints;
    }

    /**
     * Adds the specified number of power points to the player.
     *
     * @param powerPoints The number of power points to add.
     */
    public void addPowerPoints(int powerPoints) {
        this.powerPoints += powerPoints;
    }

    /**
     * Removes the specified number of power points from the player.
     *
     * @param powerPoints The number of power points to remove.
     */
    public void removePowerPoints(int powerPoints) {
        this.powerPoints -= powerPoints;
    }

    /**
     * Checks if the player is trapped.
     *
     * @return {@code true} if the player is trapped, {@code false} otherwise.
     */
    public boolean isTrapped() {
        return isTrapped;
    }

    /**
     * Sets the player's trapped status.
     *
     * @param isTrapped {@code true} if the player is trapped, {@code false} otherwise.
     */
    public void setTrapped(boolean isTrapped) {
        this.isTrapped = isTrapped;
    }

    /**
     * Checks if the player is asleep.
     *
     * @return {@code true} if the player is asleep, {@code false} otherwise.
     */
    public boolean isAsleep() {
        return isAsleep;
    }

    /**
     * Sets the player's asleep status.
     *
     * @param isAsleep {@code true} if the player is asleep, {@code false} otherwise.
     */
    public void setAsleep(boolean isAsleep) {
        this.isAsleep = isAsleep;
    }
}
