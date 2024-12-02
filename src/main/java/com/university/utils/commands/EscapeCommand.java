package com.university.utils.commands;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.LosePointsStrategy;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;
import com.university.utils.events.EscapeEvent;
import com.university.utils.events.EventManager;

/**
 * The {@code EscapeCommand} class implements the {@code ICommand} interface to escape from a trap in the current room.
 * It is part of the command pattern used to handle player actions in the game.
 */
public class EscapeCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(EscapeCommand.class);
    /**
     * Executes the escape action. If the player is in a room with an active trap, the player can attempt
     * to escape using the appropriate strategy. The escape cost is determined by the type of trap:
     * <ul>
     *     <li>Mad Scientists: The player loses 10 points.</li>
     *     <li>Trap: The player loses 5 points.</li>
     *     <li>Cutiecat: The player loses 15 points.</li>
     * </ul>
     * If there is no active trap or no trap in the room, the player is informed.
     *
     * @param context the current game context, which includes the player's current room and the trap in the room
     */
    @Override
    public void execute(GameContext context) {
        logger.debug("Executing EscapeCommand");
        Room currentRoom = context.getPlayer().getCurrentRoom();
        ITrap trap = currentRoom.getTrap();

        if (trap == null) {
            logger.info("No trap found in the current room: " + currentRoom.getLabel());
            UIManager.getInstance().displayMessage(GameNarrator.trapNotFound());
            return;
        }

        if (!trap.isActive()) {
            logger.info("Trap in room " + currentRoom.getLabel() + " is not active");
            trap.printDescription();
            return;
        }

        String trapName = trap.getName();
        logger.info("Player encountered an active trap: " + trapName + " in room: " + currentRoom.getLabel());

        switch (trapName) {
            case "mad scientists":
                logger.info("Dispatching escape event for trap: " + trapName + " with a penalty of 10 points");
                EventManager.getInstance().dispatchEvent(new EscapeEvent(trap, new LosePointsStrategy(10)));
                break;
            case "trap":
                logger.info("Dispatching escape event for trap: " + trapName + " with a penalty of 5 points");
                EventManager.getInstance().dispatchEvent(new EscapeEvent(trap, new LosePointsStrategy(5)));
                break;
            case "cutiecat":
                logger.info("Dispatching escape event for trap: " + trapName + " with a penalty of 15 points");
                EventManager.getInstance().dispatchEvent(new EscapeEvent(trap, new LosePointsStrategy(15)));
                break;
            default:
                logger.warning("Unknown trap encountered: " + trapName);
                UIManager.getInstance().displayMessage(GameNarrator.trapNotFound());
        }
    }
}