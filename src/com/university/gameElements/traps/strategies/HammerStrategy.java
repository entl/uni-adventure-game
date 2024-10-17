package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;

public class HammerStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        System.out.println("* You used hammer to escape the trap!");
    }
}
