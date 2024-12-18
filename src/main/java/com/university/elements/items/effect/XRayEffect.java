package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.player.Player;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;
import com.university.utils.commands.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * The {@code XRayEffect} class represents an effect that allows the player to see into a random adjacent room
 * without entering it. This effect is typically applied when the player uses an X-Ray vision potion or spell
 */
public class XRayEffect implements IEffect {
    private static final ILogger logger = LoggerFactory.getLogger(XRayEffect.class);

    /**
     * Applies the X-Ray vision effect, allowing the player to look into a random adjacent room and see its contents
     * The player is notified about the direction they are looking into, and if there is a trap in that room,
     * the player is informed about the trap
     *
     * @param gameContext the current game context, including the player and the dungeon state
     */
    @Override
    public void apply(GameContext gameContext) {
        logger.info("Applying X-Ray vision effect");
        Player player = gameContext.getPlayer();
        HashMap<Direction, Room> adjacentRooms = player.getCurrentRoom().getAdjacentRooms();

        Random random = new Random();
        List<Direction> directionsFromCurrentRoom = List.copyOf(adjacentRooms.keySet());
        Direction randomDirection = directionsFromCurrentRoom.get(random.nextInt(directionsFromCurrentRoom.size()));
        Room randomRoom = adjacentRooms.get(randomDirection);

        logger.debug("Selected random direction: " + randomDirection);
        logger.debug("Room details: " + randomRoom.getLabel());

        UIManager.getInstance().displayMessage("* You have used X-Ray vision and can look around the next room.");
        UIManager.getInstance().displayMessage("* In the " + randomDirection + " direction you see:");
        randomRoom.describe();

        ITrap trap = randomRoom.getTrap();
        if (trap != null) {
            logger.info("Trap found in the next room: " + trap.getName());
            UIManager.getInstance().displayMessage(String.format("* You have found a %s in the next room!\n", trap.getName()));
        } else {
            logger.info("No traps found in the next room");
        }
    }
}