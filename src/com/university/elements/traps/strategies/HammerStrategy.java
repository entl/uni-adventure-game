package com.university.elements.traps.strategies;

import com.university.core.GameContext;
import com.university.utils.ui.UIManager;

public class HammerStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        UIManager.getInstance().displayMessage("* You used hammer to escape the trap!");
    }
}
