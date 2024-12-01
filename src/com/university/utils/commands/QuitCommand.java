package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

public class QuitCommand implements ICommand {
    @Override
    public void execute(GameContext context) {
        context.setRunning(false);
        UIManager.getInstance().displayMessage(GameNarrator.quitMessage());
    }
}
