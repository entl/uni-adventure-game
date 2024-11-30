package com.university.game;

import com.university.Config;
import com.university.dungeon.Dungeon;
import com.university.dungeon.DungeonService;
import com.university.dungeon.room.Room;
import com.university.player.Player;
import com.university.utils.UI.GameNarrator;
import com.university.utils.UI.UI;
import com.university.utils.UI.UIManager;
import com.university.utils.commands.ICommand;
import com.university.utils.commands.WakeUpCommand;
import com.university.utils.events.EventManager;
import com.university.utils.parsers.CommandParser;
import com.university.utils.UI.ConsoleUI;

import java.util.Scanner;

/**
 * The Game class represents the main entry point of the game. It manages game state,
 * player interactions, and transitions between different game loops and dungeons.
 * This class follows a Singleton pattern to ensure only one instance of the game is active at any time.
 */
public class Game {
    private static Game instance;   // Singleton instance to ensure only one game is running at a time
    private final String[] dungeonPaths = Config.dungeonPaths;  // Paths to dungeon files
    private Dungeon[] dungeons;     // Array of dungeons in the game
    private GameContext gameContext;    // Context of the current game state
    private CommandParser commandParser;    // Parser for user commands
    private Scanner scanner;
    private UI ui;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private Game() {}

    /**
     * Retrieves the singleton instance of the Game class.
     * If no instance exists, it creates one and initializes the game.
     *
     * @return The singleton instance of the Game class.
     */
    public static Game initialize() {
        if (instance == null) {
            instance = new Game();
            instance.initializeGame();
        }
        return instance;
    }

    /**
     * Initializes the game by creating dungeons, setting up the player, and loading game context.
     */
    private void initializeGame() {
        setupInput();
        Difficulty difficulty = selectDifficulty();

        setupGameContext(difficulty);
        initializeDungeons();

        Player player = setupPlayer(difficulty);
        setInitialGameState(player);
    }

    private void setupInput() {
        scanner = new Scanner(System.in);
        commandParser = new CommandParser();
    }

    private void setupUI() {
        ui = UIManager.getInstance();
    }

    private void setupGameContext(Difficulty difficulty) {
        EventManager eventManager = EventManager.getInstance();
        gameContext = GameContext.initialize(difficulty, eventManager);
        UIManager.getInstance().displayMessage("* Game initialized with difficulty: " + difficulty);
    }

    private void initializeDungeons() {
        DungeonService dungeonService = new DungeonService();
        dungeons = new Dungeon[dungeonPaths.length];
        for (int i = 0; i < dungeonPaths.length; i++) {
            dungeons[i] = dungeonService.initializeDungeon(dungeonPaths[i], i + 1);
        }
    }

    private Player setupPlayer(Difficulty difficulty) {
        Player player = new Player(difficulty.getPowerPoints());
        gameContext.setPlayer(player);
        return player;
    }

    private void setInitialGameState(Player player) {
        gameContext.setCurrentDungeon(dungeons[0]);
        player.setCurrentRoom(gameContext.getCurrentDungeon().getEntranceRoom());
        player.getCurrentRoom().setVisited(true);
    }

