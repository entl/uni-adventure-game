package com.university.items;

import com.university.game.GameContext;
import com.university.items.effect.IEffect;

/**
 * The interface for all items in the game. Every item in the game implements this interface,
 * allowing players to interact with them in the game context.
 */
public interface IItem {

    /**
     * Executes the use of the item in the given game context.
     *
     * @param gameContext The current game context where the item is being used.
     */
    void use(GameContext gameContext);

    /**
     * Retrieves the effect associated with the item.
     *
     * @return The effect of the item, as defined by the {@link IEffect} implementation.
     */
    IEffect getEffect();

    /**
     * Indicates whether the item is consumable.
     * Consumable items are removed from the player's inventory after use.
     *
     * @return true if the item is consumable, false otherwise.
     */
    boolean isConsumable();

    /**
     * Gets the internal name of the item. This is typically a unique identifier for the item.
     *
     * @return The internal name of the item.
     */
    String getName();

    /**
     * Provides a description of the item, giving details about its purpose or characteristics.
     *
     * @return The description of the item.
     */
    String getDescription();

    /**
     * Returns the display name of the item, which is shown to the player.
     * This name is typically less technical than the internal name.
     * **Not used particularly utilized in current iteration, feature for future**
     * @return The display name of the item.
     */
    String getDisplayName();
}