package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.IEscapeStrategy;

public class MadScientists implements ITrap {
    private String name;
    private String description;
    private boolean isActive;


    public MadScientists() {
        this.name = "mad scientists";
        this.description = "Mad Scientists are talking constantly about their experiments. They are very annoying.";
        this.isActive = true;
    }

    @Override
    public void activate(GameContext gameContext) {
        if (!isActive) {
            System.out.println("* You see the mad scientists, but they are not talking about their experiments anymore.");
            return;
        }

        System.out.println("* " + getDescription());
        System.out.println("* Oh no! They have trapped you by talking about their experiments. You can't move.");
        System.out.println("* From rumors you heard, you know that you can escape by using ** freeze spell ** or ** giving them 10 points  **.");

        gameContext.getPlayer().setTrapped(true);
    }

    @Override
    public void escape(GameContext gameContext, IEscapeStrategy escapeStrategy) {
        System.out.println("escaped mad");
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
