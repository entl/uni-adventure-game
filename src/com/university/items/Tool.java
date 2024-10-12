package com.university.items;

import com.university.game.GameContext;
import com.university.items.effect.IEffect;

public abstract class Tool implements IItem {
    protected String name ;
    protected String description;
    protected IEffect effect;

    public Tool(String name, String description, IEffect effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    @Override
    public boolean isConsumable() {
        return false;
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
    public IEffect getEffect() {
        return effect;
    }

    @Override
    public void use(GameContext gameContext) {
        effect.apply(gameContext);
    }
}
