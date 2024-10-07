package com.university.utils.commands;

import com.university.game.GameContext;

public class MoveCommand implements ICommand {
    private Direction direction;

    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(GameContext context) {
        context.getPlayer().move(direction);
    }
}
