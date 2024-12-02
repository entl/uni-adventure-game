package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code DropCommand}class implements the {@code ICommand} interface to drop an item from the player's inventory
 * into the current room. It is part of the command pattern used to handle player actions in the game.
 */
public class DropCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(DropCommand.class);
    private String itemName;

    /**
     * Constructs a {@code DropCommand} with the specified item name to be dropped.
     *
     * @param itemName the name of the item the player wants to drop
     */
    public DropCommand(String itemName) {
        this.itemName = itemName;
        logger.debug("DropCommand created for item: " + itemName);
    }

    /**
     * Executes the drop action. If the item is found in the player's inventory, it is removed from the inventory
     * and added to the current room. If the item is not found, the player is informed.
     *
     * @param context the current game context, which includes the player's inventory and the current room
     */
    @Override
    public void execute(GameContext context) {
        logger.debug("Executing DropCommand for item: " + itemName);

        IItem item = context.getPlayer().getInventoryManager().getItemByName(itemName);

        if (item != null) {
            logger.info("Item found in inventory: " + itemName);
            context.getPlayer().getCurrentRoom().addItem(item);
            context.getPlayer().getInventoryManager().removeItem(item);

            UIManager.getInstance().displayMessage(GameNarrator.itemDropped(itemName));
            logger.info("Item successfully dropped into room: " + context.getPlayer().getCurrentRoom().getLabel());
        } else {
            logger.warning("Item not found in inventory: " + itemName);
            UIManager.getInstance().displayMessage(GameNarrator.itemNotFound(itemName));
        }

        logger.debug("DropCommand execution completed");
    }
}