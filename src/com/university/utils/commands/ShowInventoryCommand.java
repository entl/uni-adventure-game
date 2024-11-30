package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.player.inventory.InventoryManager;
import com.university.utils.UI.GameNarrator;
import com.university.utils.UI.UIManager;


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
        InventoryManager inventory = context.getPlayer().getInventoryManager();

        UIManager.getInstance().displayMessage(GameNarrator.showInventory(inventory));
    }
}