package tests.functional;

import com.university.dungeon.room.Room;
import com.university.game.Difficulty;
import com.university.game.GameContext;
import com.university.gameElements.traps.MadScientists;
import com.university.gameElements.traps.strategies.IEscapeStrategy;
import com.university.gameElements.traps.strategies.LosePointsStrategy;
import com.university.items.FreezeSpell;
import com.university.items.IItem;
import com.university.player.Player;

import static tests.ConfigTest.outputResult;

public class TrapInteractionTest {
    private static Player player;
    private static Room room;
    private static GameContext context;

    private static void setUp() {
        context = GameContext.initialize(Difficulty.EASY);
        player = new Player(Difficulty.EASY.getPowerPoints());
        context.setPlayer(player);

        room = new Room("A1", false, false, false, false, new MadScientists(), null);
        player.setCurrentRoom(room);
    }

    public static void activateTrap() {
        setUp();
        String testName = "TrapInteractionTest.activateTrap";

        room.getTrap().activate(context);

        boolean result = player.isTrapped();
        outputResult(result, testName);
    }

    public static void escapeTrapWithPoints() {
        setUp();
        String testName = "TrapInteractionTest.escapeTrapWithPoints";
        IEscapeStrategy escapeStrategy = new LosePointsStrategy(10);

        room.getTrap().activate(context);
        room.getTrap().escape(context, escapeStrategy);

        boolean result = !player.isTrapped() && player.getPowerPoints() == 90;
        outputResult(result, testName);
    }

    public static void escapeTrapWithItem() {
        setUp();
        String testName = "TrapInteractionTest.escapeTrapWithoutPoints";
        IItem item = new FreezeSpell();

        room.getTrap().activate(context);
        item.use(context);

        boolean result = !player.isTrapped() && player.getPowerPoints() == 100 && player.getInventoryManager().getItemByName(item.getName()) == null;
        outputResult(result, testName);
    }

    public static void runAllTests() {
        activateTrap();
        escapeTrapWithPoints();
        escapeTrapWithItem();
    }
}
