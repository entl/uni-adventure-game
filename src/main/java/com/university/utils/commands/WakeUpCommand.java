package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.player.Player;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

/**
 * The {@code WakeUpCommand} class implements the action of waking up the player
 * If the player is asleep, it attempts to use an "alarm clock" item from the player's inventory
 * If no alarm clock is available, the player wakes up by lossing power points
 */
public class WakeUpCommand implements ICommand {

    private static final ILogger logger = LoggerFactory.getLogger(WakeUpCommand.class);

    @Override
    public void execute(GameContext context) {
        Player player = context.getPlayer();
        IItem alarm = player.getInventoryManager().getItemByName("alarm clock");

        logger.debug("Executing WakeUpCommand for player: " + player);

        if (player.isAsleep()) {
            logger.info("Player is asleep. Attempting to wake up");
            if (alarm != null) {
                logger.debug("Found alarm clock in inventory. Using it to wake up");
                alarm.use(context);
            } else {
                logger.warning("No alarm clock found in inventory. Reducing power points to wake up");
                player.removePowerPoints(10);
                player.setAsleep(false);
                UIManager.getInstance().displayMessage(GameNarrator.wakeUp(10));
                logger.info("Player woke up at the cost of 10 power points");
            }
        } else {
            logger.info("Player is already awake. No action needed");
            UIManager.getInstance().displayMessage(GameNarrator.alreadyAwake());
        }
    }
}