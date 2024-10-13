package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.Trap;
import com.university.gameElements.traps.strategies.HammerStrategy;

public class HammerEffect implements IEffect{
    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        if (currentRoom.getTrap() instanceof Trap) {
            currentRoom.getTrap().escape(gameContext, new HammerStrategy());
        }
    }

    @Override
    public void remove(GameContext gameContext) {

    }
}
