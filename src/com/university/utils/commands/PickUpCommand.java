package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code PickUpCommand} class implements the {@code ICommand} interface and
 * handles the action of picking up an item from the current room.
 */
public class PickUpCommand implements ICommand {

    private String itemName;

    /**
     * Constructs a {@code PickUpCommand} with the specified item name.
     *
     * @param itemName the name of the item the player wants to pick up.
     */
    public PickUpCommand(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Executes the pick-up command, which allows the player to pick up an item
     * from the current room if it exists and the player has space in their inventory.
     * If the item is successfully picked up, it is removed from the room and added to the player's inventory.
     * If the inventory is full, the player is notified that they cannot pick up more items.
     *
     * @param context the current game context which contains information about the player, their inventory,
     *                the room, and the overall game state.
     */
    @Override
    public void execute(GameContext context) {
        IItem item = context.getPlayer().getCurrentRoom().getItemByName(itemName);

        if (item != null) {
            boolean isPickedUp = context.getPlayer().getInventoryManager().addItem(item);

            if (!isPickedUp) {
                UIManager.getInstance().displayMessage(GameNarrator.inventoryFull(itemName));
                return;
            }
            context.getPlayer().getCurrentRoom().removeItem(item);
            UIManager.getInstance().displayMessage(GameNarrator.itemPickedUp(itemName));
        } else {
            UIManager.getInstance().displayMessage(GameNarrator.itemNotFound(itemName));
        }
    }
}