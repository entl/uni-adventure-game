package com.university.utils.events;

import com.university.elements.chests.IBox;
import com.university.elements.items.effect.IEffect;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

/**
 * Represents an event that occurs when a box is opened.
 * The event is associated with an effect and a box.
 */
public class OpenEvent implements IEvent {
    private static final ILogger logger = LoggerFactory.getLogger(OpenEvent.class);
    private IEffect effect;
    private IBox box;

    /**
     * Constructs an OpenEvent with the specified effect and box.
     *
     * @param effect the effect associated with the event
     * @param box    the box associated with the event
     */
    public OpenEvent(IEffect effect, IBox box) {
        this.effect = effect;
        this.box = box;
        logger.info("OpenEvent created with effect: " + effect.getClass().getSimpleName() + " and box: " + box.getClass().getSimpleName());
    }

    /**
     * Returns the effect associated with this event.
     *
     * @return the effect associated with the event
     */
    public IEffect getEffect() {
        logger.debug("Getting effect: " + effect.getClass().getSimpleName());
        return effect;
    }

    /**
     * Returns the box associated with this event.
     *
     * @return the box associated with the event
     */
    public IBox getBox() {
        logger.debug("Getting box: " + box.getClass().getSimpleName());
        return box;
    }
}