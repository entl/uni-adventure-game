package com.university.elements.traps.strategies;

import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code LosePointsStrategy} class represents a strategy for escaping traps
 * by losing a specified number of power points
 */
public class LosePointsStrategy implements IEscapeStrategy {
    private static final ILogger logger = LoggerFactory.getLogger(LosePointsStrategy.class);
    private final int points;

    /**
     * Constructs a new {@code LosePointsStrategy} with the specified number of points to lose
     *
     * @param points the number of points the player will lose to escape the trap
     */
    public LosePointsStrategy(int points) {
        this.points = points;
    }

    /**
     * Executes the escape strategy by reducing the player's power points and displaying success messages
     *
     * @param gameContext the context of the game, including the player and the current game state
     */
    @Override
    public void escape(GameContext gameContext) {
        logger.info("Executing LosePointsStrategy with point deduction: " + points);

        UIManager.getInstance().displayMessage(GameNarrator.trapEscapeSuccess());
        UIManager.getInstance().displayMessage(GameNarrator.pointsLost(points));

        gameContext.getPlayer().removePowerPoints(points);
        logger.info("Player lost " + points + " power points. Current power points: " + gameContext.getPlayer().getPowerPoints());
    }
}