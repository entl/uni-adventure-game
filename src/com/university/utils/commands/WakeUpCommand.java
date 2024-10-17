package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.items.IItem;
import com.university.player.Player;

public class WakeUpCommand implements ICommand {
    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        IItem alarm = player.getInventoryManager().getItemByName("alarm clock");

        if (player.isAsleep()) {
            if (alarm != null) {
                alarm.use(context);
            } else {
                player.removePowerPoints(10);
                System.out.println("* You wake up to the sound of a loud noise, but you can't find the source of it. You lose 10 power points.");
            }
        } else {
            System.out.println("* You are already awake.");
        }
    }
}
