package unit;

import com.university.elements.items.IItem;
import com.university.elements.items.Spell;
import com.university.player.inventory.InventoryManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InventoryManagerTest {

    private InventoryManager inventoryManager;

    @Before
    public void setUp() {
        inventoryManager = new InventoryManager();
    }

    @Test
    public void addItem_ShouldSucceed_WhenItemDoesNotExceedLimit() {
        IItem freezeSpell = mock(IItem.class);
        when(freezeSpell.getName()).thenReturn("Freeze Spell");

        boolean result = inventoryManager.addItem(freezeSpell);

        assertTrue(result);
        assertEquals(freezeSpell, inventoryManager.getItemByName("Freeze Spell"));
    }

    @Test
    public void addItem_ShouldFail_WhenAddingMoreThanAllowedSpells() {
        IItem freezeSpell1 = mock(Spell.class);
        IItem freezeSpell2 = mock(Spell.class);
        IItem extraSpell = mock(Spell.class);

        when(freezeSpell1.getName()).thenReturn("Freeze Spell 1");
        when(freezeSpell2.getName()).thenReturn("Freeze Spell 2");
        when(extraSpell.getName()).thenReturn("Extra Spell");

        inventoryManager.addItem(freezeSpell1);
        inventoryManager.addItem(freezeSpell2);

        boolean result = inventoryManager.addItem(extraSpell);

        assertFalse(result);
        assertEquals(2, inventoryManager.getInventory().size());
    }

    @Test
    public void removeItem_ShouldSucceed_WhenItemExistsInInventory() {
        IItem freezeSpell = mock(IItem.class);
        when(freezeSpell.getName()).thenReturn("Freeze Spell");

        inventoryManager.addItem(freezeSpell);

        boolean result = inventoryManager.removeItem(freezeSpell);

        assertTrue(result);
        assertNull(inventoryManager.getItemByName("Freeze Spell"));
    }

    @Test
    public void removeItem_ShouldFail_WhenItemDoesNotExistInInventory() {
        IItem freezeSpell = mock(IItem.class);
        when(freezeSpell.getName()).thenReturn("Freeze Spell");

        boolean result = inventoryManager.removeItem(freezeSpell);

        assertFalse(result);
        assertTrue(inventoryManager.getInventory().isEmpty());
    }

    @Test
    public void getItemByName_ShouldReturnItem_WhenItemExists() {
        IItem freezeSpell = mock(IItem.class);
        when(freezeSpell.getName()).thenReturn("Freeze Spell");

        inventoryManager.addItem(freezeSpell);

        IItem retrievedItem = inventoryManager.getItemByName("Freeze Spell");

        assertEquals(freezeSpell, retrievedItem);
    }

    @Test
    public void getItemByName_ShouldReturnNull_WhenItemDoesNotExist() {
        IItem retrievedItem = inventoryManager.getItemByName("Nonexistent Item");

        assertNull(retrievedItem);
    }

    @Test
    public void getInventory_ShouldReturnCorrectSize_WhenItemsAreAdded() {
        IItem freezeSpell = mock(IItem.class);
        when(freezeSpell.getName()).thenReturn("Freeze Spell");

        inventoryManager.addItem(freezeSpell);

        int size = inventoryManager.getInventory().size();

        assertEquals(1, size);
    }
}