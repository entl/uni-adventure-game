package com.university.player.inventory;

import com.university.elements.items.*;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code InventoryManager} class is responsible for managing the player's inventory.
 * It keeps track of items, as well as the number of spells and tools, and ensures the player
 * cannot exceed the maximum limit for spells and tools.
 */
public class InventoryManager {
    private static final ILogger logger = LoggerFactory.getLogger(InventoryManager.class);

    private static final int MAX_SPELLS = 2;
    private static final int MAX_TOOLS = 2;

    private List<IItem> inventory;
    private int spellsCount;
    private int toolsCount;

    /**
     * Constructs an {@code InventoryManager} with an empty inventory
     */
    public InventoryManager() {
        this.inventory = new ArrayList<>();
        this.spellsCount = 0;
        this.toolsCount = 0;
        logger.debug("InventoryManager initialized with empty inventory.");
    }

    /**
     * Adds an item to the inventory
     * Spells and tools are subject to their respective maximum limits
     *
     * @param item The item to add
     * @return {@code true} if the item was added successfully, {@code false} if the item could not be added
     * due to exceeding the maximum number of spells or tools
     */
    public boolean addItem(IItem item) {
        logger.debug("Attempting to add item: " + item.getName());

        if (item instanceof Spell) {
            if (spellsCount >= MAX_SPELLS) {
                logger.warning("Failed to add spell: " + item.getName() + ". Maximum limit of spells reached.");
                return false;
            }
            spellsCount++;
            logger.info("Spell added: " + item.getName() + ". Current spell count: " + spellsCount);
        } else if (item instanceof Tool) {
            if (toolsCount >= MAX_TOOLS) {
                logger.warning("Failed to add tool: " + item.getName() + ". Maximum limit of tools reached.");
                return false;
            }
            toolsCount++;
            logger.info("Tool added: " + item.getName() + ". Current tool count: " + toolsCount);
        }

        inventory.add(item);
        logger.info("Item added to inventory: " + item.getName());
        return true;
    }

    /**
     * Removes an item from the inventory
     * Adjusts the spell or tool count accordingly if a spell or tool is removed
     *
     * @param item The item to remove
     * @return {@code true} if the item was successfully removed, {@code false} if the item was not in the inventory
     */
    public boolean removeItem(IItem item) {
        logger.debug("Attempting to remove item: " + item.getName());
        boolean isRemoved = inventory.remove(item);

        if (isRemoved) {
            if (item instanceof Spell) {
                spellsCount--;
                logger.info("Spell removed: " + item.getName() + ". Current spell count: " + spellsCount);
            } else if (item instanceof Tool) {
                toolsCount--;
                logger.info("Tool removed: " + item.getName() + ". Current tool count: " + toolsCount);
            }

            logger.info("Item removed from inventory: " + item.getName());
        } else {
            logger.warning("Failed to remove item: " + item.getName() + ". Item not found in inventory.");
        }

        return isRemoved;
    }

    /**
     * Retrieves an item from the inventory by its name
     *
     * @param itemName The name of the item to find
     * @return The item with the specified name, or {@code null} if the item is not found
     */
    public IItem getItemByName(String itemName) {
        logger.debug("Searching for item by name: " + itemName);
        for (IItem item : inventory) {
            if (item.getName().equals(itemName)) {
                logger.info("Item found in inventory: " + itemName);
                return item;
            }
        }
        logger.warning("Item not found in inventory: " + itemName);
        return null;
    }

    /**
     * Retrieves the current list of items in the inventory.
     *
     * @return The list of items in the inventory.
     */
    public List<IItem> getInventory() {
        logger.debug("Retrieving full inventory. Current size: " + inventory.size());
        return inventory;
    }
}