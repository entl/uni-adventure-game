package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.boxes.IBox;
import com.university.utils.events.OpenEvent;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UIManager;

/**
 * The {@code SpannerEffect} class represents an effect that allows the player to interact with chests in the room
 * When applied, this effect opens a closed chest in the current room. If the chest is already open or if no chest
 * is present, appropriate messages are displayed
 */
public class SpannerEffect implements IEffect {
    private static final ILogger logger = LoggerFactory.getLogger(SpannerEffect.class);

    /**
     * Applies the spanner effect, allowing the player to open a chest in the current room
     * If the chest is already opened, a message is shown. If no chest is found, the player is notified
     *
     * @param gameContext the context of the game, which includes the player and the current game state
     */
    @Override
    public void apply(GameContext gameContext) {
        logger.info("Applying Spanner effect.");
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        logger.debug("Current room: " + currentRoom.getLabel());

        IBox chest = currentRoom.getChest();
        if (chest != null && !chest.isOpened()) {
            logger.info("Chest found in room: " + currentRoom.getLabel() + " and it is closed. Attempting to open.");
            gameContext.getEventManager().dispatchEvent(new OpenEvent(this, chest));
            logger.info("Chest in room: " + currentRoom.getLabel() + " opened successfully.");
        } else if (chest != null && chest.isOpened()) {
            logger.info("Chest found in room: " + currentRoom.getLabel() + " but it is already opened.");
            UIManager.getInstance().displayMessage(GameNarrator.chestAlreadyOpen());
        } else {
            logger.warning("No chest found in room: " + currentRoom.getLabel());
            UIManager.getInstance().displayMessage(GameNarrator.chestNotFound());
        }
    }
}