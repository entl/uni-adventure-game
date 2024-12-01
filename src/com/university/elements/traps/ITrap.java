package com.university.elements.traps;

import com.university.core.GameContext;
import com.university.elements.traps.strategies.IEscapeStrategy;

/**
 * Interface for a trap in the game.
 * A trap can be activated and can be escaped from using specific strategies.
 * Each trap has a name, description, and can be in an active or inactive state.
 */
public interface ITrap {

    /**
     * Activates the trap in the context of the game.
     * This method defines what happens when the player encounters the trap.
     *
     * @param gameContext The current game context, providing information about the player and the environment.
     */
    void activate(GameContext gameContext);

    /**
     * Allows the player to escape from the trap using a specific strategy.
     * The strategy defines how the player can escape, which could involve losing power points or using an item.
     *
     * @param gameContext The current game context, providing information about the player and the environment.
     * @param escapeStrategy The strategy used to escape from the trap.
     */
    void escape(GameContext gameContext, IEscapeStrategy escapeStrategy);

    /**
     * Returns the name of the trap.
     *
     * @return The name of the trap.
     */
    String getName();

    /**
     * Returns the description of the trap.
     *
     * @return A description of the trap.
     */
    String getDescription();

    /**
     * Checks if the trap is currently active.
     *
     * @return true if the trap is active, false otherwise.
     */
    boolean isActive();

    /**
     * Prints the description of the trap based on its current state (active or inactive).
     * The method should display different descriptions depending on whether the trap is active or not.
     */
    void printDescription();
}