package com.university.items.effect;

import com.university.game.GameContext;

public class EatSandwichEffect implements IEffect {
    @Override
    public void apply(GameContext gameContext) {
        System.out.println("* You ate the sandwich. It was delicious! It granted you 10 power points.");
        gameContext.getPlayer().addPowerPoints(10);
    }
}
