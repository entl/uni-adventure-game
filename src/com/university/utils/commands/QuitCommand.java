package com.university.utils.commands;

import com.university.game.GameContext;

public class QuitCommand implements ICommand {
    @Override
    public void execute(GameContext context) {
        context.setRunning(false);
    }
}
