package com.university.elements.traps;

import com.university.core.GameContext;
import com.university.elements.traps.strategies.FreezeSpellStrategy;
import com.university.elements.traps.strategies.HammerStrategy;
import com.university.elements.traps.strategies.IEscapeStrategy;
import com.university.elements.traps.strategies.LosePointsStrategy;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;
import com.university.utils.events.EscapeEvent;
import com.university.utils.events.EventManager;
import com.university.utils.events.IEvent;
import com.university.utils.events.IEventListener;

import java.util.List;

/**
 * Represents a generic trap in the game that can immobilize the player
 * The trap can be escaped by using specific strategies or items
 */
public class Trap implements ITrap, IEventListener {
    private static final ILogger logger = LoggerFactory.getLogger(Trap.class);

    private String name;
    private String description;
    private String escapeDescription;
    private boolean isActive;

    /**
     * Constructs a new basic trap.
     * The trap is initially active, and the description is set to inform the player that they are immobilized.
     */
    public Trap() {
        this.name = "trap";
        this.description = "Your foot is caught in a trap. You can't move.";
        this.escapeDescription = "You can escape by using a freeze spell, hammer, or giving 5 points.";
        this.isActive = true;
        logger.debug("Trap created with name: " + name + " and description: " + description);
        subscribeToEvents();
    }

    /**
     * Activates the trap, immobilizing the player if the trap is still active
     *
     * @param gameContext The current game context, including player and environment details
     */
    @Override
    public void activate(GameContext gameContext) {
        if (!isActive) {
            logger.info("Trap is already inactive, activation skipped.");
            return;
        }
        logger.info("Activating trap for player in room: " + gameContext.getPlayer().getCurrentRoom().getLabel());
        gameContext.getPlayer().setTrapped(true);
    }

    /**
     * Allows the player to escape the trap using a provided strategy
     * After successfully escaping, the trap becomes inactive, and the player is no longer trapped
     *
     * @param gameContext The current game context, including player and environment details
     * @param escapeStrategy The strategy used by the player to escape the trap
     */
    @Override
    public void escape(GameContext gameContext, IEscapeStrategy escapeStrategy) {
        logger.info("Attempting escape using strategy: " + escapeStrategy.getClass().getSimpleName());
        escapeStrategy.escape(gameContext);
        gameContext.getPlayer().setTrapped(false);
        isActive = false;
        logger.info("Player successfully escaped the trap using " + escapeStrategy.getClass().getSimpleName());
    }

    /**
     * Returns the name of the trap
     *
     * @return The name "trap"
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the trap
     *
     * @return A string describing that the player is caught and unable to move
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether the trap is currently active
     *
     * @return true if the trap is active, false otherwise
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * Returns the description of the escape options for the trap
     *
     * @return A string describing the escape options for the trap
     */
    @Override
    public String getEscapeDescription() {
        return escapeDescription;
    }

    /**
     * Handles events related to the trap, such as escape attempts
     *
     * @param event The event being handled
     */
    @Override
    public void onEvent(IEvent event) {
        logger.debug("Received event: " + event.getClass().getSimpleName());
        List<Class<? extends IEscapeStrategy>> escapeStrategies = List.of(FreezeSpellStrategy.class, LosePointsStrategy.class, HammerStrategy.class);
        if(event instanceof EscapeEvent && isActive) {
            EscapeEvent escapeEvent = (EscapeEvent) event;
            if(escapeEvent.getTrap() == this){
                if (escapeStrategies.contains(escapeEvent.getEscapeStrategy().getClass())) {
                    escape(GameContext.initialize(), escapeEvent.getEscapeStrategy());
                } else {
                    logger.warning("Invalid escape strategy: " + escapeEvent.getEscapeStrategy().getClass().getSimpleName());
                    UIManager.getInstance().displayMessage(GameNarrator.trapEscapeFail());
                }
            }
        }
    }

    /**
     * Subscribes the trap to relevant events
     */
    private void subscribeToEvents() {
        logger.debug("Subscribing trap to EscapeEvent");
        EventManager.getInstance().registerListener(EscapeEvent.class, this);
    }
}