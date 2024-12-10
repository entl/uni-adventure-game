package com.university;

import com.university.config.Config;
import com.university.core.Game;
import com.university.core.Menu;
import com.university.dungeon.DungeonService;
import com.university.elements.items.ItemFactory;
import com.university.utils.commands.CommandFactory;
import com.university.utils.input.ConsoleInputHandler;
import com.university.utils.input.IInputHandler;
import com.university.utils.parsers.CommandParser;
import com.university.utils.ui.UI;
import com.university.utils.ui.UIManager;
import com.university.persistance.DatabaseConnection;
import com.university.persistance.DatabaseInitializer;
import com.university.persistance.repositories.PlayerRepository;
import com.university.persistance.repositories.RunHistoryRepository;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        try {
            String databaseUrl = Config.databaseUrl;
            DatabaseInitializer.initializeDatabase(databaseUrl);
            Connection connection = DatabaseConnection.getInstance(databaseUrl);

            ItemFactory itemFactory = new ItemFactory();
            CommandFactory commandFactory = new CommandFactory();

            UI ui = UIManager.getInstance();
            CommandParser commandParser = new CommandParser(itemFactory, commandFactory);
            String[] dungeonPaths = Config.dungeonPaths;
            DungeonService dungeonService = new DungeonService();
            IInputHandler inputHandler = new ConsoleInputHandler();

            RunHistoryRepository runHistoryRepository = new RunHistoryRepository(connection);
            PlayerRepository playerRepository = new PlayerRepository(connection);

            Game game = new Game(ui, commandParser, dungeonService, dungeonPaths, inputHandler, playerRepository, runHistoryRepository);
            Menu menu = new Menu(ui, runHistoryRepository, playerRepository, inputHandler, game);

            menu.display();
        }
        catch (Exception e) {
            UIManager.getInstance().displayError("Error: " + e.getMessage());
        }
    }
}