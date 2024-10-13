package com.university.items;

import com.university.items.effect.IEffect;

public abstract class Tool extends AbstractItem {

    public Tool(String name, String description, IEffect effect) {
        super(name, description, effect);
    }

    @Override
    public boolean isConsumable() {
        return false;
    }
}
