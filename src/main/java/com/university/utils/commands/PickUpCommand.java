package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code PickUpCommand} class implements the {@code ICommand} interface and
 * handles the action of picking up an item from the current room.
 */
public class PickUpCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(PickUpCommand.class);
    private String itemName;

    /**
     * Constructs a {@code PickUpCommand} with the specified item name.
     *
     * @param itemName the name of the item the player wants to pick up
     */
    public PickUpCommand(String itemName) {
        this.itemName = itemName;
        logger.debug("PickUpCommand created for item: " + itemName);
    }

    /**
     * Executes the pick-up command, which allows the player to pick up an item
     * from the current room if it exists and the player has space in their inventory.
     * If the item is successfully picked up, it is removed from the room and added to the player's inventory.
     * If the inventory is full, the player is notified that they cannot pick up more items.
     *
     * @param context the current game context which contains information about the player, their inventory,
     *                the room, and the overall game state
     */
    @Override
    public void execute(GameContext context) {
        if (itemName == null) {
            logger.warning("Item name not specified for PICKUP command.");
            UIManager.getInstance().displayMessage("* Specify item name");
            return;
        }

        logger.debug("Executing PickUpCommand for item: " + itemName);
        IItem item = context.getPlayer().getCurrentRoom().getItemByName(itemName);

        if (item != null) {
            logger.info("Item found in current room: " + itemName);
            boolean isPickedUp = context.getPlayer().getInventoryManager().addItem(item);

            if (!isPickedUp) {
                logger.warning("Failed to pick up item: " + itemName + ". Inventory is full.");
                UIManager.getInstance().displayMessage(GameNarrator.inventoryFull(itemName));
                return;
            }

            context.getPlayer().getCurrentRoom().removeItem(item);
            UIManager.getInstance().displayMessage(GameNarrator.itemPickedUp(itemName));
            logger.info("Item successfully picked up: " + itemName);
        } else {
            logger.warning("Item not found in the current room: " + itemName);
            UIManager.getInstance().displayMessage(GameNarrator.itemNotFound(itemName));
        }
    }
}