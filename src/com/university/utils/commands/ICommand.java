package com.university.utils.commands;

import com.university.game.GameContext;

/**
 * The {@code ICommand} interface defines the structure for all command classes in the game.
 * Each command that implements this interface must define the behavior of the {@code execute} method,
 * which allows the command to be performed based on the game's current context.
 */
public interface ICommand {

    /**
     * Executes the command using the provided {@code GameContext}.
     * Each command implementation determines the specific action it performs when executed.
     *
     * @param context the current game context which contains all necessary information about the player,
     *                dungeon, and game state
     */
    void execute(GameContext context);
}