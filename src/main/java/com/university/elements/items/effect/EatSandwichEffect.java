package com.university.elements.items.effect;

import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

/**
 * The {@code EatSandwichEffect} class represents the effect of eating a sandwich in the game.
 * When applied, the player gains 10 power points, and a message is displayed.
 */
public class EatSandwichEffect implements IEffect {
    private static final ILogger logger = LoggerFactory.getLogger(EatSandwichEffect.class);

    /**
     * Applies the effect of eating a sandwich, which increases the player's power points
     * by 10 and displays a message indicating the effect.
     *
     * @param gameContext the context of the game, which contains the player and other game states
     */
    @Override
    public void apply(GameContext gameContext) {
        logger.info("Applying EatSandwichEffect.");
        UIManager.getInstance().displayMessage("* You ate the sandwich. It was delicious! It granted you 10 power points.");
        gameContext.getPlayer().addPowerPoints(10);
        logger.info("Player gained 10 power points from eating a sandwich. Current power points: " + gameContext.getPlayer().getPowerPoints());
    }
}