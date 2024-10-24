package com.university.player.inventory;

import com.university.items.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code InventoryManager} class is responsible for managing the player's inventory.
 * It keeps track of items, as well as the number of spells and tools, and ensures the player
 * cannot exceed the maximum limit for spells and tools.
 */
public class InventoryManager {
    private static final int MAX_SPELLS = 2;
    private static final int MAX_TOOLS = 2;

    private List<IItem> inventory;
    private int spellsCount;
    private int toolsCount;

    /**
     * Constructs an {@code InventoryManager} with an empty inventory.
     */
    public InventoryManager() {
        this.inventory = new ArrayList<>();
        this.spellsCount = 0;
        this.toolsCount = 0;
    }

    /**
     * Adds an item to the inventory.
     * Spells and tools are subject to their respective maximum limits.
     *
     * @param item The item to add.
     * @return {@code true} if the item was added successfully, {@code false} if the item could not be added
     * due to exceeding the maximum number of spells or tools.
     */
    public boolean addItem(IItem item) {
        if (item instanceof Spell) {
            if (spellsCount >= MAX_SPELLS) {
                return false;
            }
            spellsCount++;
        } else if (item instanceof Tool) {
            if (toolsCount >= MAX_TOOLS) {
                return false;
            }
            toolsCount++;
        }

        inventory.add(item);
        return true;
    }

    /**
     * Removes an item from the inventory.
     * Adjusts the spell or tool count accordingly if a spell or tool is removed.
     *
     * @param item The item to remove.
     * @return {@code true} if the item was successfully removed, {@code false} if the item was not in the inventory.
     */
    public boolean removeItem(IItem item) {
        boolean isRemoved = inventory.remove(item);

        if (isRemoved) {
            if (item instanceof Spell) {
                spellsCount--;
            } else if (item instanceof Tool) {
                toolsCount--;
            }
        }

        return isRemoved;
    }

    /**
     * Retrieves an item from the inventory by its name.
     *
     * @param itemName The name of the item to find.
     * @return The item with the specified name, or {@code null} if the item is not found.
     */
    public IItem getItemByName(String itemName) {
        for (IItem item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Retrieves the current list of items in the inventory.
     *
     * @return The list of items in the inventory.
     */
    public List<IItem> getInventory() {
        return inventory;
    }
}