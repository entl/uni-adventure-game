package tests.unit;

import com.university.dungeon.room.RoomFactory;
import com.university.dungeon.room.Room;
import com.university.core.Difficulty;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.chests.IBox;
import com.university.utils.commands.Direction;
import com.university.utils.events.EventManager;

import java.util.ArrayList;
import java.util.List;

import static tests.ConfigTest.outputResult;

public class RoomFactoryTest {

    private static GameContext context;

    private static void setUp() {
        context = GameContext.initialize(Difficulty.EASY, EventManager.getInstance());
    }

    public static void testCreateRoomFromCell_Wall() {
        setUp();

        String testName = "RoomFactoryTest.testCreateRoomFromCell_Wall";
        Room room = RoomFactory.createRoomFromCell("W", "WallRoom");

        boolean result = room.isWall() && !room.isEntrance() && !room.isExit() && !room.isTreasureRoom();
        outputResult(result, testName);
    }

    public static void testCreateRoomFromCell_Entrance() {
        setUp();

        String testName = "RoomFactoryTest.testCreateRoomFromCell_Entrance";
        Room room = RoomFactory.createRoomFromCell("E", "EntranceRoom");

        boolean result = room.isEntrance() && !room.isWall() && !room.isExit() && !room.isTreasureRoom();
        outputResult(result, testName);
    }

    public static void testCreateRoomFromCell_Exit() {
        setUp();

        String testName = "RoomFactoryTest.testCreateRoomFromCell_Exit";
        Room room = RoomFactory.createRoomFromCell("X", "ExitRoom");

        boolean result = room.isExit() && !room.isWall() && !room.isEntrance() && !room.isTreasureRoom();
        outputResult(result, testName);
    }

    public static void testCreateRoomFromCell_Treasure() {
        setUp();

        String testName = "RoomFactoryTest.testCreateRoomFromCell_Treasure";
        Room room = RoomFactory.createRoomFromCell("T", "TreasureRoom");

        boolean result = room.isTreasureRoom() && !room.isWall() && !room.isEntrance() && !room.isExit();
        outputResult(result, testName);
    }

    public static void testCreateRoomFromCell_RegularRoomWithItemsAndTraps() {
        setUp();

        String testName = "RoomFactoryTest.testCreateRoomFromCell_RegularRoomWithItemsAndTraps";
        Room room = RoomFactory.createRoomFromCell("R", "RegularRoom");

        boolean hasTrap = room.getTrap() instanceof ITrap || room.getTrap() == null;
        boolean hasChest = room.getChest() instanceof IBox || room.getChest() == null;
        boolean hasItems = room.getItems() != null;

        boolean result = hasTrap && hasChest && hasItems && !room.isWall() && !room.isEntrance() && !room.isExit();
        outputResult(result, testName);
    }

    public static void testSetAdjacentRooms() {
        setUp();

        String testName = "RoomFactoryTest.testSetAdjacentRooms";

        // Creating a 2x2 dungeon layout
        List<List<Room>> rooms = new ArrayList<>();
        List<Room> row1 = new ArrayList<>();
        row1.add(RoomFactory.createRoomFromCell("E", "Room1")); // Entrance
        row1.add(RoomFactory.createRoomFromCell("R", "Room2")); // Regular
        List<Room> row2 = new ArrayList<>();
        row2.add(RoomFactory.createRoomFromCell("W", "Room3")); // Wall
        row2.add(RoomFactory.createRoomFromCell("X", "Room4")); // Exit
        rooms.add(row1);
        rooms.add(row2);

        // Set adjacent rooms
        RoomFactory.setAdjacentRooms(rooms);

        Room room1 = rooms.get(0).get(0);
        Room room2 = rooms.get(0).get(1);
        Room room3 = rooms.get(1).get(0);
        Room room4 = rooms.get(1).get(1);

        // Check adjacent rooms
        boolean result = room1.getAdjacentRooms().get(Direction.EAST) == room2
                && room2.getAdjacentRooms().get(Direction.WEST) == room1
                && room2.getAdjacentRooms().get(Direction.SOUTH) == room4
                && room4.getAdjacentRooms().get(Direction.NORTH) == room2
                && room3.getAdjacentRooms().get(Direction.EAST) == null; // Room3 is a wall

        outputResult(result, testName);
    }

    public static void runAllTests() {
        testCreateRoomFromCell_Wall();
        testCreateRoomFromCell_Entrance();
        testCreateRoomFromCell_Exit();
        testCreateRoomFromCell_Treasure();
        testCreateRoomFromCell_RegularRoomWithItemsAndTraps();
        testSetAdjacentRooms();
    }
}
