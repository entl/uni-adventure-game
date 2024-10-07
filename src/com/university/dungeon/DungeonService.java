package com.university.dungeon;

import com.university.dungeon.room.Room;
import com.university.utils.commands.Direction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DungeonService {
    public DungeonService() {
    }

    public Dungeon initializeDungeon(String dungeonCSV) {
        List<List<Room>> rooms = initializeRooms(dungeonCSV);
        Room entrance = getEntrance(rooms);
        return new Dungeon(rooms, entrance);
    }

    private List<List<Room>> initializeRooms(String dungeonCSV) {
        List<List<String>> dungeonMap = null;
        List<List<Room>> dungeon = new ArrayList<>();

        try {
            dungeonMap = readDungeonCSV(dungeonCSV);
        } catch (FileNotFoundException e) {
            System.out.println("Error initializing rooms: " + e.getMessage());
            System.exit(1);
            return null;
        }

        for (List<String> row : dungeonMap) {
            List<Room> roomRow = new ArrayList<>();
            for (String cell : row) {
                Room room = createRoomFromCell(cell);
                roomRow.add(room);
            }
            dungeon.add(roomRow);
        }

        calculateAdjacentRooms(dungeon);

        return dungeon;
    }

    private List<List<String>> readDungeonCSV(String csvFile) throws FileNotFoundException {
        List<List<String>> dungeon = new ArrayList<>();

        try {
            File file = new File(csvFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                String[] values = line.split(",");

                List<String> lineValues = new ArrayList<>();
                for (String value : values) {
                    lineValues.add(value.trim());
                }
                dungeon.add(lineValues);
            }

            scanner.close();
            return dungeon;
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private Room createRoomFromCell(String cell) {
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

    private void calculateAdjacentRooms(List<List<Room>> rooms) {
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

    public void printDungeon(List<List<Room>> dungeon) {
        for (List<Room> row : dungeon) {
            StringBuilder rowOutput = new StringBuilder();
            for (Room room : row) {
                if (room.isWall()) {
                    rowOutput.append("W,");
                } else if (room.isEntrance()) {
                    rowOutput.append("E,");
                } else if (room.isExit()) {
                    rowOutput.append("X,");
                } else if (room.isTreasureRoom()) {
                    rowOutput.append("T,");
                } else {
                    rowOutput.append("R,");
                }
            }
            // Remove trailing comma and print the row
            if (!rowOutput.isEmpty()) {
                rowOutput.setLength(rowOutput.length() - 1);
            }
            System.out.println(rowOutput.toString());
        }
    }

    public Room getEntrance(List<List<Room>> dungeon) {
        for (List<Room> row : dungeon) {
            for (Room room : row) {
                if (room.isEntrance()) {
                    return room;
                }
            }
        }
        return null;
    }
}
