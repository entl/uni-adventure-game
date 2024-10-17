package com.university.utils.commands;

import com.university.game.GameContext;


public class HelpCommand implements ICommand {

    @Override
    public void execute(GameContext context) {
        for (Command command : Command.values()) {
            if (command != Command.UNKNOWN) {
                // for consistent output set the width of the first column to 10
                System.out.printf("- %-10s : %s\n", command.getAliases().getFirst(), command.getDescription());
            }
        }
    }
}
