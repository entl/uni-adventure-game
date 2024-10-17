package com.university.utils.commands;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.ITrap;
import com.university.gameElements.traps.strategies.LosePointsStrategy;

public class EscapeCommand implements ICommand {
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
