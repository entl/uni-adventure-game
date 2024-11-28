package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;
import com.university.utils.UI.UIManager;

public class HammerStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        UIManager.getInstance().displayMessage("* You used hammer to escape the trap!");
    }
}
