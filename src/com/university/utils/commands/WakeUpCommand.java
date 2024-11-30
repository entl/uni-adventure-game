package com.university.utils.commands;

import com.university.game.GameContext;
import com.university.items.IItem;
import com.university.player.Player;
import com.university.utils.UI.GameNarrator;
import com.university.utils.UI.UIManager;

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
                player.setAsleep(false);
                UIManager.getInstance().displayMessage(GameNarrator.wakeUp(10));
            }
        } else {
            UIManager.getInstance().displayMessage(GameNarrator.alreadyAwake());
        }
    }
}
