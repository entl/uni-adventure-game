package unit;

import com.university.core.Difficulty;
import com.university.core.GameContext;
import com.university.dungeon.room.RoomFactory;
import com.university.dungeon.room.Room;
import com.university.utils.commands.Direction;
import com.university.utils.events.EventManager;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RoomFactoryTest {

    private static GameContext context;

    @Before
    public void setUp() {
        context = GameContext.initialize(Difficulty.EASY, EventManager.getInstance());
    }

    @Test
    public void createRoomFromCell_ShouldCreateWallRoom_WhenInputIsWallCell() {
        Room room = RoomFactory.createRoomFromCell("W", "WallRoom");
        assertTrue(room.isWall());
        assertFalse(room.isEntrance());
        assertFalse(room.isExit());
        assertFalse(room.isTreasureRoom());
    }

    @Test
    public void createRoomFromCell_ShouldCreateEntranceRoom_WhenInputIsEntranceCell() {
        Room room = RoomFactory.createRoomFromCell("E", "EntranceRoom");
        assertTrue(room.isEntrance());
        assertFalse(room.isWall());
        assertFalse(room.isExit());
        assertFalse(room.isTreasureRoom());
    }

    @Test
    public void createRoomFromCell_ShouldCreateExitRoom_WhenInputIsExitCell() {
        Room room = RoomFactory.createRoomFromCell("X", "ExitRoom");
        assertTrue(room.isExit());
        assertFalse(room.isWall());
        assertFalse(room.isEntrance());
        assertFalse(room.isTreasureRoom());
    }

    @Test
    public void createRoomFromCell_ShouldCreateTreasureRoom_WhenInputIsTreasureCell() {
        Room room = RoomFactory.createRoomFromCell("T", "TreasureRoom");
        assertTrue(room.isTreasureRoom());
        assertFalse(room.isWall());
        assertFalse(room.isEntrance());
        assertFalse(room.isExit());
    }

    @Test
    public void createRoomFromCell_ShouldCreateRegularRoom_WhenInputIsRegularCell() {
        Room room = RoomFactory.createRoomFromCell("R", "RegularRoom");
        assertNotNull(room.getItems());
        assertFalse(room.isWall());
        assertFalse(room.isEntrance());
        assertFalse(room.isExit());
        assertFalse(room.isTreasureRoom());
    }

    @Test
    public void setAdjacentRooms_ShouldSetCorrectAdjacency_WhenRoomsAreProvided() {
        List<List<Room>> rooms = new ArrayList<>();
        List<Room> row1 = new ArrayList<>();
        row1.add(RoomFactory.createRoomFromCell("E", "Room1"));
        row1.add(RoomFactory.createRoomFromCell("R", "Room2"));
        List<Room> row2 = new ArrayList<>();
        row2.add(RoomFactory.createRoomFromCell("W", "Room3"));
        row2.add(RoomFactory.createRoomFromCell("X", "Room4"));
        rooms.add(row1);
        rooms.add(row2);

        RoomFactory.setAdjacentRooms(rooms);

        Room room1 = rooms.get(0).get(0);
        Room room2 = rooms.get(0).get(1);
        Room room3 = rooms.get(1).get(0);
        Room room4 = rooms.get(1).get(1);

        assertEquals(room2, room1.getAdjacentRooms().get(Direction.EAST));
        assertEquals(room1, room2.getAdjacentRooms().get(Direction.WEST));
        assertEquals(room4, room2.getAdjacentRooms().get(Direction.SOUTH));
        assertEquals(room2, room4.getAdjacentRooms().get(Direction.NORTH));
        assertNull(room3.getAdjacentRooms().get(Direction.EAST));
    }
}