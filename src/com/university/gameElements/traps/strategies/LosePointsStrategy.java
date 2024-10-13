package com.university.gameElements.traps.strategies;

public class LosePointsStrategy implements IEscapeStrategy {
    private final int points;

    public LosePointsStrategy(int points) {
        this.points = points;
    }

    @Override
    public void escape() {
        System.out.println("* You lost " + points + " points!");
    }
}
