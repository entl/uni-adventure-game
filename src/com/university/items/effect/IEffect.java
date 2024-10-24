package com.university.items.effect;

import com.university.game.GameContext;

/**
 * The {@code IEffect} interface represents an effect that can be applied within the game context.
 * Implementations of this interface define specific behaviors that occur when the effect is triggered.
 * <p>
 * Effects can be applied to various elements in the game, such as items, players, or the dungeon itself.
 * The {@link GameContext} provides access to the current state of the game, allowing effects to modify the game world.
 * @see GameContext
 *
 * <p>
 * Currently, some effects as {@link com.university.items.FreezeSpell} are tigthly coupled with objects they affect.
 */
public interface IEffect {

    /**
     * Applies the effect to the game context.
     *
     * @param gameContext the context of the game, which contains the current state of the player, dungeon, and other elements
     */
    void apply(GameContext gameContext);
}