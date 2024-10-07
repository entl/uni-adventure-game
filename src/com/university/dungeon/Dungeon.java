package com.university.dungeon;

import com.university.dungeon.room.Room;

import java.util.List;

public class Dungeon {
    List<List<Room>> rooms;
    Room entranceRoom;

    public Dungeon(List<List<Room>> rooms, Room entranceRoom) {
        this.rooms = rooms;
        this.entranceRoom = entranceRoom;
    }

    public List<List<Room>> getRooms() {
        return rooms;
    }

    public Room getEntranceRoom() {
        return entranceRoom;
    }
}
