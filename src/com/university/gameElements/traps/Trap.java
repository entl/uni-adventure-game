package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.FreezeSpellStrategy;
import com.university.gameElements.traps.strategies.HammerStrategy;
import com.university.gameElements.traps.strategies.IEscapeStrategy;
import com.university.gameElements.traps.strategies.LosePointsStrategy;
import com.university.utils.events.EscapeEvent;
import com.university.utils.events.EventManager;
import com.university.utils.events.IEvent;
import com.university.utils.events.IEventListener;

import java.util.List;

/**
 * Represents a generic trap in the game that can immobilize the player.
 * The trap can be escaped by using specific strategies or items.
 */
public class Trap implements ITrap, IEventListener {
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
        subscribeToEvents();
    }

    /**
     * Activates the trap, immobilizing the player if the trap is still active.
     *
     * @param gameContext The current game context, including player and environment details.
     */
    @Override
    public void activate(GameContext gameContext) {
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
    public void printDescription() {
        System.out.println("* " + getDescription());
        System.out.println("* You can escape by using ** freeze spell **, ** hammer ** or ** giving 5 points  **.\n");
    }

    @Override
    public void onEvent(IEvent event) {
        List<Class<? extends IEscapeStrategy>> escapeStrategies = List.of(FreezeSpellStrategy.class, LosePointsStrategy.class, HammerStrategy.class);
        if(event instanceof EscapeEvent && isActive) {
            EscapeEvent escapeEvent = (EscapeEvent) event;
            if(escapeEvent.getTrap() == this){
                if (escapeStrategies.contains(escapeEvent.getEscapeStrategy().getClass())) {
                    escape(GameContext.initialize(), escapeEvent.getEscapeStrategy());
                } else {
                    System.out.println("* You tried to escape, but the strategy didn't work.\n");
                }
            }
        }
    }

    private void subscribeToEvents() {
        EventManager.getInstance().registerListener(EscapeEvent.class, this);
    }
}