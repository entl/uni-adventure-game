package functional;

import com.university.dungeon.room.Room;
import com.university.core.Difficulty;
import com.university.core.GameContext;
import com.university.elements.traps.MadScientists;
import com.university.elements.traps.strategies.IEscapeStrategy;
import com.university.elements.traps.strategies.LosePointsStrategy;
import com.university.elements.items.FreezeSpell;
import com.university.elements.items.IItem;
import com.university.player.Player;
import com.university.utils.events.EventManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrapInteractionTest {

    private Player player;
    private Room room;
    private GameContext context;

    @Before
    public void setUp() {
        context = GameContext.initialize(Difficulty.EASY, EventManager.getInstance());
        player = new Player(Difficulty.EASY.getPowerPoints(), "Test Player");
        context.setPlayer(player);

        room = new Room("A1", false, false, false, false, new MadScientists(), null);
        player.setCurrentRoom(room);
    }

    @Test
    public void activateTrap_ShouldSetPlayerTrapped_WhenTrapIsActivated() {
        room.getTrap().activate(context);

        assertTrue(player.isTrapped());
    }

    @Test
    public void escapeTrapWithPoints_ShouldFreePlayer_WhenUsingLosePointsStrategy() {
        IEscapeStrategy escapeStrategy = new LosePointsStrategy(10);

        room.getTrap().activate(context);
        room.getTrap().escape(context, escapeStrategy);

        assertFalse(player.isTrapped());
        assertEquals(90, player.getPowerPoints());
    }

    @Test
    public void escapeTrapWithItem_ShouldFreePlayer_WhenUsingFreezeSpell() {
        IItem freezeSpell = new FreezeSpell();


        player.getInventoryManager().addItem(freezeSpell);
        room.getTrap().activate(context);
        freezeSpell.use(context);

        assertFalse(player.isTrapped());
        assertNull(player.getInventoryManager().getItemByName("Freeze Spell"));
    }
}