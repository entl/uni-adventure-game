package tests.unit;

import com.university.items.FreezeSpell;
import com.university.items.IItem;
import com.university.player.inventory.InventoryManager;

import static tests.ConfigTest.outputResult;

public class InventoryManagerTest {

    private static InventoryManager inventoryManager;

    // new instance of InventoryManager should be created for each test to ensure isolation
    public static void setUp() {
        inventoryManager = new InventoryManager();
    }

    public static void testAddItem_Success() {
        setUp();
        String testName = "InventoryManagerTest.testAddItem_Success";

        IItem freezeSpell = new FreezeSpell();
        boolean addItemResult = inventoryManager.addItem(freezeSpell);

        // ideally, we should mock the item and method getItemsByName to test this method
        // since unit imply to test a single unit of code
        // but in plain java it would be too complex to mock the item and method
        boolean result = addItemResult && inventoryManager.getItemByName(freezeSpell.getName()) == freezeSpell;
        outputResult(result, testName);
    }

    public static void testAddItem_ExceedLimit() {
        setUp();
        String testName = "InventoryManagerTest.testAddItem_ExceedLimit";

        // add spells until the limit is reached
        inventoryManager.addItem(new FreezeSpell());
        inventoryManager.addItem(new FreezeSpell());

        // try adding another spell which should fail
        IItem extraSpell = new FreezeSpell();
        boolean addItemResult = inventoryManager.addItem(extraSpell);

        // check that last item was not added and inventory size is still 2
        boolean result = !addItemResult && inventoryManager.getInventory().size() == 2;
        outputResult(result, testName);
    }

    public static void testRemoveItem() {
        setUp();
        String testName = "InventoryManagerTest.testRemoveItem";

        IItem freezeSpell = new FreezeSpell();
        inventoryManager.addItem(freezeSpell);

        boolean removeItemResult = inventoryManager.removeItem(freezeSpell);
        boolean result = removeItemResult && inventoryManager.getItemByName(freezeSpell.getName()) == null;
        outputResult(result, testName);
    }

    public static void testRemoveItem_NotInInventory() {
        setUp();
        String testName = "InventoryManagerTest.testRemoveItem_NotInInventory";

        IItem freezeSpell = new FreezeSpell();
        boolean removeItemResult = inventoryManager.removeItem(freezeSpell);

        boolean result = !removeItemResult && inventoryManager.getInventory().isEmpty();
        outputResult(result, testName);
    }

    public static void testGetItemByName_ItemExists() {
        setUp();
        String testName = "InventoryManagerTest.testGetItemByName_ItemExists";

        IItem freezeSpell = new FreezeSpell();
        inventoryManager.addItem(freezeSpell);

        boolean result = inventoryManager.getItemByName(freezeSpell.getName()) == freezeSpell;
        outputResult(result, testName);
    }

    public static void testGetItemByName_ItemNotExists() {
        setUp();
        String testName = "InventoryManagerTest.testGetItemByName_ItemNotExists";

        boolean result = inventoryManager.getItemByName("AnotherItem") == null;
        outputResult(result, testName);
    }

    public static void testGetInventory() {
        setUp();
        String testName = "InventoryManagerTest.testGetInventory";

        IItem freezeSpell = new FreezeSpell();
        inventoryManager.addItem(freezeSpell);

        boolean result = inventoryManager.getInventory().size() == 1;
        outputResult(result, testName);
    }

    public static void runAllTests() {
        testAddItem_Success();
        testAddItem_ExceedLimit();
        testRemoveItem();
        testRemoveItem_NotInInventory();
        testGetItemByName_ItemExists();
        testGetItemByName_ItemNotExists();
        testGetInventory();
    }
}
