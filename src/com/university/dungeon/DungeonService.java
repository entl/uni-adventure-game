package com.university.dungeon;

import com.university.dungeon.room.Room;
import com.university.dungeon.room.RoomService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DungeonService {
    public DungeonService() {
    }

    public Dungeon initializeDungeon(String dungeonCSV) {
        List<List<Room>> rooms = initializeDungeonMap(dungeonCSV);
        Room entrance = getEntrance(rooms);
        return new Dungeon(rooms, entrance);
    }

    private List<List<Room>> initializeDungeonMap(String dungeonCSV) {
        List<List<String>> dungeonCells = null;
        List<List<Room>> dungeon = new ArrayList<>();

        try {
            dungeonCells = readDungeonCSV(dungeonCSV);
        } catch (FileNotFoundException e) {
            System.out.println("Error initializing rooms: " + e.getMessage());
            System.exit(1);
            return null;
        }

        for (List<String> row : dungeonCells) {
            List<Room> roomRow = new ArrayList<>();
            for (String cell : row) {
                Room room = RoomService.createRoomFromCell(cell);
                roomRow.add(room);
            }
            dungeon.add(roomRow);
        }

        RoomService.setAdjacentRooms(dungeon);

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
