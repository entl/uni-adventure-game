package com.university.utils.commands;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.ITrap;
import com.university.gameElements.traps.strategies.LosePointsStrategy;

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
            System.out.println("* There is no trap in this room!");
            return;
        }

        if (!trap.isActive()) {
            trap.printDescriptionByState();
            return;
        }

        switch (currentRoom.getTrap().getName()) {
            case "mad scientists":
                currentRoom.getTrap().escape(context, new LosePointsStrategy(10));
                break;
            case "trap":
                currentRoom.getTrap().escape(context, new LosePointsStrategy(5));
                break;
            default:
                System.out.println("* There is no trap in this room!");
        }
    }
}