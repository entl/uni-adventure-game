package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.items.IItem;

public class PickUpCommand implements ICommand {
    private String itemName;

    public PickUpCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute(GameContext context) {
        IItem item = context.getPlayer().getCurrentRoom().getItemByName(itemName);

        if (item != null) {
            boolean isPickedUp =  context.getPlayer().getInventoryManager().addItem(item);

            if (!isPickedUp) {
                System.out.println("* You can't pick up more items!");
                return;
            }
            context.getPlayer().getCurrentRoom().removeItem(item);
            System.out.println("* You picked up " + item.getName());
        } else {
            System.out.println("* Item not found!");
        }
    }
}
