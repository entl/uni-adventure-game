package com.university.game;

import com.university.Config;
import com.university.dungeon.Dungeon;
import com.university.dungeon.DungeonService;
import com.university.player.Player;
import com.university.utils.commands.ICommand;
import com.university.utils.parsers.CommandParser;

import java.util.Scanner;

public class Game {
    private static Game instance;   // singleton instance to ensure only one game is running at a time
    private final String[] dungeonPaths = Config.dungeonPaths;  // paths to dungeon files
    private Dungeon[] dungeons;     // array of dungeons
    private GameContext gameContext;    // game context
    private CommandParser commandParser;    // command parser
    private Scanner scanner;


    private Game() {
        initializeGame();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
            instance.initializeGame();
        }
        return instance;
    }

    private void initializeGame() {
        scanner = new Scanner(System.in);
        commandParser = new CommandParser();

        Player player = new Player("Dummy Name", 100);
        DungeonService dungeonService = new DungeonService();

        dungeons = new Dungeon[dungeonPaths.length];
        for (int i = 0; i < dungeonPaths.length; i++) {
            dungeons[i] = dungeonService.initializeDungeon(dungeonPaths[i]);
        }

        gameContext = new GameContext(player, dungeons[0], dungeons[0].getEntranceRoom());
        player.setCurrentRoom(gameContext.getCurrentRoom());
    }

    public void startGame() {
        String startingArt = """
                ######################################
                
                oooooooooo.    .o"o.   oooooooooo.  \s
                `888'   `Y8b  "' "  `" `888'   `Y8b \s
                 888      888    "      888      888\s
                 888      888    "      888      888\s
                 888      888    "      888      888\s
                 888     d88'    "      888     d88'\s
                o888bood8P'      "     o888bood8P'  \s
                
                ######################################
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

    public void loop() {
        while (gameContext.isRunning()) {
            System.out.println();
            System.out.println("* What would you like to do?");

            System.out.print("> ");
            String userInput = scanner.nextLine();
            System.out.println();

            ICommand command = commandParser.parse(userInput);

            if (command != null) {
                command.execute(gameContext);
            }
        }
        scanner.close();
    }
}
