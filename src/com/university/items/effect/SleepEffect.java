package com.university.items.effect;

import com.university.game.GameContext;

public class SleepEffect implements IEffect{
    @Override
    public void apply(GameContext gameContext) {
        System.out.println("* You feel drowsy and fall asleep.");
        gameContext.getPlayer().setAsleep(true);
    }
}
