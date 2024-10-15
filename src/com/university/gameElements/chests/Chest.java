package com.university.gameElements.chests;

import com.university.game.GameContext;
import com.university.items.IItem;
import com.university.items.ItemFactory;

public class Chest implements IChest {
    String description;
    Boolean isOpened;
    IItem item;

    public Chest() {
        this.description = "* An old chest with a rusty lock.";
        this.item = new ItemFactory().createRandomItem();
        this.isOpened = false;
    }

    @Override
    public void open(GameContext gameContext) {
        if (isOpened) {
            System.out.println("* The chest is already opened.");
        } else {
            System.out.println("* You opened the chest and found a " + item.getDisplayName() + ".");
            System.out.println("* You can pick it up!");
            gameContext.getPlayer().getCurrentRoom().addItem(item);
            this.isOpened = true;
        }
    }

    @Override
    public boolean isOpened() {
        return isOpened;
    }
}
