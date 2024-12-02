package com.university.elements.traps.strategies;

import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

/**
 * The {@code FreezeSpellStrategy} class represents a strategy for escaping traps
 * by using a freeze spell
 */
public class FreezeSpellStrategy implements IEscapeStrategy {
    private static final ILogger logger = LoggerFactory.getLogger(FreezeSpellStrategy.class);

    /**
     * Executes the freeze spell strategy, allowing the player to escape a trap
     *
     * @param gameContext the context of the game, including the player and the current game state
     */
    @Override
    public void escape(GameContext gameContext) {
        logger.info("Executing FreezeSpellStrategy to escape trap.");
        UIManager.getInstance().displayMessage("* You used freeze spell to escape the trap!");
        logger.info("Player successfully escaped the trap using FreezeSpellStrategy.");
    }
}