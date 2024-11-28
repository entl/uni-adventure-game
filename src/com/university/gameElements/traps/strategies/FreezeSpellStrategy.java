package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;
import com.university.utils.UI.UIManager;

public class FreezeSpellStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        UIManager.getInstance().displayMessage("* You used freeze spell to escape the trap!");
    }
}
