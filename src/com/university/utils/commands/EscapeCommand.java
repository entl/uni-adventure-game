package com.university.utils.commands;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.LosePointsStrategy;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;
import com.university.utils.events.EscapeEvent;
import com.university.utils.events.EventManager;

/**
 * The {@code EscapeCommand} class implements the {@code ICommand} interface to escape from a trap in the current room.
 * It is part of the command pattern used to handle player actions in the game.
 */
public class EscapeCommand implements ICommand {

    /**
     * Executes the escape action. If the player is in a room with an active trap, the player can attempt
     * to escape using the appropriate strategy. The escape cost is determined by the type of trap:
     * <ul>
     *     <li>Mad Scientists: The player loses 10 points.</li>
     *     <li>Trap: The player loses 5 points.</li>
     * </ul>
     * If there is no active trap or no trap in the room, the player is informed.
     *
     * @param context the current game context, which includes the player's current room and the trap in the room
     */
    @Override
    public void execute(GameContext context) {
        Room currentRoom = context.getPlayer().getCurrentRoom();
        ITrap trap = currentRoom.getTrap();

        if (trap == null) {
            UIManager.getInstance().displayMessage(GameNarrator.trapNotFound());
            return;
        }

        if (!trap.isActive()) {
            trap.printDescription();
            return;
        }

        switch (currentRoom.getTrap().getName()) {
            case "mad scientists":
                EventManager.getInstance().dispatchEvent(new EscapeEvent(currentRoom.getTrap(), new LosePointsStrategy(10)));
                break;
            case "trap":
                EventManager.getInstance().dispatchEvent(new EscapeEvent(currentRoom.getTrap(), new LosePointsStrategy(5)));
                break;
            case "cutiecat":
                EventManager.getInstance().dispatchEvent(new EscapeEvent(currentRoom.getTrap(), new LosePointsStrategy(15)));
                break;
            default:
                UIManager.getInstance().displayMessage(GameNarrator.trapNotFound());
        }
    }
}