package com.university.elements.traps.strategies;

import com.university.core.GameContext;
import com.university.utils.ui.UIManager;

public class FreezeSpellStrategy implements IEscapeStrategy {
    @Override
    public void escape(GameContext gameContext) {
        UIManager.getInstance().displayMessage("* You used freeze spell to escape the trap!");
    }
}
