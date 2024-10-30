package com.university.game;

import com.university.Config;
import com.university.dungeon.Dungeon;
import com.university.dungeon.DungeonService;
import com.university.dungeon.room.Room;
import com.university.player.Player;
import com.university.utils.ClearScreen;
import com.university.utils.commands.ICommand;
import com.university.utils.commands.WakeUpCommand;
import com.university.utils.parsers.CommandParser;

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

    /**
     * Private constructor to prevent direct instantiation.
     */
    private Game() {
    }

    /**
     * Retrieves the singleton instance of the Game class.
     * If no instance exists, it creates one and initializes the game.
     *
     * @return The singleton instance of the Game class.
     */
    public static Game getInstance() {
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
        scanner = new Scanner(System.in);
        commandParser = new CommandParser();

        Difficulty difficulty = selectDifficulty();

        gameContext = GameContext.getInstance(difficulty);
        System.out.println("* Game initialized with difficulty: " + difficulty);
        Player player = new Player(difficulty.getPowerPoints());

        DungeonService dungeonService = new DungeonService();
        dungeons = new Dungeon[dungeonPaths.length];
        for (int i = 0; i < dungeonPaths.length; i++) {
            dungeons[i] = dungeonService.initializeDungeon(dungeonPaths[i], i + 1);
        }

        gameContext.setPlayer(player);
        gameContext.setCurrentDungeon(dungeons[0]);
        player.setCurrentRoom(gameContext.getCurrentDungeon().getEntranceRoom());
        player.getCurrentRoom().setVisited(true);
    }

    /**
     * Prompts the user to select a difficulty level.
     */
    private Difficulty selectDifficulty() {
        System.out.println("* Select difficulty level:");
        System.out.println("1. Easy");
        System.out.println("2. Medium");
        System.out.println("3. Hard");

        while (true) {
            System.out.print("Enter your choice (1-3): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("* Difficulty set to Easy.");
                    return Difficulty.EASY;
                case "2":
                    System.out.println("* Difficulty set to Medium.");
                    return Difficulty.MEDIUM;
                case "3":
                    System.out.println("* Difficulty set to Hard.");
                    return Difficulty.HARD;
                default:
                    System.out.println("* Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    /**
     * Starts the game and presents the introduction to the player.
     */
    public void startGame() {
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
        System.out.println(startingArt);
        System.out.println("* Welcome to the game `Dungeons NAND Dragons!`");

        System.out.println("* Press Enter to start the game...");
        scanner.nextLine();

        System.out.println(
                """
                * Year: 1347.\s
                * The Black Death devastates Europe, claiming lives without mercy.\s
                * In your small village, your wife has fallen ill with the plague.\s
                * Desperate and out of options, you hear rumors of a *legendary elixir* hidden deep within a dangerous dungeon.
                * Armed with nothing but your will to save her, you determined to go on a dangerous adventure into the dark ancient dungeon.\s
                * Time is running out.\s
                * Will you find the cure before it's too late, or will the dungeon claim you, as it has so many before?
                """);

        System.out.println("* Press Enter to begin your journey...");
        System.out.println("* Type `help` to see the list of available commands.");
        scanner.nextLine();

        System.out.println("* You step into the dungeon, the air growing cold and moisture around you...");
        System.out.println("* As you turn to look back, the entrance slams shut, sealing you inside. There is no way out.");

        loop();
    }

    /**
     * The main game loop that handles user interaction, command execution, and state transitions.
     */
    public void loop() {
        while (gameContext.isRunning()) {
            Player player = gameContext.getPlayer();
            Room currentRoom = player.getCurrentRoom();

            if (player.getPowerPoints() <= 0) {
                gameContext.setGameLost(true);
                gameContext.setRunning(false);
                break;
            }

            if (currentRoom.isTreasureRoom()) {
                gameContext.setGameWon(true);
                gameContext.setRunning(false);
                break;
            }

            if (currentRoom.isExit()) {
                advanceToNextDungeon();
            }

            // Check if player is asleep in the game loop
            // since no input is taken in the sleep effect
            if (player.isAsleep()) {
                try {
                    System.out.println("* Sleeping...");
                    Thread.sleep(3000);
                    new WakeUpCommand().execute(gameContext);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.print("\n* Press Enter to continue...");
            scanner.nextLine();
            ClearScreen.clear();

            System.out.println("* What would you like to do?");

            System.out.print("> ");
            String userInput = scanner.nextLine();
            System.out.println();

            ICommand command = commandParser.parse(userInput);

            if (command != null) {
                command.execute(gameContext);
            }
        }

        endGame();
        scanner.close();
    }

    /**
     * Checks if the game has been won.
     *
     * @return True if the game is won, false otherwise.
     */
    private boolean isGameWon() {
        return gameContext.isGameWon();
    }

    /**
     * Checks if the game has been lost.
     *
     * @return True if the game is lost, false otherwise.
     */
    private boolean isGameLost() {
        return gameContext.isGameLost();
    }

    /**
     * Ends the game and displays the result to the player.
     */
    private void endGame() {
        if (isGameWon()) {
            System.out.println("* You have found the cure and saved your wife!");
            System.out.printf("* You have won the game with %d power points! Congratulations!\n", gameContext.getPlayer().getPowerPoints());
        } else if (isGameLost()) {
            System.out.println("* You have run out of power points and died in the dungeon.");
            System.out.println("* You have lost the game. Better luck next time!");
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
        System.out.printf("* You have entered the Dungeon Level %d\n", currentDungeon.getLevel());
    }

}
