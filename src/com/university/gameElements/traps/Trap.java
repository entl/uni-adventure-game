package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.IEscapeStrategy;

/**
 * Represents a generic trap in the game that can immobilize the player.
 * The trap can be escaped by using specific strategies or items.
 */
public class Trap implements ITrap {
    private String name;
    private String description;
    private boolean isActive;

    /**
     * Constructs a new basic trap.
     * The trap is initially active, and the description is set to inform the player that they are immobilized.
     */
    public Trap() {
        this.name = "trap";
        this.description = "Your foot is caught in a trap. You can't move.";
        this.isActive = true;
    }

    /**
     * Activates the trap, immobilizing the player if the trap is still active.
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
     * @return The name "trap".
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the trap.
     *
     * @return A string describing that the player is caught and unable to move.
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
     * If the trap is active, it describes how the player is immobilized.
     * If the trap is inactive, it informs the player that the trap is no longer a threat.
     */
    @Override
    public void printDescriptionByState() {
        if (!isActive) {
            System.out.println("* You see the trap, but it is not active anymore.");
            return;
        }

        System.out.println("* " + getDescription());
        System.out.println("* You can escape by using ** freeze spell **, ** hammer ** or ** giving 5 points  **.");
    }
}