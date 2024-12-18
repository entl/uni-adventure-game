package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code HelpCommand} class implements the {@code ICommand} interface
 * This command outputs a list of available commands, along with their descriptions and aliases,
 * to guide the player on how to interact with the game.
 */
public class HelpCommand implements ICommand {

    @Override
    public void execute(GameContext context) {
        UIManager.getInstance().displayMessage(GameNarrator.helpList());
    }
}