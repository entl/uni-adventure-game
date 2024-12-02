package com.university.elements.items.effect;

import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

/**
 * The {@code EatCakeEffect} class represents the effect of eating a cake in the game
 * When applied, the player gains 3 power points, and a message is displayed
 */
public class EatCakeEffect implements IEffect {
    private static final ILogger logger = LoggerFactory.getLogger(EatCakeEffect.class);

    /**
     * Applies the effect of eating a cake, which increases the player's power points
     * by 3 and displays a message indicating the effect
     *
     * @param gameContext the context of the game, which contains the player and other game states
     */
    @Override
    public void apply(GameContext gameContext) {
        logger.info("Applying EatCakeEffect.");
        UIManager.getInstance().displayMessage("* You ate the cake. It was delicious! It granted you 3 power points.");
        gameContext.getPlayer().addPowerPoints(3);
        logger.info("Player gained 3 power points from eating a cake. Current power points: " + gameContext.getPlayer().getPowerPoints());
    }
}