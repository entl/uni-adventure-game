package com.university.gameElements.chests;

import com.university.game.GameContext;
import com.university.items.IItem;
import com.university.items.ItemFactory;
import com.university.utils.UI.UIManager;

/**
 * Represents a chest in the game that contains a random item.
 * The chest can be opened by the player, and once opened, the item is added to the current room's inventory.
 */
public class Chest implements IChest {
    private String description;
    private Boolean isOpened;
    private IItem item;

    /**
     * Constructs a new Chest with a default description and a random item inside.
     * The chest is initially closed.
     */
    public Chest() {
        this.description = "* An old chest with a rusty lock.";
        this.item = new ItemFactory().createRandomItem();
        this.isOpened = false;
    }

    /**
     * Opens the chest if it hasn't been opened yet.
     * If the chest is already opened, it notifies the player.
     * Upon opening, the item inside the chest is added to the current room's inventory.
     *
     * @param gameContext The current game context, providing information about the player and the room.
     */
    @Override
    public void open(GameContext gameContext) {
        if (isOpened) {
            UIManager.getInstance().displayMessage("* The chest is already opened.");
        } else {
            UIManager.getInstance().displayMessage("* You opened the chest and found a " + item.getDisplayName() + ".");
            UIManager.getInstance().displayMessage("* You can pick it up!");
            gameContext.getPlayer().getCurrentRoom().addItem(item);
            this.isOpened = true;
        }
    }

    /**
     * Checks whether the chest has been opened.
     *
     * @return true if the chest has been opened, false otherwise.
     */
    @Override
    public boolean isOpened() {
        return isOpened;
    }
}