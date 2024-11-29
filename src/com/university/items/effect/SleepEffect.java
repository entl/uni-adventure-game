package com.university.items.effect;

import com.university.game.GameContext;
import com.university.utils.UI.GameNarrator;
import com.university.utils.UI.UIManager;

/**
 * The {@code SleepEffect} class represents an effect that causes the player to fall asleep.
 * When applied, this effect sets the player's sleep status to {@code true}, preventing them from performing actions.
 */
public class SleepEffect implements IEffect {

    /**
     * Applies the sleep effect to the player.
     * {@code asleep} status to {@code true}.
     *
     * @param gameContext the context of the game, which includes the player and the current game state.
     */
    @Override
    public void apply(GameContext gameContext) {
        UIManager.getInstance().displayMessage(GameNarrator.sleepEffect());
        gameContext.getPlayer().setAsleep(true);
    }
}