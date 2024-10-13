package com.university.items;

import com.university.game.GameContext;
import com.university.items.effect.IEffect;

public abstract class AbstractItem implements IItem {
    protected String name;
    protected String description;
    protected IEffect effect;

    public AbstractItem(String name, String description, IEffect effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
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

    // Override equals method to compare based on the item's name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IItem item = (IItem) o;
        return name.equals(item.getName());
    }

    // Override hashCode method to be consistent with equals
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
