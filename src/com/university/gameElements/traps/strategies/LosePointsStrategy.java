package com.university.gameElements.traps.strategies;

import com.university.game.GameContext;

public class LosePointsStrategy implements IEscapeStrategy {
    private final int points;

    public LosePointsStrategy(int points) {
        this.points = points;
    }

    @Override
    public void escape(GameContext gameContext) {
        System.out.println("* You catch your breath; it seems you've managed to escape...");
        System.out.println("* You lost " + points + " points!");
        gameContext.getPlayer().removePowerPoints(points);
    }
}
