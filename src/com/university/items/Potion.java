package com.university.items;

import com.university.items.effect.IEffect;
import com.university.items.effect.SleepEffect;
import com.university.items.effect.XRayEffect;

import java.util.List;

public class Potion extends AbstractItem {
    public Potion() {
        super("potion", "It's so dark, you can't understand what is it...", getRandomEffect());
    }

    @Override
    public boolean isConsumable() {
        return true;
    }

    @Override
    public String getDisplayName() {
        return "suspicious potion";
    }

    // since player can't know the potion it has, effect is randomly chosen on creation
    private static IEffect getRandomEffect() {
        List<IEffect> effects = List.of(new XRayEffect(), new SleepEffect());
        return effects.get((int) (Math.random() * effects.size()));
    }
}
