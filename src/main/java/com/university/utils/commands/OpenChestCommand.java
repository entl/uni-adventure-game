package com.university.utils.commands;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

public class OpenChestCommand implements ICommand{
    private static final ILogger logger = LoggerFactory.getLogger(OpenChestCommand.class);
    private String itemName;

    public OpenChestCommand(String itemName) {
        this.itemName = itemName;
        logger.debug("OpenChestCommand created for item: " + itemName);
    }

    @Override
    public void execute(GameContext context) {
        IItem itemFromInventory = context.getPlayer().getInventoryManager().getItemByName(itemName);

        if (itemFromInventory == null) {
            UIManager.getInstance().displayMessage(GameNarrator.itemNotFound(itemName));
            return;
        }

        itemFromInventory.use(context);
    }
}
