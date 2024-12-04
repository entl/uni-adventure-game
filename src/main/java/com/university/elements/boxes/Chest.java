package com.university.elements.boxes;

import com.university.core.GameContext;
import com.university.elements.items.IItem;
import com.university.elements.items.ItemFactory;
import com.university.elements.items.effect.IEffect;
import com.university.elements.items.effect.SpannerEffect;
import com.university.utils.events.EventManager;
import com.university.utils.events.IEvent;
import com.university.utils.events.IEventListener;
import com.university.utils.events.OpenEvent;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.ui.UIManager;

import java.util.List;

/**
 * Represents a chest in the game that contains a random item
 * The chest can be opened by the player, and once opened, the item is added to the current room's inventory
 */
public class Chest implements IBox, IEventListener {
    private static final ILogger logger = LoggerFactory.getLogger(Chest.class);

    private String description;
    private Boolean isOpened;
    private IItem item;

    /**
     * Constructs a new Chest with a default description and a random item inside
     * The chest is initially closed
     */
    public Chest() {
        logger.info("Creating a new chest.");
        this.description = "* An old chest with a rusty lock.";
        this.item = new ItemFactory().createRandomItem();
        logger.debug("Chest initialized with item: " + item.getDisplayName());
        this.isOpened = false;
        subscribeToEvents();
    }

    /**
     * Opens the chest if it hasn't been opened yet
     * If the chest is already opened, it notifies the player
     * Upon opening, the item inside the chest is added to the current room's inventory
     *
     * @param gameContext The current game context, providing information about the player and the room
     */
    @Override
    public void open(GameContext gameContext, IEffect effect) {
        logger.info("Attempting to open the chest.");
        if (isOpened) {
            logger.info("Chest is already opened.");
            UIManager.getInstance().displayMessage("* The chest is already opened.");
        } else {
            logger.info("Chest opened. Item inside: " + item.getDisplayName());
            UIManager.getInstance().displayMessage("* You opened the chest and found a " + item.getDisplayName() + ".");
            UIManager.getInstance().displayMessage("* You can pick it up!");
            gameContext.getPlayer().getCurrentRoom().addItem(item);
            this.isOpened = true;
            logger.debug("Item added to the current room: " + gameContext.getPlayer().getCurrentRoom().getLabel());
        }
    }

    /**
     * Checks whether the chest has been opened
     *
     * @return true if the chest has been opened, false otherwise
     */
    @Override
    public boolean isOpened() {
        logger.debug("Checking if chest is opened: " + isOpened);
        return isOpened;
    }


    @Override
    public void onEvent(IEvent event) {
        logger.debug("Received event: " + event.getClass());
        List<Class<? extends IEffect>> effects = List.of(SpannerEffect.class);
        if (event instanceof OpenEvent && !isOpened) {
            OpenEvent openEvent = (OpenEvent) event;
            if (openEvent.getBox() == this) {
                if (effects.contains(openEvent.getEffect().getClass())) {
                    open(GameContext.initialize(), openEvent.getEffect());
                } else {
                    logger.warning("Invalid effect for opening the chest.");
                    UIManager.getInstance().displayMessage("* You cannot open the chest with that.");
                }
            }
        }
    }

    /**
     * Subscribes the chest to relevant events
     */
    private void subscribeToEvents() {
        logger.debug("Subscribing to events.");
        EventManager.getInstance().registerListener(OpenEvent.class, this);
    }
}