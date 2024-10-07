package com.university.game;

import com.university.Config;
import com.university.dungeon.Dungeon;
import com.university.dungeon.DungeonService;
import com.university.player.Player;
import com.university.utils.commands.Command;
import com.university.utils.commands.ICommand;
import com.university.utils.parsers.CommandParser;

import java.util.Scanner;

public class Game {
    private static Game instance;   // singleton instance to ensure only one game is running at a time
    private final String[] dungeonPaths = Config.dungeonPaths;  // paths to dungeon files
    private Dungeon[] dungeons;     // array of dungeons
    private GameContext gameContext;    // game context
    private CommandParser commandParser;    // command parser


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
        commandParser = new CommandParser();

        Player player = new Player("Dummy Name", 100);
        DungeonService dungeonService = new DungeonService();

        dungeons = new Dungeon[dungeonPaths.length];
        for (int i = 0; i < dungeonPaths.length; i++) {
            dungeons[i] = dungeonService.initializeDungeon(dungeonPaths[i]);
        }

        gameContext = new GameContext(player, dungeons[0], dungeons[0].getEntranceRoom());
    }

    public void startGame() {
        System.out.println("Game started!");
        loop();
    }

    public void loop() {
        Scanner scanner = new Scanner(System.in);
        while (gameContext.isRunning()) {
            System.out.println("What would you like to do?");

            String userInput = scanner.nextLine();

            ICommand command = commandParser.parse(userInput);

            if (command != null) {
                command.execute(gameContext);
            }
        }
        scanner.close();
    }
}
