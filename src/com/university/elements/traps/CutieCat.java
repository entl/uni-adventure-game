package com.university.elements.traps;

import com.university.core.GameContext;
import com.university.elements.traps.strategies.IEscapeStrategy;
import com.university.elements.traps.strategies.LosePointsStrategy;
import com.university.elements.traps.strategies.SausageStrategy;
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
 * Represents a special trap: CutieCat, which immobilizes the player until it is fed.
 * If the player chooses not to feed the cat, they lose 5 power points to escape.
 */
public class CutieCat implements ITrap, IEventListener {
    private static final ILogger logger = LoggerFactory.getLogger(CutieCat.class);

    private String name;
    private String description;
    private boolean isActive;

    /**
     * Constructs a new CutieCat trap
     * The trap is initially active, and the description informs the player that the cat must be fed
     */
    public CutieCat() {
        this.name = "cutiecat";
        this.description = "A hungry cute cat blocks your way, demanding food!";
        this.isActive = true;
        logger.debug("CutieCat trap created with name: " + name + " and description: " + description);
        subscribeToEvents();
    }

    /**
     * Activates the CutieCat trap, immobilizing the player until they feed the cat or take the penalty
     *
     * @param gameContext The current game context, including player and environment details
     */
    @Override
    public void activate(GameContext gameContext) {
        if (!isActive) {
            logger.info("CutieCat trap is already inactive. Activation skipped.");
            return;
        }
        logger.info("Activating CutieCat trap for player in room: " + gameContext.getPlayer().getCurrentRoom().getLabel());
        gameContext.getPlayer().setTrapped(true);
    }

    /**
     * Allows the player to escape the CutieCat trap by either feeding the cat or losing power points
     * After escaping, the trap becomes inactive, and the player is no longer trapped
     *
     * @param gameContext    The current game context, including player and environment details
     * @param escapeStrategy The strategy used by the player to escape the trap
     */
    @Override
    public void escape(GameContext gameContext, IEscapeStrategy escapeStrategy) {
        logger.info("Attempting escape from CutieCat trap using strategy: " + escapeStrategy.getClass().getSimpleName());
        escapeStrategy.escape(gameContext);
        gameContext.getPlayer().setTrapped(false);
        isActive = false;
        logger.info("Player successfully escaped the CutieCat trap using " + escapeStrategy.getClass().getSimpleName());
    }

    /**
     * Returns the name of the trap
     *
     * @return The name "CutieCat"
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the trap
     *
     * @return A string describing that the CutieCat must be fed or the player will lose points
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Checks whether the CutieCat trap is currently active
     *
     * @return true if the trap is active, false otherwise
     */
    @Override
    public boolean isActive() {
        return isActive;
    }

    /**
     * Prints the description of the CutieCat trap
     */
    @Override
    public void printDescription() {
        logger.debug("Printing CutieCat trap description. Is active: " + isActive);
        UIManager.getInstance().displayMessage("* " + getDescription());
        UIManager.getInstance().displayMessage("* You can escape by feeding the cutie cat or losing 15 power points.\n");
    }

    /**
     * Handles events related to the trap, such as escape attempts
     *
     * @param event The event being handled
     */
    @Override
    public void onEvent(IEvent event) {
        logger.debug("Received event: " + event.getClass().getSimpleName());
        List<Class<? extends IEscapeStrategy>> escapeStrategies = List.of(SausageStrategy.class, LosePointsStrategy.class);
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
     * Subscribes the CutieCat trap to relevant events
     */
    private void subscribeToEvents() {
        logger.debug("Subscribing CutieCat trap to EscapeEvent");
        EventManager.getInstance().registerListener(EscapeEvent.class, this);
    }
}