package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.IEscapeStrategy;

/**
 * Represents a special trap: CutieCat, which immobilizes the player until it is fed.
 * If the player chooses not to feed the cat, they lose 5 power points to escape.
 */
public class CutieCat implements ITrap {
    private String name;
    private String description;
    private boolean isActive;

    /**
     * Constructs a new CutieCat trap.
     * The trap is initially active, and the description informs the player that the cat must be fed.
     */
    public CutieCat() {
        this.name = "cutiecat";
        this.description = "A hungry cute cat blocks your way, demanding food!";
        this.isActive = true;
    }

    /**
     * Activates the CutieCat trap, immobilizing the player until they feed the cat or take the penalty.
     *
     * @param gameContext The current game context, including player and environment details.
     */
    @Override
    public void activate(GameContext gameContext) {
        if (!isActive) {
            return;
        }
        printDescription();
        gameContext.getPlayer().setTrapped(true);
    }

    /**
     * Allows the player to escape the CutieCat trap by either feeding the cat or losing power points.
     * After escaping, the trap becomes inactive, and the player is no longer trapped.
     *
     * @param gameContext    The current game context, including player and environment details.
     * @param escapeStrategy The strategy used by the player to escape the trap.
     */
    @Override
    public void escape(GameContext gameContext, IEscapeStrategy escapeStrategy) {
        escapeStrategy.escape(gameContext);
        gameContext.getPlayer().setTrapped(false);
        isActive = false;
        System.out.println("* The cat is happy and lets you pass!");
    }

    /**
     * Returns the name of the trap.
     *
     * @return The name "CutieCat".
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the trap.
     *
     * @return A string describing that the CutieCat must be fed or the player will lose points.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether the CutieCat trap is currently active.
     *
     * @return true if the trap is active, false otherwise.
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * Prints the description of the CutieCat trap.
     */
    @Override
    public void printDescription() {
        System.out.println("* " + getDescription());
        System.out.println("* You can escape by feeding the cutie cat or losing 15 power points.\n");
    }
}