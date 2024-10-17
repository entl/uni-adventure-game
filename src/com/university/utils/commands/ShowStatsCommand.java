package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.player.Player;

public class ShowStatsCommand implements ICommand {
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
