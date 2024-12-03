package com.university.elements.traps;

import com.university.core.GameContext;
import com.university.elements.traps.strategies.FreezeSpellStrategy;
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
 * Represents the "Mad Scientists" trap in the game
 * The trap involves a group of scientists who trap the player by talking constantly about their experiments
 * The player becomes stuck in the room until they use a specific strategy to escape
 */
public class MadScientists implements ITrap, IEventListener {
    private static final ILogger logger = LoggerFactory.getLogger(MadScientists.class);

    private String name;
    private String description;
    private String escapeDescription;
    private boolean isActive;

    /**
     * Constructs a new MadScientists trap
     * The trap is initially active, and the description is set to describe the annoying scientists
     */
    public MadScientists() {
        this.name = "mad scientists";
        this.description = "Mad Scientists are talking constantly about their experiments. They are very annoying.";
        this.escapeDescription = "You can escape by using a freeze spell or giving them 10 points.";
        this.isActive = true;
        logger.debug("MadScientists trap created with name: " + name + " and description: " + description);
        subscribeToEvents();
    }

    /**
     * Activates the Mad Scientists trap
     * If the trap is still active, the player becomes trapped and cannot move
     *
     * @param gameContext The current game context, including player and environment details
     */
    @Override
    public void activate(GameContext gameContext) {
        if (!isActive) {
            logger.info("Trap is already inactive, activation skipped.");
            return;
        }
        logger.info("Activating MadScientists trap for player in room: " + gameContext.getPlayer().getCurrentRoom().getLabel());
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
        logger.info("Player successfully escaped the MadScientists trap using " + escapeStrategy.getClass().getSimpleName());
    }

    /**
     * Returns the name of the trap
     *
     * @return The name "mad scientists"
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the trap
     *
     * @return A string describing the annoying scientists
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
     * @return A string describing the escape options for the MadScientists trap
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
        List<Class<? extends IEscapeStrategy>> escapeStrategies = List.of(FreezeSpellStrategy.class, LosePointsStrategy.class);
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
     * Subscribes the MadScientists trap to relevant events
     */
    private void subscribeToEvents() {
        logger.debug("Subscribing MadScientists trap to EscapeEvent");
        EventManager.getInstance().registerListener(EscapeEvent.class, this);
    }
}