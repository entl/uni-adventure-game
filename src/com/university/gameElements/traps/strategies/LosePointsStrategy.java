package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;
import com.university.utils.UI.GameNarrator;
import com.university.utils.UI.UIManager;

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
