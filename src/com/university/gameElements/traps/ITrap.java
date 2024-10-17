package com.university.gameElements.traps;

import com.university.game.GameContext;
import com.university.gameElements.traps.strategies.IEscapeStrategy;

public interface ITrap {
    void activate(GameContext gameContext);
    void escape(GameContext gameContext, IEscapeStrategy escapeStrategy);
    String getName();
    String getDescription();

    boolean isActive();

    void printDescriptionByState();
}
