package com.university.items;

import com.university.game.GameContext;
import com.university.items.effect.IEffect;

public abstract class Food implements IItem {
    protected IEffect effect;
    protected String name;
    protected String description;

    public Food(String name, String description, IEffect effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    @Override
    public IEffect getEffect() {
        return effect;
    }

    @Override
    public boolean isConsumable() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void use(GameContext gameContext) {
        effect.apply(gameContext);
    }
}
