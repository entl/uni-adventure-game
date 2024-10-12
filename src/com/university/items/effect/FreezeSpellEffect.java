package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;

public class FreezeSpellEffect implements IEffect{

    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getCurrentRoom();
    }

    @Override
    public void remove(GameContext gameContext) {

    }
}
