package tests.functional;

import com.university.dungeon.room.Room;
import com.university.core.Difficulty;
import com.university.core.GameContext;
import com.university.elements.items.Cake;
import com.university.elements.items.IItem;
import com.university.elements.items.Spanner;
import com.university.elements.items.TeleportationSpell;
import com.university.player.Player;
import com.university.utils.commands.DropCommand;
import com.university.utils.commands.ICommand;
import com.university.utils.commands.PickUpCommand;
import com.university.utils.commands.UseCommand;
import com.university.utils.events.EventManager;

import static tests.ConfigTest.outputResult;

public class ItemInteractionTest {
    private static Player player;
    private static Room room;
    private static GameContext context;

    private static void setUp() {
        context = GameContext.initialize(Difficulty.EASY, EventManager.getInstance());
        player = new Player(Difficulty.EASY.getPowerPoints());
        context.setPlayer(player);

        room = new Room("A1", false, false, false, false, null, null);
        player.setCurrentRoom(room);
    }

    public static void testPickUpItem() {
        setUp();
        String testName = "ItemInteractionTest.testPickUpItem";

        IItem item = new TeleportationSpell();
        room.addItem(item); // add item for test

        ICommand pickUpCommand = new PickUpCommand(item.getName());
        pickUpCommand.execute(context);

        boolean result = player.getInventoryManager().getItemByName(item.getName()) == item;
        outputResult(result, testName);
    }

    public static void testDropItem() {
        setUp();
        String testName = "ItemInteractionTest.testDropItem";

        IItem item = new Spanner();
        player.getInventoryManager().addItem(item);

        ICommand dropCommand = new DropCommand(item.getName());
        dropCommand.execute(context);

        boolean result = player.getInventoryManager().getInventory().isEmpty();
        outputResult(result, testName);
    }

    public static void testUseItem() {
        setUp();
        String testName = "ItemInteractionTest.testUseItem";

        IItem item = new Cake();
        player.getInventoryManager().addItem(item);

        ICommand useCommand = new UseCommand(item.getName());
        useCommand.execute(context);

        boolean result = player.getInventoryManager().getInventory().isEmpty() && player.getPowerPoints() == 103;
        outputResult(result, testName);
    }

    public static void runAllTests() {
        testPickUpItem();
        testDropItem();
        testUseItem();
    }
}
