package functional;

import com.university.core.Difficulty;
import com.university.core.GameContext;
import com.university.dungeon.room.Room;
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
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ItemInteractionTest {

    private Player player;
    private Room room;
    private GameContext context;

    @Before
    public void setUp() {
        context = GameContext.initialize(Difficulty.EASY, mock(EventManager.class));
        player = new Player(Difficulty.EASY.getPowerPoints(), "Test Player");
        context.setPlayer(player);

        room = mock(Room.class);
        when(room.getLabel()).thenReturn("A1");
        player.setCurrentRoom(room);
    }

    @Test
    public void pickUpItem_ShouldSucceed_WhenItemExistsInRoom() {
        IItem teleportationSpell = mock(TeleportationSpell.class);
        when(teleportationSpell.getName()).thenReturn("Teleportation Spell");
        when(room.getItemByName("Teleportation Spell")).thenReturn(teleportationSpell);

        ICommand pickUpCommand = new PickUpCommand("Teleportation Spell");
        pickUpCommand.execute(context);

        assertEquals(teleportationSpell, player.getInventoryManager().getItemByName("Teleportation Spell"));
        verify(room).removeItem(teleportationSpell);
    }

    @Test
    public void dropItem_ShouldSucceed_WhenItemExistsInInventory() {
        IItem spanner = mock(Spanner.class);
        when(spanner.getName()).thenReturn("Spanner");
        player.getInventoryManager().addItem(spanner);

        ICommand dropCommand = new DropCommand("Spanner");
        dropCommand.execute(context);

        assertNull(player.getInventoryManager().getItemByName("Spanner"));
        verify(room).addItem(spanner);
    }

    @Test
    public void useItem_ShouldSucceed_WhenItemExistsInInventory() {
        IItem cake = mock(Cake.class);
        when(cake.getName()).thenReturn("cake");
        when(cake.getDisplayName()).thenReturn("cake");
        when(cake.isConsumable()).thenReturn(true);

        player.getInventoryManager().addItem(cake);

        ICommand useCommand = new UseCommand("cake");
        useCommand.execute(context);

        assertEquals(0, player.getInventoryManager().getInventory().size());
    }
}