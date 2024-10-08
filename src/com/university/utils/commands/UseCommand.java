package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.item.IItem;
import com.university.player.Player;

public class UseCommand implements ICommand {
    private String itemName;

    public UseCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        System.out.println(itemName);
        IItem item = player.getItemFromName(itemName);

        System.out.println(player.getInventory());
        String temp = player.getInventory().get(0).getName();
        System.out.println(temp);

        if (item != null) {
            player.use(item);
            System.out.println("* Item used! DELETE LATER");
        } else {
            System.out.println("* Item not found!");
        }
    }
}
