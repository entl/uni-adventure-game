package com.university.utils.events;

import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.IEscapeStrategy;

/**
 * Represents an event triggered when a player attempts to escape from a trap.
 * This event contains the trap involved and the strategy used for escaping.
 */
public class EscapeEvent implements IEvent {
    private final ITrap trap;
    private final IEscapeStrategy escapeStrategy;

    /**
     * Constructs a new EscapeEvent.
     *
     * @param trap           The trap that the player is trying to escape from.
     * @param escapeStrategy The strategy used by the player to attempt the escape.
     */
    public EscapeEvent(ITrap trap, IEscapeStrategy escapeStrategy) {
        this.trap = trap;
        this.escapeStrategy = escapeStrategy;
    }

    /**
     * Gets the trap involved in the escape event.
     *
     * @return The trap.
     */
    public ITrap getTrap() {
        return trap;
    }

    /**
     * Gets the escape strategy used in the escape event.
     *
     * @return The escape strategy.
     */
    public IEscapeStrategy getEscapeStrategy() {
        return escapeStrategy;
    }
}