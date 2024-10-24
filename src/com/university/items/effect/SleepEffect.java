package com.university.items.effect;

import com.university.game.GameContext;

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
        System.out.println("* You feel drowsy and fall asleep.");
        gameContext.getPlayer().setAsleep(true);
    }
}