package com.university.elements.chests;

import com.university.core.GameContext;
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
 * Represents a mimic chest in the game that attacks the player when opened
 */
public class MimicChest implements IBox, IEventListener {
    ILogger logger = LoggerFactory.getLogger(MimicChest.class);

    private String description;
    private Boolean isOpened;

    private final int powerPoints = 10;

    public MimicChest(){
        logger.info("Creating a mimic chest.");
        this.description = "* An old chest with a rusty lock."; // description is same to fool the player
        logger.debug("Mimic initialized");
        this.isOpened = false;
        subscribeToEvents();
    }

    @Override
    public void open(GameContext gameContext, IEffect effect) {
        logger.info("Attempting to open the mimic chest.");
        if (isOpened) {
            logger.info("Mimic chest is already opened.");
            UIManager.getInstance().displayMessage("* The chest is already opened.");
        } else {
            logger.info("Mimic chest opened.");
            UIManager.getInstance().displayMessage(" * Oh no! The chest was a mimic! It attacks you! Minus " + powerPoints + " power points.");
            gameContext.getPlayer().removePowerPoints(powerPoints);
            this.isOpened = true;
        }
    }

    @Override
    public boolean isOpened() {
        return false;
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
