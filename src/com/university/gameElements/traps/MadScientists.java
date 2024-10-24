package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.IEscapeStrategy;

/**
 * Represents the "Mad Scientists" trap in the game.
 * The trap involves a group of scientists who trap the player by talking constantly about their experiments.
 * The player becomes stuck in the room until they use a specific strategy to escape.
 */
public class MadScientists implements ITrap {
    private String name;
    private String description;
    private boolean isActive;

    /**
     * Constructs a new MadScientists trap.
     * The trap is initially active, and the description is set to describe the annoying scientists.
     */
    public MadScientists() {
        this.name = "mad scientists";
        this.description = "Mad Scientists are talking constantly about their experiments. They are very annoying.";
        this.isActive = true;
    }

    /**
     * Activates the Mad Scientists trap.
     * If the trap is still active, the player becomes trapped and cannot move.
     *
     * @param gameContext The current game context, including player and environment details.
     */
    @Override
    public void activate(GameContext gameContext) {
        printDescriptionByState();
        if (!isActive) {
            return;
        }
        gameContext.getPlayer().setTrapped(true);
    }

    /**
     * Allows the player to escape the trap using a provided strategy.
     * After successfully escaping, the trap becomes inactive, and the player is no longer trapped.
     *
     * @param gameContext The current game context, including player and environment details.
     * @param escapeStrategy The strategy used by the player to escape the trap.
     */
    @Override
    public void escape(GameContext gameContext, IEscapeStrategy escapeStrategy) {
        escapeStrategy.escape(gameContext);
        gameContext.getPlayer().setTrapped(false);
        isActive = false;
    }

    /**
     * Returns the name of the trap.
     *
     * @return The name "mad scientists".
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the trap.
     *
     * @return A string describing the annoying scientists.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether the trap is currently active.
     *
     * @return true if the trap is active, false otherwise.
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * Prints the description of the trap based on whether it is active or inactive.
     * If the trap is active, it describes how the scientists trap the player.
     * If the trap is inactive, it describes the scientists as no longer talking about their experiments.
     */
    @Override
    public void printDescriptionByState() {
        if (!isActive) {
            System.out.println("* You see the mad scientists, but they are not talking about their experiments anymore.");
            return;
        }

        System.out.println("* " + getDescription());
        System.out.println("* Oh no! They have trapped you by talking about their experiments. You can't move.");
        System.out.println("* From rumors you heard, you know that you can escape by using ** freeze spell ** or ** giving them 10 points  **.");
    }
}