package com.university.dungeon.room;

import com.university.config.Config;
import com.university.core.GameContext;
import com.university.elements.chests.Chest;
import com.university.elements.chests.IChest;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.TrapFactory;
import com.university.elements.items.ItemFactory;
import com.university.utils.commands.Direction;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.util.*;

/**
 * Factory class responsible for creating rooms and setting adjacent rooms in a dungeon.
 * It also handles the random generation of items, traps, and chests within the rooms.
 */
public class RoomFactory {

    private static final ILogger logger = LoggerFactory.getLogger(RoomFactory.class);
    private static final Random random = new Random();
    private static final Config.SpawnRates spawnRates = GameContext.initialize().getDifficulty().getSpawnRates();

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
        logger.debug("Creating room from cell type: " + cell + ", name: " + name);
        switch (cell.toUpperCase()) {
            case "W": {
                logger.debug("Created Wall room: " + name);
                return new Room(name, false, false, false, true, null, null);
            }
            case "E": {
                logger.debug("Created Entrance room: " + name);
                return new Room(name, true, false, false, false, null, null);
            }
            case "X": {
                logger.debug("Created Exit room: " + name);
                return new Room(name, false, true, false, false, null, null);
            }
            case "T": {
                logger.debug("Created Treasure room: " + name);
                return new Room(name, false, false, true, false, null, null);
            }
            case "R": {
                Room room = new Room(name, false, false, false, false, createTrap(), createChest());
                logger.debug("Created Regular room: " + name);
                addItemsToRoom(room);
                return room;
            }
            default: {
                logger.warning("Unknown cell type: " + cell + ". Defaulting to Wall room.");
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
        logger.debug("Trap creation probability: " + probability);
        if (probability < spawnRates.trapProbability) {
            ITrap trap = trapFactory.createRandomTrap();
            logger.debug("Created trap: " + trap);
            return trap;
        }
        logger.debug("No trap created.");
        return null;
    }

    /**
     * Creates a chest for a room based on a set probability.
     *
     * @return A Chest object or null if no chest is generated.
     */
    private static IChest createChest() {
        double probability = random.nextDouble();
        logger.debug("Chest creation probability: " + probability);
        if (probability < spawnRates.chestProbability) {
            IChest chest = new Chest();
            logger.debug("Created chest: " + chest);
            return chest;
        }
        logger.debug("No chest created.");
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
        logger.debug("Adding " + numberOfItems + " item(s) to room: " + room.getLabel());
        for (int i = 0; i < numberOfItems; i++) {
            room.addItem(itemFactory.createRandomItem());
            logger.debug("Item added to room: " + room.getLabel());
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
        logger.debug("Item spawn probability: " + probability);
        if (probability < spawnRates.zeroItemProbability) {
            logger.debug("0 items will be spawned.");
            return 0;
        } else if (probability < spawnRates.zeroItemProbability + spawnRates.oneItemProbability) {
            logger.debug("1 item will be spawned.");
            return 1;
        } else {
            logger.debug("2 items will be spawned.");
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
        logger.debug("Setting adjacent rooms.");
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = 0; j < rooms.get(i).size(); j++) {
                Room currentRoom = rooms.get(i).get(j);
                HashMap<Direction, Room> adjacentRooms = new HashMap<>();

                if (currentRoom.isWall()) {
                    logger.debug("Room " + currentRoom.getLabel() + " is a wall. No adjacent rooms set.");
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

                logger.debug("Adjacent rooms for " + currentRoom.getLabel() + ": " + adjacentRooms);
                currentRoom.setAdjacentRooms(adjacentRooms);
            }
        }
    }
}