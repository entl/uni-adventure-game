package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.dungeon.room.Room;
import com.university.player.Player;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code MoveCommand} class implements the {@code ICommand} interface and
 * is responsible for moving the player in a specified direction.
 * This command is triggered when the player decides to move to a different room.
 */
public class MoveCommand implements ICommand {

    private Direction direction;

    /**
     * Constructs a {@code MoveCommand} with the specified direction.
     *
     * @param direction the direction in which the player should move (e.g., NORTH, SOUTH, EAST, WEST)
     */
    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    /**
     * Executes the move command, which moves the player to the room in the specified direction,
     * if such a room exists. The movement may trigger events such as encountering a trap.
     *
     * @param context the current game context which contains all necessary information about the player,
     *                the dungeon, and the game state.
     */
    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        if (canMove(player)) {
            player.move(direction);
            UIManager.getInstance().displayMessage(GameNarrator.movementSuccess(direction));
            UIManager.getInstance().displayMessage("");
        }
    }

    private boolean canMove(Player player) {

        if (player.isTrapped()) {
            UIManager.getInstance().displayMessage(GameNarrator.trapActivated());
            return false;
        }

        if (player.isAsleep()) {
            UIManager.getInstance().displayMessage(GameNarrator.sleepEffect());
            return false;
        }

        Room currentRoom = player.getCurrentRoom();
        Room nextRoom = currentRoom.getRoomInDirection(direction);

        if (nextRoom == null) {
            UIManager.getInstance().displayMessage(GameNarrator.movementFail(direction));
            return false;
        }

        return true;
    }
}