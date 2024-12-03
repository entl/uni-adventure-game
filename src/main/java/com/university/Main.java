package com.university;

import com.university.config.Config;
import com.university.core.Game;
import com.university.dungeon.DungeonService;
import com.university.utils.input.ConsoleInputHandler;
import com.university.utils.input.IInputHandler;
import com.university.utils.parsers.CommandParser;
import com.university.utils.ui.UI;
import com.university.utils.ui.UIManager;


public class Main {
    public static void main(String[] args) {
        try {
            UI ui = UIManager.getInstance();
            CommandParser commandParser = new CommandParser();
            String[] dungeonPaths = Config.dungeonPaths;
            DungeonService dungeonService = new DungeonService();
            IInputHandler inputHandler = new ConsoleInputHandler();
            Game game = new Game(ui, commandParser, dungeonService, dungeonPaths, inputHandler);
            game.startGame();
        }
        catch (Exception e) {
            UIManager.getInstance().displayError("Error: " + e.getMessage());
        }
    }
}