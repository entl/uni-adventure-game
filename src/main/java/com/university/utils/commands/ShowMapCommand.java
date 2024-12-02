package com.university.utils.commands;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

import java.util.*;

/**
 * The {@code ShowMapCommand} class implements the {@code ICommand} interface
 * and is responsible for displaying the map of the dungeon.
 * The map will only show the rooms that the player has visited, marking their current position.
 * Unvisited rooms will be represented as unexplored areas.
 */
public class ShowMapCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(ShowMapCommand.class);

    /**
     * Executes the command to display the map of the dungeon.
     * The map shows the player's current room, visited rooms, and unexplored areas.
     *
     * @param context the current game context which contains the dungeon and player information.
     */
    @Override
    public void execute(GameContext context) {
        logger.debug("Executing ShowMapCommand");

        List<List<Room>> rooms = GameContext.initialize().getCurrentDungeon().getRooms();
        Room currentRoom = context.getPlayer().getCurrentRoom();

        logger.info("Generating map grid for the current dungeon");
        char[][] mapGrid = mapGrid(rooms, currentRoom);

        logger.info("Displaying the generated map to the player");
        displayMap(mapGrid);

        logger.debug("Map displayed successfully");
    }

    /**
     * Generates the map grid for the dungeon, based on the player's visited rooms
     * Marks the current room, visited rooms, and unexplored rooms
     *
     * @param rooms       a 2D list of rooms in the dungeon.
     * @param currentRoom the room where the player is currently located.
     * @return a 2D character array representing the map grid.
     */
    private char[][] mapGrid(List<List<Room>> rooms, Room currentRoom) {
        logger.debug("Creating map grid for the dungeon");
        char[][] mapGrid = new char[rooms.size()][rooms.get(0).size()];

        for (int row = 0; row < rooms.size(); row++) {
            for (int col = 0; col < rooms.get(row).size(); col++) {
                Room room = rooms.get(row).get(col);
                if (room.equals(currentRoom)) {
                    mapGrid[row][col] = 'P';  // Mark player's current room
                    logger.debug("Marked player's current room at (" + row + ", " + col + ")");
                } else if (room.isEntrance() && room.isVisited()) {
                    mapGrid[row][col] = 'E';  // Mark entrance room if visited
                    logger.debug("Marked visited entrance room at (" + row + ", " + col + ")");
                } else if (room.isExit() && room.isVisited()) {
                    mapGrid[row][col] = 'X';  // Mark exit room if visited
                    logger.debug("Marked visited exit room at (" + row + ", " + col + ")");
                } else if (room.isVisited()) {
                    mapGrid[row][col] = '.';  // Mark visited rooms
                    logger.debug("Marked visited room at (" + row + ", " + col + ")");
                } else {
                    mapGrid[row][col] = '#';  // Mark unexplored rooms
                    logger.debug("Marked unexplored room at (" + row + ", " + col + ")");
                }
            }
        }

        logger.info("Map grid generated successfully");
        return mapGrid;
    }

    /**
     * Displays the generated map grid in a readable format
     * It shows the map with legends for the player, entrance, visited rooms, and unexplored areas
     *
     * @param mapGrid the 2D character array representing the map grid
     */
    private void displayMap(char[][] mapGrid) {
        logger.debug("Displaying map grid");
        UIManager.getInstance().displayMessage(GameNarrator.showMap(mapGrid));
        logger.info("Map grid displayed to the player");
    }
}