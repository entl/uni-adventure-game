package com.university.items.effect;

import com.university.game.GameContext;

public class EatCakeEffect implements IEffect {
    @Override
    public void apply(GameContext gameContext) {
        System.out.println("* You ate the cake. It was delicious!. It granted you 3 power points.");
        gameContext.getPlayer().addPowerPoints(3);
    }

    @Override
    public void remove(GameContext gameContext) {
        System.out.println("* Cake can't harm you.");
    }
}
