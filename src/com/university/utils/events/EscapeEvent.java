package com.university.utils.events;

import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.IEscapeStrategy;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

/**
 * Represents an event triggered when a player attempts to escape from a trap
 * This event contains the trap involved and the strategy used for escaping
 */
public class EscapeEvent implements IEvent {
    private static final ILogger logger = LoggerFactory.getLogger(EscapeEvent.class);

    private final ITrap trap;
    private final IEscapeStrategy escapeStrategy;

    /**
     * Constructs a new EscapeEvent
     *
     * @param trap           The trap that the player is trying to escape from
     * @param escapeStrategy The strategy used by the player to attempt the escape
     */
    public EscapeEvent(ITrap trap, IEscapeStrategy escapeStrategy) {
        this.trap = trap;
        this.escapeStrategy = escapeStrategy;
        logger.info("EscapeEvent created with trap: " + trap.getName() + " and strategy: " + escapeStrategy.getClass().getSimpleName());
    }

    /**
     * Gets the trap involved in the escape event
     *
     * @return The trap
     */
    public ITrap getTrap() {
        logger.debug("Getting trap: " + trap.getName());
        return trap;
    }

    /**
     * Gets the escape strategy used in the escape event
     *
     * @return The escape strategy
     */
    public IEscapeStrategy getEscapeStrategy() {
        logger.debug("Getting escape strategy: " + escapeStrategy.getClass().getSimpleName());
        return escapeStrategy;
    }
}