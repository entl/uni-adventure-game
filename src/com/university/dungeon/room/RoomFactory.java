package com.university.dungeon.room;

import com.university.Config;
import com.university.game.GameContext;
import com.university.gameElements.chests.Chest;
import com.university.gameElements.chests.IChest;
import com.university.gameElements.traps.ITrap;
import com.university.gameElements.traps.TrapFactory;
import com.university.items.ItemFactory;
import com.university.utils.commands.Direction;

import java.util.*;

/**
 * Factory class responsible for creating rooms and setting adjacent rooms in a dungeon.
 * It also handles the random generation of items, traps, and chests within the rooms.
 */
public class RoomFactory {

    private static final Random random = new Random();
    private static final Config.SpawnRates spawnRates = GameContext.getInstance().getDifficulty().getSpawnRates();

    private static final ItemFactory itemFactory = new ItemFactory();
    private static final TrapFactory trapFactory = new TrapFactory();

    /**
     * Creates a Room instance based on the cell type provided. The room will be initialized with
     * items, traps, or chests depending on the room type.
     *
     * @param cell The type of the cell (W, E, X, T, R).
     * @param name The label/name of the room.
     * @return A new Room object based on the cell type.
     */
    public static Room createRoomFromCell(String cell, String name) {
        switch (cell.toUpperCase()) {
            case "W": {
                // Wall
                return new Room(name, false, false, false, true, null, null);
            }
            case "E": {
                // Entrance
                return new Room(name, true, false, false, false, null, null);
            }
            case "X": {
                // Exit
                return new Room(name, false, true, false, false, null, null);
            }
            case "T": {
                // Treasure
                return new Room(name, false, false, true, false, null, null);
            }
            case "R": {
                // Regular Room with random items and traps
                Room room = new Room(name, false, false, false, false, createTrap(), createChest());
                addItemsToRoom(room);
                return room;
            }
            default: {
                // Handle unknown cell types as walls for safety
                return new Room(name, false, false, false, true, null, null);
            }
        }
    }

    /**
     * Creates a random trap for a room based on a set probability.
     *
     * @return A random ITrap object or null if no trap is generated.
     */
    private static ITrap createTrap() {
        double probability = random.nextDouble();
        if (probability < spawnRates.trapProbability) {
            return trapFactory.createRandomTrap();
        }
        return null;
    }

    /**
     * Creates a chest for a room based on a set probability.
     *
     * @return A Chest object or null if no chest is generated.
     */
    private static IChest createChest() {
        double probability = random.nextDouble();
        if (probability < spawnRates.chestProbability) {
            return new Chest();
        }
        return null;
    }

    /**
     * Adds a random number of items (0, 1, or 2) to a room.
     * The number of items is based on predefined probabilities.
     *
     * @param room The room where the items will be added.
     */
    private static void addItemsToRoom(Room room) {
        int numberOfItems = generateNumberOfItemsToSpawn();
        for (int i = 0; i < numberOfItems; i++) {
            room.addItem(itemFactory.createRandomItem());
        }
    }

    /**
     * Determines how many items (0, 1, or 2) should be spawned in a room based on
     * predefined probabilities.
     *
     * @return The number of items to spawn.
     */
    private static int generateNumberOfItemsToSpawn() {
        double probability = random.nextDouble();
        if (probability < spawnRates.zeroItemProbability) {
            return 0;
        } else if (probability < spawnRates.zeroItemProbability + spawnRates.oneItemProbability) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Sets adjacent rooms for each room in a 2D list of rooms.
     * This method connects rooms in all four directions (north, south, east, west).
     *
     * @param rooms A 2D list of Room objects representing the dungeon layout.
     */
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
