package com.university.elements.traps.strategies;

import com.university.core.GameContext;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

public class LosePointsStrategy implements IEscapeStrategy {
    private final int points;

    public LosePointsStrategy(int points) {
        this.points = points;
    }

    @Override
    public void escape(GameContext gameContext) {
        UIManager.getInstance().displayMessage(GameNarrator.trapEscapeSuccess());
        UIManager.getInstance().displayMessage(GameNarrator.pointsLost(points));

        gameContext.getPlayer().removePowerPoints(points);
    }
}
