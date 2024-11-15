package com.university.utils.commands;

import com.university.game.GameContext;

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
        context.getPlayer().move(direction);
        context.getPlayer().lookAround();
    }
}