package com.university.dungeon;

import com.university.dungeon.room.Room;
import java.util.List;

/**
 * The Dungeon class represents a multi-level dungeon in the game.
 * It holds a 2D list of rooms, an entrance room, and the dungeon's current level.
 */
public class Dungeon {

    /** A 2D list representing the dungeon rooms. */
    List<List<Room>> rooms;

    /** The entrance room of the dungeon where the player starts. */
    Room entranceRoom;

    /** The current level of the dungeon. */
    int level;

    /**
     * Constructs a Dungeon instance with the specified rooms, entrance room, and level.
     *
     * @param rooms A 2D list of rooms that make up the dungeon.
     * @param entranceRoom The room that serves as the entrance to the dungeon.
     * @param level The current level of the dungeon.
     */
    public Dungeon(List<List<Room>> rooms, Room entranceRoom, int level) {
        this.rooms = rooms;
        this.entranceRoom = entranceRoom;
        this.level = level;
    }

    /**
     * Returns the 2D list of rooms in the dungeon.
     *
     * @return The 2D list of rooms.
     */
    public List<List<Room>> getRooms() {
        return rooms;
    }

    /**
     * Returns the entrance room of the dungeon.
     *
     * @return The entrance room.
     */
    public Room getEntranceRoom() {
        return entranceRoom;
    }

    /**
     * Returns the current level of the dungeon.
     *
     * @return The dungeon's level.
     */
    public int getLevel() {
        return level;
    }
}
