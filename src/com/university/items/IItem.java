package com.university.items;

import com.university.game.GameContext;
import com.university.items.effect.IEffect;

public interface IItem {
    void use(GameContext gameContext);

    IEffect getEffect();
    boolean isConsumable();

    String getName();
    String getDescription();
}
