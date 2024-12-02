package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

/**
 * The {@code QuitCommand} class handles the action of quitting the game
 * When executed, it stops the game loop and displays a quit message to the player
 */
public class QuitCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(QuitCommand.class);

    /**
     * Executes the quit action by stopping the game and displaying a quit message
     *
     * @param context The current game context containing the game state
     */
    @Override
    public void execute(GameContext context) {
        logger.info("Executing QuitCommand. Stopping the game");
        context.setRunning(false);
        UIManager.getInstance().displayMessage(GameNarrator.quitMessage());
        logger.info("Game has been stopped. Quit message displayed to the player");
    }
}