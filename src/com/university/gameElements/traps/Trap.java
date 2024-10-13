package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.IEscapeStrategy;

public class Trap implements ITrap {
    private String name;
    private String description;
    private boolean isActive;


    public Trap() {
        this.name = "trap";
        this.description = "This is a trap.";
        this.isActive = true;
    }

    @Override
    public void activate(GameContext gameContext) {
        if (!isActive) {
            System.out.println("* You see the trap, but it is not active anymore.");
            return;
        }

        System.out.println("* " + getDescription());
        System.out.println("* Oh no! You have been trapped.");
        System.out.println("* You can escape by using ** freeze spell **, ** hammer ** or ** giving 5 points  **.");

        gameContext.getPlayer().setTrapped(true);
    }

    @Override
    public void escape(GameContext gameContext, IEscapeStrategy escapeStrategy) {
        escapeStrategy.escape();
        gameContext.getPlayer().setTrapped(false);
        isActive = false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
