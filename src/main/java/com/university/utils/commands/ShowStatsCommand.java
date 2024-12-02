package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.player.Player;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code ShowStatsCommand} class implements the {@code ICommand} interface
 * and is responsible for displaying the player's current statistics, such as power points
 * and current room, in a nicely formatted output.
 */
public class ShowStatsCommand implements ICommand {
    private static final ILogger logger = LoggerFactory.getLogger(ShowStatsCommand.class);
    /**
     * Executes the command to display the player's current statistics, including power points
     * and the current room they are located in.
     *
     * @param context the current game context which contains the player and game state information.
     */
    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        logger.debug("Executing ShowStatsCommand for player: " + player);

        UIManager.getInstance().displayMessage(GameNarrator.showStats(player));

        logger.info("Player stats displayed: Power Points = " + player.getPowerPoints() +
                ", Current Room = " + player.getCurrentRoom().getLabel());
    }
}