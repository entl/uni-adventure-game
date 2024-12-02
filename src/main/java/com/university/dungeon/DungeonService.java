package com.university.dungeon;

import com.university.dungeon.room.Room;
import com.university.dungeon.room.RoomFactory;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * DungeonService is responsible for initializing the dungeon from a CSV file, creating
 * rooms, and setting adjacent room relationships. It also provides utilities for dungeon
 * navigation and printing the dungeon map.
 */
public class DungeonService {

    private static final ILogger logger = LoggerFactory.getLogger(DungeonService.class);

    /**
     * Initializes a dungeon by reading a CSV file, creating a grid of rooms, and setting
     * the entrance room and dungeon level.
     *
     * @param dungeonCSV The path to the CSV file representing the dungeon layout.
     * @param level      The level of the dungeon.
     * @return A Dungeon object containing the initialized rooms and entrance.
     */
    public Dungeon initializeDungeon(String dungeonCSV, int level) {
        logger.info("Initializing dungeon from file: " + dungeonCSV + " at level: " + level);
        List<List<Room>> rooms = initializeDungeonMap(dungeonCSV);
        Room entrance = getEntrance(rooms);
        if (entrance == null) {
            logger.warning("Entrance room not found in dungeon.");
        } else {
            logger.info("Entrance room found: " + entrance.getLabel());
        }
        return new Dungeon(rooms, entrance, level);
    }

    /**
     * Reads the CSV file, creates a 2D grid of rooms, and sets adjacent rooms for each room.
     *
     * @param dungeonCSV The path to the CSV file representing the dungeon layout.
     * @return A 2D list of Room objects.
     */
    private List<List<Room>> initializeDungeonMap(String dungeonCSV) {
        logger.debug("Reading dungeon map from file: " + dungeonCSV);
        List<List<String>> dungeonCells = null;
        List<List<Room>> dungeon = new ArrayList<>();

        try {
            dungeonCells = readDungeonCSV(dungeonCSV);
            logger.info("Dungeon map successfully read from file.");
        } catch (FileNotFoundException e) {
            logger.error("Error initializing rooms: " + e.getMessage());
            System.exit(1);
            return null;
        }

        for (int i = 0; i < dungeonCells.size(); i++) {
            List<Room> roomRow = new ArrayList<>();
            for (int j = 0; j < dungeonCells.get(i).size(); j++) {
                String roomLabel = generateGridLabel(i, j);
                Room room = RoomFactory.createRoomFromCell(dungeonCells.get(i).get(j), roomLabel);
                logger.debug("Created room: " + room.getLabel() + " of type: " + dungeonCells.get(i).get(j));
                roomRow.add(room);
            }
            dungeon.add(roomRow);
        }

        logger.debug("Setting adjacent rooms for the dungeon.");
        RoomFactory.setAdjacentRooms(dungeon);

        return dungeon;
    }

    /**
     * Reads the CSV file and returns the dungeon layout as a list of rows,
     * where each row is a list of strings representing room types.
     *
     * @param csvFile The path to the CSV file.
     * @return A list of lists of strings representing the dungeon layout.
     * @throws FileNotFoundException If the CSV file is not found.
     */
    private List<List<String>> readDungeonCSV(String csvFile) throws FileNotFoundException {
        logger.debug("Reading dungeon CSV file: " + csvFile);
        List<List<String>> dungeon = new ArrayList<>();

        try {
            File file = new File(csvFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    logger.debug("Skipping empty line in CSV file.");
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
            logger.info("CSV file successfully read.");
            return dungeon;
        } catch (FileNotFoundException e) {
            logger.error("Error reading file: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Generates a label for a room based on its row and column indices in the grid.
     * The label format is a combination of a letter (for rows) and a number (for columns).
     *
     * @param rowIndex    The row index of the room.
     * @param columnIndex The column index of the room.
     * @return A string representing the label of the room (e.g., A1, B2).
     */
    private String generateGridLabel(int rowIndex, int columnIndex) {
        char rowLabel = (char) ('A' + rowIndex); // Start from A, each row is a letter
        int columnLabel = columnIndex + 1; // Start from 1, each column is a number
        String label = rowLabel + String.valueOf(columnLabel);

        logger.debug("Generated room label: " + label);
        return label;
    }

    /**
     * Searches for the entrance room in the dungeon layout.
     *
     * @param dungeon A 2D list of Room objects representing the dungeon layout.
     * @return The entrance Room object if found, otherwise null.
     */
    public Room getEntrance(List<List<Room>> dungeon) {
        logger.debug("Searching for entrance room in the dungeon.");
        for (List<Room> row : dungeon) {
            for (Room room : row) {
                if (room.isEntrance()) {
                    logger.info("Entrance room found: " + room.getLabel());
                    return room;
                }
            }
        }
        logger.warning("No entrance room found in the dungeon.");
        return null;
    }
}