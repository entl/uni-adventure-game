package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;

public class FreezeSpellStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        System.out.println("* You used freeze spell to escape the trap!");
    }
}
