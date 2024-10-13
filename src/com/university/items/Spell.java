package com.university.items;

import com.university.items.effect.IEffect;

public abstract class Spell extends AbstractItem {

    public Spell(String name, String description, IEffect effect) {
        super(name, description, effect);
    }

    @Override
    public boolean isConsumable() {
        return true;
    }
}
