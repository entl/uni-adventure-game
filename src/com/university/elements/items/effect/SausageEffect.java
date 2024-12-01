package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.SausageStrategy;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;
import com.university.utils.events.EscapeEvent;

/**
 * The {@code SausageEffect} class represents an effect that allows the player to use a sausage to escape a trap.
 * If no trap is present, a message informs the player that the sausage is kept for later.
 */
public class SausageEffect implements IEffect {
    private static final ILogger logger = LoggerFactory.getLogger(SausageEffect.class);

    /**
     * Applies the sausage effect, allowing the player to escape a trap in the current room using the sausage strategy.
     * If no trap is present, a message indicates that the sausage is kept for later.
     *
     * @param gameContext the current game context, including the player and the current room state.
     */
    @Override
    public void apply(GameContext gameContext) {
        logger.info("Applying SausageEffect.");
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        logger.debug("Current room: " + currentRoom.getLabel());

        ITrap trap = currentRoom.getTrap();
        if (trap != null) {
            logger.info("Trap found in room: " + currentRoom.getLabel() + ". Dispatching EscapeEvent with SausageStrategy.");
            gameContext.getEventManager().dispatchEvent(new EscapeEvent(trap, new SausageStrategy()));
            logger.info("EscapeEvent dispatched successfully for trap: " + trap.getName());
        } else {
            logger.info("No trap found in room: " + currentRoom.getLabel());
            UIManager.getInstance().displayMessage("* You tried to eat sausage, but decided to keep it for later.");
        }
    }
}