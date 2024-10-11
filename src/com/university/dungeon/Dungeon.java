package com.university.dungeon;

import com.university.dungeon.room.Room;

import java.util.List;

public class Dungeon {
    List<List<Room>> rooms;
    Room entranceRoom;
    int level;

    public Dungeon(List<List<Room>> rooms, Room entranceRoom, int level) {
        this.rooms = rooms;
        this.entranceRoom = entranceRoom;
        this.level = level;
    }

    public List<List<Room>> getRooms() {
        return rooms;
    }

    public Room getEntranceRoom() {
        return entranceRoom;
    }

    public int getLevel() {
        return level;
    }
}