    /**
     * Prompts the user to select a difficulty level.
     */
    private Difficulty selectDifficulty() {
        UIManager.getInstance().displayMessage("* Select difficulty level:");
        UIManager.getInstance().displayMessage("1. Easy");
        UIManager.getInstance().displayMessage("2. Medium");
        UIManager.getInstance().displayMessage("3. Hard");

        while (true) {
            UIManager.getInstance().displayInline("Enter your choice (1-3): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    UIManager.getInstance().displayMessage("* Difficulty set to Easy.");
                    return Difficulty.EASY;
                case "2":
                    UIManager.getInstance().displayMessage("* Difficulty set to Medium.");
                    return Difficulty.MEDIUM;
                case "3":
                    UIManager.getInstance().displayMessage("* Difficulty set to Hard.");
                    return Difficulty.HARD;
                default:
                    UIManager.getInstance().displayMessage("* Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    /**
     * Starts the game and presents the introduction to the player.
     */
    public void startGame() {
        displayIntro();

        loop();
    }

    private void displayIntro() {
        String startingArt = """
                ======================================
                
                oooooooooo.    .o"o.   oooooooooo.  \s
                `888'   `Y8b  "' "  `" `888'   `Y8b \s
                 888      888    "      888      888\s
                 888      888    "      888      888\s
                 888      888    "      888      888\s
                 888     d88'    "      888     d88'\s
                o888bood8P'      "     o888bood8P'  \s
                
                ======================================
                """;
        UIManager.getInstance().displayMessage(startingArt);
        UIManager.getInstance().displayMessage("* Welcome to the game `Dungeons NAND Dragons!`");

        UIManager.getInstance().displayMessage("* Press Enter to start the game...");
        scanner.nextLine();

        UIManager.getInstance().displayMessage(
                """
                * Year: 1347.\s
                * The Black Death devastates Europe, claiming lives without mercy.\s
                * In your small village, your wife has fallen ill with the plague.\s
                * Desperate and out of options, you hear rumors of a *legendary elixir* hidden deep within a dangerous dungeon.
                * Armed with nothing but your will to save her, you determined to go on a dangerous adventure into the dark ancient dungeon.\s
                * Time is running out.\s
                * Will you find the cure before it's too late, or will the dungeon claim you, as it has so many before?
                """);

        UIManager.getInstance().displayMessage("* Press Enter to begin your journey...");
        UIManager.getInstance().displayMessage("* Type `help` to see the list of available commands.");
        scanner.nextLine();

        UIManager.getInstance().displayMessage("* You step into the dungeon, the air growing cold and moisture around you...");
        UIManager.getInstance().displayMessage("* As you turn to look back, the entrance slams shut, sealing you inside. There is no way out.");
    }

    /**
     * The main game loop that handles user interaction, command execution, and state transitions.
     */
    public void loop() {
        while (gameContext.isRunning()) {
            handleGameState();

            if (!gameContext.isRunning()) {
                break;
            }

            handlePlayerSleep();
            waitForPlayerInput();

            processPlayerCommand();
        }

        endGame();
        scanner.close();
    }

    private void handleGameState() {
        Player player = gameContext.getPlayer();
        Room currentRoom = player.getCurrentRoom();

        if (player.getPowerPoints() <= 0) {
            gameContext.setGameLost(true);
            gameContext.setRunning(false);
        } else if (currentRoom.isTreasureRoom()) {
            gameContext.setGameWon(true);
            gameContext.setRunning(false);
        } else if (currentRoom.getTrap() != null && currentRoom.getTrap().isActive()) {
            if (!player.isTrapped()) {
                currentRoom.getTrap().printDescription();
            }
            currentRoom.getTrap().activate(gameContext);
        } else if (currentRoom.isExit()) {
            advanceToNextDungeon();
        }
    }

    private void handlePlayerSleep() {
        Player player = gameContext.getPlayer();
        if (player.isAsleep()) {
            try {
                UIManager.getInstance().displayMessage("* Sleeping...");
                Thread.sleep(3000);
                new WakeUpCommand().execute(gameContext);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void waitForPlayerInput() {
        UIManager.getInstance().displayMessage("\n* Press Enter to continue...");
        scanner.nextLine();
        new ConsoleUI().clearScreen();
    }

    private void processPlayerCommand() {
        UIManager.getInstance().displayMessage("* What would you like to do?");
        UIManager.getInstance().displayInline("> ");
        String userInput = scanner.nextLine();
        UIManager.getInstance().displayMessage("");

        ICommand command = commandParser.parse(userInput);
        if (command != null) {
            command.execute(gameContext);
        }
    }

    /**
     * Ends the game and displays the result to the player.
     */
    private void endGame() {
        if (gameContext.isGameWon()) {
            UIManager.getInstance().displayMessage(GameNarrator.winMessage());
            UIManager.getInstance().displayMessage(String.format("* You have won the game with %d power points! Congratulations!\n", gameContext.getPlayer().getPowerPoints()));
        } else if (gameContext.isGameLost()) {
            UIManager.getInstance().displayMessage(GameNarrator.loseMessage());
        }
    }

    /**
     * Advances the player to the next dungeon level if they reach an exit room.
     */
    private void advanceToNextDungeon() {
        Dungeon currentDungeon = gameContext.getCurrentDungeon();
        int currentDungeonIndex = currentDungeon.getLevel() - 1;
        Dungeon nextDungeon = dungeons[currentDungeonIndex + 1];
        gameContext.setCurrentDungeon(nextDungeon);
        currentDungeon = nextDungeon;
        gameContext.getPlayer().setCurrentRoom(currentDungeon.getEntranceRoom());
        UIManager.getInstance().displayMessage(GameNarrator.proceedNextLevel(currentDungeon.getLevel()));
    }
}