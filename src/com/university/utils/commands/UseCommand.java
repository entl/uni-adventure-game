package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.items.IItem;
import com.university.player.Player;

public class UseCommand implements ICommand {
    private String itemName;

    public UseCommand(String itemName) {
        this.itemName = itemName;
    }

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
            System.out.println("* Item not found!");
        }
    }
}
