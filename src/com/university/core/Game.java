package com.university.core;

import com.university.config.Config;
import com.university.dungeon.Dungeon;
import com.university.dungeon.DungeonService;
import com.university.dungeon.room.Room;
import com.university.player.Player;
import com.university.utils.ui.GameNarrator;
import com.university.utils.ui.UI;
import com.university.utils.ui.UIManager;
import com.university.utils.commands.ICommand;
import com.university.utils.commands.WakeUpCommand;
import com.university.utils.events.EventManager;
import com.university.utils.parsers.CommandParser;
import com.university.utils.ui.ConsoleUI;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.util.Scanner;

/**
 * The {@code Game} class serves as the central controller for the game.
 * It initializes the game, manages player interactions, game state,
 * and transitions between different levels of the dungeon
 *
 * <p>This class follows the Singleton pattern</p>
 *
 * <p>Main responsibilities include:
 * <ul>
 * <li>Initializing game components like dungeons, players, and UI</li>
 * <li>Managing the main game loop</li>
 * <li>Handling game state transitions like winning, losing, or moving between levels</li>
 * <li>Processing player commands and actions</li>
 * </ul>
 * </p>
 */
public class Game {
    /**
     * Logger for the Game class to log events and errors
     */
    private static final ILogger logger = LoggerFactory.getLogger(Game.class);

    /**
     * Singleton instance of the Game class
     */
    private static Game instance;

    /**
     * Paths to dungeon configuration files.
     */
    private final String[] dungeonPaths = Config.dungeonPaths;

    /**
     * Array of dungeons in the game
     */
    private Dungeon[] dungeons;

    /**
     * Current game context storing the player, difficulty, and game state
     */
    private GameContext gameContext;

    /**
     * Parser for processing user commands
     */
    private CommandParser commandParser;

    /**
     * Scanner for reading user input
     */
    private Scanner scanner;

    /**
     * User interface instance for displaying messages and interacting with the player.
     */
    private UI ui;

    /**
     * Private constructor to enforce Singleton pattern
     */
    private Game() {}

    /**
     * Retrieves the Singleton instance of the {@code Game} class.
     * If no instance exists, it initializes one and sets up the game.
     *
     * @return The Singleton instance of the Game class.
     */
    public static Game initialize() {
        if (instance == null) {
            instance = new Game();
            instance.initializeGame();
        }
        return instance;
    }

    /**
     * Initializes the game by setting up input, UI, game context,
     * dungeons, and the player
     */
    private void initializeGame() {
        logger.info("Initializing game...");
        setupInput();
        Difficulty difficulty = selectDifficulty();
        setupGameContext(difficulty);
        initializeDungeons();
        setupUI();
        Player player = setupPlayer(difficulty);
        setInitialGameState(player);
        logger.info("Game initialized successfully.");
    }

    /**
     * Sets up the input and command parser for the game
     */
    private void setupInput() {
        logger.debug("Setting up input and command parser.");
        scanner = new Scanner(System.in);
        commandParser = new CommandParser();
    }

    /**
     * Sets up the user interface for the game
     */
    private void setupUI() {
        logger.debug("Setting up UI.");
        ui = UIManager.getInstance();
    }

    /**
     * Sets up the game context, including difficulty and event management
     *
     * @param difficulty The selected difficulty level for the game
     */
    private void setupGameContext(Difficulty difficulty) {
        logger.info("Setting up game context with difficulty: " + difficulty);
        EventManager eventManager = EventManager.getInstance();
        gameContext = GameContext.initialize(difficulty, eventManager);
        UIManager.getInstance().displayMessage("* Game initialized with difficulty: " + difficulty);
    }

    /**
     * Initializes dungeons from configuration files
     */
    private void initializeDungeons() {
        logger.info("Initializing dungeons...");
        DungeonService dungeonService = new DungeonService();
        dungeons = new Dungeon[dungeonPaths.length];
        for (int i = 0; i < dungeonPaths.length; i++) {
            logger.debug("Initializing dungeon from file: " + dungeonPaths[i]);
            dungeons[i] = dungeonService.initializeDungeon(dungeonPaths[i], i + 1);
            logger.info("Dungeon " + (i + 1) + " initialized successfully.");
        }
    }

    /**
     * Creates and sets up the player for the game based on the selected difficulty
     *
     * @param difficulty The selected difficulty level for the game.
     * @return The initialized {@code Player} object
     */
    private Player setupPlayer(Difficulty difficulty) {
        logger.info("Setting up player with initial power points: " + difficulty.getPowerPoints());
        Player player = new Player(difficulty.getPowerPoints());
        gameContext.setPlayer(player);
        return player;
    }

    /**
     * Sets the initial state of the game, including the current dungeon and room.
     *
     * @param player The player object for the game.
     */
    private void setInitialGameState(Player player) {
        logger.info("Setting initial game state.");
        gameContext.setCurrentDungeon(dungeons[0]);
        player.setCurrentRoom(gameContext.getCurrentDungeon().getEntranceRoom());
        player.getCurrentRoom().setVisited(true);
    }

