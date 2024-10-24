package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.items.IItem;

import java.util.List;

/**
 * The {@code ShowInventoryCommand} class implements the {@code ICommand} interface
 * and is responsible for displaying the player's current inventory.
 * The inventory is displayed with formatted borders and includes item names and descriptions.
 */
public class ShowInventoryCommand implements ICommand {

    /**
     * Executes the command to display the player's inventory.
     * If the inventory is empty, a message is shown to the player indicating the lack of items.
     * Otherwise, the inventory is displayed in a formatted table with item names and descriptions.
     *
     * @param context the current game context which contains the player's inventory
     *                and other game-related state information.
     */
    @Override
    public void execute(GameContext context) {
        List<IItem> inventory = context.getPlayer().getInventoryManager().getInventory();

        // Display the inventory with borders and headers
        System.out.println("=======================================");
        System.out.println("               Inventory               ");
        System.out.println("=======================================");

        if (inventory.isEmpty()) {
            System.out.println("* You can't see any items in your backpack.");
            System.out.println("=======================================");
            return;
        }

        // Print column headers
        System.out.printf("%-25s %s%n", "Name", "Description");
        System.out.println("---------------------------------------");

        // Iterate through inventory items and display them with formatting
        for (IItem item : inventory) {
            // Truncate descriptions if they are too long for better readability
            String description = item.getDescription();
            if (description.length() > 60) {
                description = description.substring(0, 57) + "...";
            }
            System.out.printf("%-23s %s%n", item.getDisplayName(), description);
        }

        // Close the inventory display with a border
        System.out.println("=======================================");
    }
}