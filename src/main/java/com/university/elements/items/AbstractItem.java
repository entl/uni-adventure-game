package com.university.elements.items;

import com.university.core.GameContext;
import com.university.elements.items.effect.IEffect;

/**
 * An abstract class that implements the {@link IItem} interface and provides a base implementation
 * for common functionality across all items in the game.
 * <p>
 * This class defines common properties such as the item's name, description, and effect. Subclasses
 * should provide additional details or specific behavior for particular types of items.
 */
public abstract class AbstractItem implements IItem {
    protected String name;
    protected String description;
    protected IEffect effect;

    /**
     * Constructs an {@code AbstractItem} with the specified name, description, and effect.
     *
     * @param name        The name of the item.
     * @param description A brief description of the item.
     * @param effect      The effect that this item has when used in the game.
     */
    public AbstractItem(String name, String description, IEffect effect) {
        this.name = name;
        this.description = description;
        this.effect = effect;
    }

    /**
     * Returns the internal name of the item.
     *
     * @return The name of the item.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns a description of the item.
     *
     * @return The description of the item.
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Returns the effect associated with the item.
     *
     * @return The item's effect.
     */
    @Override
    public IEffect getEffect() {
        return effect;
    }

    /**
     * Uses the item, applying its effect in the given game context.
     *
     * @param gameContext The game context in which the item is being used.
     */
    @Override
    public void use(GameContext gameContext) {
        effect.apply(gameContext);
    }

    /**
     * Returns the display name of the item, typically used to present the item in a user-friendly way.
     *
     * @return The display name of the item.
     */
    @Override
    public String getDisplayName() {
        return name;
    }

    /**
     * Compares this item to another object for equality. Two items are considered equal if they have the same name.
     *
     * @param o The object to compare this item against.
     * @return {@code true} if the items are equal based on their names; {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IItem item = (IItem) o;
        return name.equals(item.getName());
    }

    /**
     * Returns a hash code value for the item. The hash code is based on the item's name.
     *
     * @return The hash code for this item.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}