    /**
     * Prompts the player to select a difficulty level
     *
     * @return The selected {@code Difficulty} object
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
                    logger.info("Player selected difficulty: Easy");
                    return Difficulty.EASY;
                case "2":
                    UIManager.getInstance().displayMessage("* Difficulty set to Medium.");
                    logger.info("Player selected difficulty: Medium");
                    return Difficulty.MEDIUM;
                case "3":
                    UIManager.getInstance().displayMessage("* Difficulty set to Hard.");
                    logger.info("Player selected difficulty: Hard");
                    return Difficulty.HARD;
                default:
                    UIManager.getInstance().displayMessage("* Invalid choice. Please enter 1, 2, or 3.");
                    logger.warning("Invalid difficulty choice: " + input);
            }
        }
    }

    /**
     * Starts the game
     */
    public void startGame() {
        logger.info("Game started.");
        displayIntro();
        loop();
    }

    /**
     * Displays the game introduction and story to the player
     */
    private void displayIntro() {
        logger.debug("Displaying game introduction.");
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
     * The main game loop that manages player actions and transitions between game states
     */
    public void loop() {
        logger.info("Entering main game loop.");
        while (gameContext.isRunning()) {
            handleGameState();
            if (!gameContext.isRunning()) break;
            handlePlayerSleep();
            waitForPlayerInput();
            processPlayerCommand();
        }
        endGame();
        scanner.close();
    }

    /**
     * Handles the current game state, including checking win/loss conditions and traps
     */
    private void handleGameState() {
        logger.debug("Handling game state.");
        Player player = gameContext.getPlayer();
        Room currentRoom = player.getCurrentRoom();

        if (player.getPowerPoints() <= 0) {
            logger.warning("Player has run out of power points. Game over.");
            gameContext.setGameLost(true);
            gameContext.setRunning(false);
        } else if (currentRoom.isTreasureRoom()) {
            logger.info("Player has reached the treasure room. Game won.");
            gameContext.setGameWon(true);
            gameContext.setRunning(false);
        } else if (currentRoom.getTrap() != null && currentRoom.getTrap().isActive()) {
            logger.info("Player encountered an active trap in room: " + currentRoom.getLabel());
            if (!player.isTrapped()) {
                currentRoom.getTrap().printDescription();
            }
            currentRoom.getTrap().activate(gameContext);
        } else if (currentRoom.isExit()) {
            logger.info("Player reached an exit room. Advancing to the next dungeon.");
            advanceToNextDungeon();
        }
    }

    /**
     * Handles the player sleep state
     */
    private void handlePlayerSleep() {
        Player player = gameContext.getPlayer();
        if (player.isAsleep()) {
            logger.info("Player is sleeping...");
            try {
                UIManager.getInstance().displayMessage("* Sleeping...");
                Thread.sleep(3000);
                new WakeUpCommand().execute(gameContext);
                logger.info("Player woke up.");
            } catch (InterruptedException e) {
                logger.error("Sleep interrupted.", e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Waits for player input
     */
    private void waitForPlayerInput() {
        logger.debug("Waiting for player input.");
        UIManager.getInstance().displayMessage("\n* Press Enter to continue...");
        scanner.nextLine();
        new ConsoleUI().clearScreen();
    }

    /**
     * Processes a command entered by the player and executes it
     */
    private void processPlayerCommand() {
        logger.debug("Processing player command.");
        UIManager.getInstance().displayMessage("* What would you like to do?");
        UIManager.getInstance().displayInline("> ");
        String userInput = scanner.nextLine();
        UIManager.getInstance().displayMessage("");

        ICommand command = commandParser.parse(userInput);
        if (command != null) {
            command.execute(gameContext);
        } else {
            logger.warning("Invalid or unrecognized command: " + userInput);
        }
    }

    /**
     * Ends the game and displays the win or loss message
     */
    private void endGame() {
        if (gameContext.isGameWon()) {
            logger.info("Game ended. Player won.");
            UIManager.getInstance().displayMessage(GameNarrator.winMessage());
            UIManager.getInstance().displayMessage(String.format("* You have won the game with %d power points! Congratulations!\n", gameContext.getPlayer().getPowerPoints()));
        } else if (gameContext.isGameLost()) {
            logger.info("Game ended. Player lost.");
            UIManager.getInstance().displayMessage(GameNarrator.loseMessage());
        }
    }

    /**
     * Advances the player to the next dungeon level when they reach exit
     */
    private void advanceToNextDungeon() {
        logger.debug("Advancing to the next dungeon.");
        Dungeon currentDungeon = gameContext.getCurrentDungeon();
        int currentDungeonIndex = currentDungeon.getLevel() - 1;
        Dungeon nextDungeon = dungeons[currentDungeonIndex + 1];
        gameContext.setCurrentDungeon(nextDungeon);
        currentDungeon = nextDungeon;
        gameContext.getPlayer().setCurrentRoom(currentDungeon.getEntranceRoom());
        UIManager.getInstance().displayMessage(GameNarrator.proceedNextLevel(currentDungeon.getLevel()));
    }
}