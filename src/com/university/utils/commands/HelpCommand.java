package com.university.utils.commands;

import com.university.game.GameContext;

/**
 * The {@code HelpCommand} class implements the {@code ICommand} interface
 * This command outputs a list of available commands, along with their descriptions and aliases,
 * to guide the player on how to interact with the game.
 */
public class HelpCommand implements ICommand {

    /**
     * Executes the help command by printing a list of all available commands (except unknown commands).
     * The output includes the main alias of each command (formatted for alignment) and the command's description.
     *
     * @param context the current game context, which is unused in this command but required for the interface
     */
    @Override
    public void execute(GameContext context) {
        for (Command command : Command.values()) {
            if (command != Command.UNKNOWN) {
                // For consistent output, set the width of the first column to 10 characters
                System.out.printf("- %-10s : %s\n", command.getAliases().getFirst(), command.getDescription());
            }
        }
    }
}