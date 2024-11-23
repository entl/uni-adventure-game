package tests.e2e;

import com.university.dungeon.Dungeon;
import com.university.dungeon.room.Room;
import com.university.game.Difficulty;
import com.university.game.Game;
import com.university.game.GameContext;
import com.university.items.Cake;
import com.university.utils.commands.Direction;
import com.university.utils.commands.MoveCommand;
import com.university.utils.commands.PickUpCommand;
import com.university.utils.commands.UseCommand;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import static tests.ConfigTest.outputResult;

public class GamePlayTest {
    private GameContext gameContext;
    private Game game;

    private void setup() throws IOException {
        game = Game.initialize();
        gameContext = GameContext.initialize();
    }

    public void testGamePlay() throws IOException {
        String testName = "GamePlayTest.testGamePlay";

        // select difficulty
        mockUserInput("2\n");
        setup();

        boolean result = gameContext.getDifficulty() == Difficulty.MEDIUM;
        outputResult(result, testName + " - select difficulty");

        // test move
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        new MoveCommand(Direction.NORTH).execute(gameContext);

        result = !currentRoom.equals(gameContext.getPlayer().getCurrentRoom());
        outputResult(result, testName + " - move to next room");

        // test add cake to room
        currentRoom = gameContext.getPlayer().getCurrentRoom();
        currentRoom.setItems(new ArrayList<>(Arrays.asList(new Cake())));

        result = currentRoom.getItems().size() == 1;
        outputResult(result, testName + " - add cake to room");

        // test pick up cake
        new PickUpCommand("cake").execute(gameContext);
        result = gameContext.getPlayer().getInventoryManager().getItemByName("cake") != null;
        outputResult(result, testName + " - pick up cake");

        // use cake
        int powerPoints = gameContext.getPlayer().getPowerPoints();
        new UseCommand("cake").execute(gameContext);

        result = gameContext.getPlayer().getPowerPoints() > powerPoints;
        outputResult(result, testName + " - use cake");

        // advance to next level
        Dungeon currentDungeon = gameContext.getCurrentDungeon();
        Class<?> gameClass = game.getClass();
        try {
            Method advanceMethod = gameClass.getDeclaredMethod("advanceToNextDungeon");
            advanceMethod.setAccessible(true);
            advanceMethod.invoke(game);

            result = !currentDungeon.equals(gameContext.getCurrentDungeon());
            outputResult(result, testName + " - advance to next level");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            outputResult(false, testName + " - advance to next level");
        }

        // test user wins
        Room winRoom =new Room("Win Room", false, false, true, false, null, null);
        gameContext.getPlayer().setCurrentRoom(winRoom);

        try {
            Method handleGameState = gameClass.getDeclaredMethod("handleGameState");
            handleGameState.setAccessible(true);
            handleGameState.invoke(game);

            result = gameContext.isGameWon();
            outputResult(result, testName + " - user wins");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException  e) {
            outputResult(false, testName + " - user wins");
        }
    }

    public void runAllTests() throws IOException {
        testGamePlay();
    }

    private void mockUserInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
