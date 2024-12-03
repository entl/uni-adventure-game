package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.dungeon.room.Room;
import com.university.player.Player;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code MoveCommand} class implements the {@code ICommand} interface and
 * is responsible for moving the player in a specified direction
 * This command is triggered when the player decides to move to a different room
 */
public class MoveCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(MoveCommand.class);
    private Direction direction;

    /**
     * Constructs a {@code MoveCommand} with the specified direction
     *
     * @param direction the direction in which the player should move (e.g., NORTH, SOUTH, EAST, WEST)
     */
    public MoveCommand(Direction direction) {
        this.direction = direction;
        logger.debug("MoveCommand created with direction: " + direction);
    }

    /**
     * Executes the move command, which moves the player to the room in the specified direction,
     * if such a room exists. The movement may trigger events such as encountering a trap
     *
     * @param context the current game context which contains all necessary information about the player,
     *                the dungeon, and the game state
     */
    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        logger.debug("Executing MoveCommand for direction: " + direction + " by player: " + player);

        if (canMove(player)) {
            logger.info("Player " + player + " is moving in direction: " + direction);
            UIManager.getInstance().displayMessage(GameNarrator.movementSuccess(direction));
            player.move(direction);
            logger.info("Player " + player + " successfully moved to the next room in direction: " + direction);
        } else {
            logger.info("Player " + player + " could not move in direction: " + direction);
        }
    }

    private boolean canMove(Player player) {
        if (player.isTrapped()) {
            logger.warning("Player " + player + " is trapped and cannot move");
            UIManager.getInstance().displayMessage(GameNarrator.trapActivated());
            return false;
        }

        if (player.isAsleep()) {
            logger.warning("Player " + player + " is asleep and cannot move");
            UIManager.getInstance().displayMessage(GameNarrator.sleepEffect());
            return false;
        }

        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = currentRoom.getRoomInDirection(direction);

        if (nextRoom == null) {
            logger.warning("No room exists in direction: " + direction + " for player: " + player);
            UIManager.getInstance().displayMessage(GameNarrator.movementFail(direction));
            return false;
        }

        logger.debug("Player " + player + " can move in direction: " + direction);
        return true;
    }
}