package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.items.IItem;

public class DropCommand implements ICommand {
    private String itemName;

    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute(GameContext context) {
        IItem item = context.getPlayer().getInventoryManager().getItemByName(itemName);

        if (item != null) {
            context.getPlayer().getCurrentRoom().addItem(item);
            context.getPlayer().getInventoryManager().removeItem(item);
            System.out.println("* You dropped " + item.getName());
        } else {
            System.out.printf("* %s not found!", itemName);
        }
    }
}
