package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.CutieCat;
import com.university.gameElements.traps.strategies.SausageStrategy;

public class SausageEffect implements IEffect{
    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        if (currentRoom.getTrap() instanceof CutieCat) {
            currentRoom.getTrap().escape(gameContext, new SausageStrategy());
        } else {
            System.out.println("* You tried to eat sausage, but decided to keep it for later.");
        }
    }
}
