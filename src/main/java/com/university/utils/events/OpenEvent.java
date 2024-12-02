package com.university.utils.events;

import com.university.elements.chests.IBox;
import com.university.elements.items.effect.IEffect;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

public class OpenEvent implements IEvent {
    private static final ILogger logger = LoggerFactory.getLogger(OpenEvent.class);
    private IEffect effect;
    private IBox box;

    public OpenEvent(IEffect effect, IBox box) {
        this.effect = effect;
        this.box = box;
        logger.info("OpenEvent created with effect: " + effect.getClass().getSimpleName() + " and box: " + box.getClass().getSimpleName());
    }

    public IEffect getEffect() {
        logger.debug("Getting effect: " + effect.getClass().getSimpleName());
        return effect;
    }

    public IBox getBox() {
        logger.debug("Getting box: " + box.getClass().getSimpleName());
        return box;
    }
}
