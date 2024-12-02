package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.player.inventory.InventoryManager;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;


/**
 * The {@code ShowInventoryCommand} class implements the {@code ICommand} interface
 * and is responsible for displaying the player's current inventory.
 * The inventory is displayed with formatted borders and includes item names and descriptions.
 */
public class ShowInventoryCommand implements ICommand {
    private static final ILogger logger = LoggerFactory.getLogger(ShowInventoryCommand.class);
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
        InventoryManager inventory = context.getPlayer().getInventoryManager();
        logger.debug("Executing ShowInventoryCommand for player's inventory: " + inventory);

        if (inventory.getInventory().isEmpty()) {
            logger.info("Player's inventory is empty");
        } else {
            logger.info("Displaying player's inventory: " + inventory.getInventory());
        }

        UIManager.getInstance().displayMessage(GameNarrator.showInventory(inventory));
        logger.debug("Inventory displayed successfully");
    }
}