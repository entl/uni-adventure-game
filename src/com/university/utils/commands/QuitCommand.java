package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.utils.UI.GameNarrator;
import com.university.utils.UI.UIManager;

public class QuitCommand implements ICommand {
    @Override
    public void execute(GameContext context) {
        context.setRunning(false);
        UIManager.getInstance().displayMessage(GameNarrator.quitMessage());
    }
}
