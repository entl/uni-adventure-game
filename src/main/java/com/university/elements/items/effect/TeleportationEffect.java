package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TeleportationEffect} class represents an effect that teleports the player to a random room within
 * the current dungeon, excluding walls and the current room. This effect is applied when the player uses a teleportation spell
 */
public class TeleportationEffect implements IEffect {
    private static final ILogger logger = LoggerFactory.getLogger(TeleportationEffect.class);

    /**
     * Applies the teleportation effect, which teleports the player to a random room in the current dungeon
     * The player is informed that they have used the teleportation spell and have been teleported
     *
     * @param gameContext the context of the game, including the player and the current dungeon
     */
    @Override
    public void apply(GameContext gameContext) {
        logger.info("Applying teleportation effect.");
        List<List<Room>> rooms = gameContext.getCurrentDungeon().getRooms();
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        List<Room> flattenedRooms = flattenRoomsAndRemoveWalls(rooms);

        // Get a random room that is not the current room
        Room destination = getRandomRoom(flattenedRooms);
        while (destination.equals(currentRoom)) {
            logger.debug("Selected room is the same as the current room. Re-selecting...");
            destination = getRandomRoom(flattenedRooms);
        }

        logger.info("Teleporting player from room: " + currentRoom.getLabel() + " to room: " + destination.getLabel());
        teleportPlayer(gameContext, destination);
        UIManager.getInstance().displayMessage("* You have used the teleportation spell");
        UIManager.getInstance().displayMessage("* You have been teleported to a random room");
    }

    /**
     * Teleports the player to the specified destination room
     *
     * @param gameContext the context of the game, including the player
     * @param destination the room to which the player will be teleported
     */
    private void teleportPlayer(GameContext gameContext, Room destination) {
        logger.debug("Setting player's current room to: " + destination.getLabel());
        gameContext.getPlayer().setCurrentRoom(destination);
    }

    /**
     * Flattens the 2D list of rooms into a single list and removes wall rooms from the list
     *
     * @param rooms the 2D list of rooms in the dungeon
     * @return a list of non-wall rooms in the dungeon
     */
    private List<Room> flattenRoomsAndRemoveWalls(List<List<Room>> rooms) {
        logger.debug("Flattening rooms and removing walls.");
        List<Room> flattenedRooms = new ArrayList<>();
        for (List<Room> roomList : rooms) {
            for (Room room : roomList) {
                if (!room.isWall()) {
                    flattenedRooms.add(room);
                }
            }
        }
        logger.info("Total non-wall rooms available: " + flattenedRooms.size());
        return flattenedRooms;
    }

    /**
     * Selects a random room from a list of rooms
     *
     * @param rooms the list of rooms to choose from
     * @return a randomly selected room from the list
     */
    private Room getRandomRoom(List<Room> rooms) {
        int randomIndex = (int) (Math.random() * rooms.size());
        Room randomRoom = rooms.get(randomIndex);
        logger.debug("Randomly selected room: " + randomRoom.getLabel());
        return randomRoom;
    }
}