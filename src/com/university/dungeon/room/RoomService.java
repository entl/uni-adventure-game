package com.university.dungeon.room;

import com.university.utils.commands.Direction;

import java.util.HashMap;
import java.util.List;

public class RoomService {
    public static Room createRoomFromCell(String cell) {
        return switch (cell.toUpperCase()) {
            case "W" ->
                // Wall
                    new Room(null, false, false, false, true);
            case "E" ->
                // Entrance
                    new Room(null, true, false, false, false);
            case "X" ->
                // Exit
                    new Room(null, false, true, false, false);
            case "T" ->
                // Treasure
                    new Room(null, false, false, true, false);
            case "R" ->
                // Regular Room
                    new Room(null, false, false, false, false);
            default ->
                // Handle unknown cell types as walls for safety
                    new Room(null, false, false, false, true);
        };
    }


    public static void setAdjacentRooms(List<List<Room>> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).size(); j++) {
                Room currentRoom = rooms.get(i).get(j);
                HashMap<Direction, Room> adjacentRooms = new HashMap<>();

                if (currentRoom.isWall()) {
                    // Walls do not have adjacent rooms
                    currentRoom.setAdjacentRooms(adjacentRooms);
                    continue;
                }
                // North
                if (i > 0) {
                    Room northRoom = rooms.get(i - 1).get(j);
                    if (!northRoom.isWall()) {
                        adjacentRooms.put(Direction.NORTH, northRoom);
                    }
                }
                // South
                if (i < rooms.size() - 1) {
                    Room southRoom = rooms.get(i + 1).get(j);
                    if (!southRoom.isWall()) {
                        adjacentRooms.put(Direction.SOUTH, southRoom);
                    }
                }
                // West
                if (j > 0) {
                    Room westRoom = rooms.get(i).get(j - 1);
                    if (!westRoom.isWall()) {
                        adjacentRooms.put(Direction.WEST, westRoom);
                    }
                }
                // East
                if (j < rooms.get(i).size() - 1) {
                    Room eastRoom = rooms.get(i).get(j + 1);
                    if (!eastRoom.isWall()) {
                        adjacentRooms.put(Direction.EAST, eastRoom);
                    }
                }

                currentRoom.setAdjacentRooms(adjacentRooms);
            }
        }
    }
}
