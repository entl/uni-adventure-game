package com.university.elements.boxes;

import com.university.core.GameContext;
import com.university.elements.items.effect.IEffect;

/**
 * Represents a chest in the game that can be opened by the player.
 * A chest may contain items or rewards that the player can collect.
 */
public interface IBox {

    /**
     * Opens the chest and reveals its contents
     * This method is called when the player interacts with the chest.
     *
     * @param gameContext The current game context, which provides information about the player and the game state.
     */
    void open(GameContext gameContext, IEffect effect);

    /**
     * Checks whether the chest has already been opened.
     *
     * @return true if the chest has been opened, false otherwise.
     */
    boolean isOpened();
}
