package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.MadScientists;
import com.university.gameElements.traps.Trap;
import com.university.gameElements.traps.strategies.FreezeSpellStrategy;

public class FreezeSpellEffect implements IEffect{

    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        System.out.println(currentRoom.getTrap());
        if (currentRoom.getTrap() instanceof MadScientists || currentRoom.getTrap() instanceof Trap) {
            currentRoom.getTrap().escape(gameContext, new FreezeSpellStrategy());
        }
    }

    @Override
    public void remove(GameContext gameContext) {

    }
}
