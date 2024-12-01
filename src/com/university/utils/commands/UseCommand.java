package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.player.Player;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code UseCommand} class implements the {@code ICommand} interface and
 * handles the use of an item from the player's inventory.
 * The command will search for an item by name, and if found, it will apply its effect.
 * If the item is consumable, it will be removed from the inventory after use.
 */
public class UseCommand implements ICommand {
    private String itemName;

    /**
     * Constructs a {@code UseCommand} with the specified item name to use.
     *
     * @param itemName the name of the item to use
     */
    public UseCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Executes the command to use the specified item from the player's inventory.
     * If the item is found and used, it applies its effect. If the item is consumable, it is removed from the player's inventory.
     *
     * @param context the current game context, which includes the player and inventory state
     */
    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        IItem item = player.getInventoryManager().getItemByName(itemName);

        if (item != null) {
            item.use(context);

            if (item.isConsumable()) {
                player.getInventoryManager().removeItem(item);
            }
        } else {
            UIManager.getInstance().displayMessage(GameNarrator.itemNotFound(itemName));
        }
    }
}