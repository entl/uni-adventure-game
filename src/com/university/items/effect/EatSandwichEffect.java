package com.university.items.effect;

import com.university.game.GameContext;
import com.university.utils.UI.UIManager;

/**
 * The {@code EatSandwichEffect} class represents the effect of eating a sandwich in the game.
 * When applied, the player gains 10 power points, and a message is displayed.
 */
public class EatSandwichEffect implements IEffect {

    /**
     * Applies the effect of eating a sandwich, which increases the player's power points
     * by 10 and displays a message indicating the effect.
     *
     * @param gameContext the context of the game, which contains the player and other game states
     */
    @Override
    public void apply(GameContext gameContext) {
        UIManager.getInstance().displayMessage("* You ate the sandwich. It was delicious! It granted you 10 power points.");
        gameContext.getPlayer().addPowerPoints(10);
    }
}