package com.university.gameElements.chests;

import com.university.game.GameContext;

public interface IChest {
    void open(GameContext gameContext);
    boolean isOpened();
}
