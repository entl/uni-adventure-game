package com.university.utils.commands;

import com.university.game.GameContext;

public class LookAroundCommand implements ICommand {
    @Override
    public void execute(GameContext context) {
        context.getPlayer().lookAround();
    }
}
