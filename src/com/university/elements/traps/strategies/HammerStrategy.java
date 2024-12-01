package com.university.elements.traps.strategies;

import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

/**
 * The {@code HammerStrategy} class represents a strategy for escaping traps
 * by using a hammer
 */
public class HammerStrategy implements IEscapeStrategy {
    private static final ILogger logger = LoggerFactory.getLogger(HammerStrategy.class);

    /**
     * Executes the hammer strategy, allowing the player to escape a trap
     *
     * @param gameContext the context of the game, including the player and the current game state
     */
    @Override
    public void escape(GameContext gameContext) {
        logger.info("Executing HammerStrategy to escape trap.");
        UIManager.getInstance().displayMessage("* You used hammer to escape the trap!");
        logger.info("Player successfully escaped the trap using HammerStrategy.");
    }
}