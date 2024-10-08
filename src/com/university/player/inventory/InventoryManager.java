package com.university.player.inventory;

import com.university.item.IItem;
import com.university.item.Spell;
import com.university.item.TeleportationSpell;
import com.university.item.Tool;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private static final int MAX_SPELLS = 2;
    private static final int MAX_TOOLS = 2;

    private List<IItem> inventory;
    private int spellsCount;
    private int toolsCount;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
        inventory.add(new TeleportationSpell("teleportation spell", "Teleport to the destination room", "destination"));
        this.spellsCount = 1;
        this.toolsCount = 0;
    }

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

    public boolean hasItem(IItem item) {
        return inventory.contains(item);
    }

    public IItem getItemByName(String itemName) {
        for (IItem item : inventory) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public List<IItem> getInventory() {
        return inventory;
    }
}
