package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.player.Player;

/**
 * The {@code ShowStatsCommand} class implements the {@code ICommand} interface
 * and is responsible for displaying the player's current statistics, such as power points
 * and current room, in a nicely formatted output.
 */
public class ShowStatsCommand implements ICommand {

    /**
     * Executes the command to display the player's current statistics, including power points
     * and the current room they are located in.
     *
     * @param context the current game context which contains the player and game state information.
     */
    @Override
    public void execute(GameContext context) {
        // get player stats
        Player player = context.getPlayer();
        int powerPoints = player.getPowerPoints();
        String currentRoom = player.getCurrentRoom().getLabel();

        // output in nice format using separators and padding
        System.out.println("=======================================");
        System.out.println("               Your Stats               ");
        System.out.println("---------------------------------------");

        System.out.printf("%-20s %s%n", "Attribute", "Value");
        System.out.println("---------------------------------------");

        System.out.printf("%-20s %d%n", "Power Points", powerPoints);
        System.out.printf("%-20s %s%n", "Current Room", currentRoom);

        System.out.println("=======================================");
    }
}