package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.player.Player;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

/**
 * The {@code UseCommand} class implements the {@code ICommand} interface and
 * handles the use of an item from the player's inventory
 * The command will search for an item by name, and if found, it will apply its effect
 * If the item is consumable, it will be removed from the inventory after use
 */
public class UseCommand implements ICommand {
    private static final ILogger logger = LoggerFactory.getLogger(UseCommand.class);
    private String itemName;

    /**
     * Constructs a {@code UseCommand} with the specified item name to use.
     *
     * @param itemName the name of the item to use
     */
    public UseCommand(String itemName) {
        this.itemName = itemName;
        logger.debug("UseCommand created for item: " + itemName);
    }

    /**
     * Executes the command to use the specified item from the player's inventory.
     * If the item is found and used, it applies its effect. If the item is consumable, it is removed from the player's inventory.
     *
     * @param context the current game context, which includes the player and inventory state
     */
    @Override
    public void execute(GameContext context) {
        if (itemName == null) {
            logger.warning("Item name not specified for USE command.");
            UIManager.getInstance().displayMessage("* Specify item name");
            return;
        }

        Player player = context.getPlayer();
        logger.debug("Executing UseCommand for item: " + itemName + " by player: " + player);

        IItem item = player.getInventoryManager().getItemByName(itemName);

        if (item != null) {
            logger.info("Item found: " + itemName + ". Using the item.");
            item.use(context);

            if (item.isConsumable()) {
                player.getInventoryManager().removeItem(item);
                logger.info("Item " + itemName + " is consumable and has been removed from the inventory.");
            }
        } else {
            logger.warning("Item not found: " + itemName + ". Cannot use.");
            UIManager.getInstance().displayMessage(GameNarrator.itemNotFound(itemName));
        }
    }
}