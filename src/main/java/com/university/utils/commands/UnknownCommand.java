package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

public class UnknownCommand implements ICommand {
    private final ILogger logger = LoggerFactory.getLogger(UnknownCommand.class);

    @Override
    public void execute(GameContext context) {
        logger.warning("Unknown command entered by the player.");
        UIManager.getInstance().displayMessage("* I can't understand your input.\n* Enter `help` to see commands");
    }
}
