package com.university.utils.commands;

import com.university.core.GameContext;

/**
 * The {@code LookAroundCommand} class implements the {@code ICommand} interface
 * and allows the player to "look around" in the current room.
 * This command provides details about the items, traps, and adjacent rooms.
 */
public class LookAroundCommand implements ICommand {

    /**
     * Executes the "look around" command, which makes the player examine their
     * surroundings. This includes details about the current room, items present,
     * and possible directions to move.
     *
     * @param context the current game context which contains all necessary information about the player,
     *                the room, and the game state.
     */
    @Override
    public void execute(GameContext context) {
        context.getPlayer().lookAround();
    }
}