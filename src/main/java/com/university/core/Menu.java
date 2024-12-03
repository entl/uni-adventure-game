package com.university.core;

import com.university.utils.input.IInputHandler;
import com.university.utils.ui.UI;
import com.university.persistance.repositories.PlayerRepository;
import com.university.persistance.repositories.RunHistoryRepository;
import com.university.persistance.entities.PlayerEntity;
import com.university.persistance.entities.RunHistoryEntity;

import java.util.List;

/**
 * The main menu of the game.
 */
public class Menu {
    private final UI ui;
    private final RunHistoryRepository runHistoryRepository;
    private final PlayerRepository playerRepository;
    private final IInputHandler inputHandler;
    private final Game game;

    /**
     * Constructs a Menu instance with the provided dependencies.
     *
     * @param ui                  the UI instance for displaying messages and input prompts
     * @param runHistoryRepository the repository for accessing run history data
     * @param playerRepository     the repository for accessing player data
     * @param inputHandler         the input handler for reading user inputs
     * @param game                 the Game instance for starting a new game
     */
    public Menu(UI ui, RunHistoryRepository runHistoryRepository, PlayerRepository playerRepository, IInputHandler inputHandler, Game game) {
        this.ui = ui;
        this.runHistoryRepository = runHistoryRepository;
        this.playerRepository = playerRepository;
        this.inputHandler = inputHandler;
        this.game = game;
    }

    /**
     * Displays the main menu and handles user interaction.
     * Users can select options to start a new game, view previous runs, or exit the game
     */
    public void display() {
        boolean running = true;
        while (running) {
            ui.displayMessage("""
                    =====================================
                    Welcome to Dungeons NAND Dragons!
                    =====================================
                    1. Start a New Game
                    2. View Previous Runs
                    3. Exit
                    =====================================
                    """);
            ui.displayInputPrompt("Select an option: ");
            String choice = inputHandler.getInput();

            switch (choice) {
                case "1":
                    startNewGame();
                    break;
                case "2":
                    displayRunHistory();
                    break;
                case "3":
                    ui.displayMessage("* Exiting the game. Goodbye!");
                    running = false;
                    break;
                default:
                    ui.displayMessage("* Invalid choice. Please enter 1, 2, or 3.");
            }

            ui.displayMessage("\nPress Enter to continue...");
            inputHandler.getInput();
            ui.clearScreen();
        }
    }

    /**
     * Starts a new game
     */
    private void startNewGame() {
        ui.displayMessage("* Starting a new game...\n");
        game.startGame();
    }

    /**
     * Displays the run history stored in the database.
     * Each run includes details such as run ID, player name, outcome, dungeon level, and date.
     * If no runs are found, a message is displayed
     */
    private void displayRunHistory() {
        ui.displayMessage("""
                =====================================
                            Previous Runs
                =====================================
                """);
        List<RunHistoryEntity> runs = runHistoryRepository.findAll();

        if (runs.isEmpty()) {
            ui.displayMessage("* No previous runs found.");
        } else {
            StringBuilder message = new StringBuilder();

            // Column headers
            message.append(String.format("%-10s %-20s %-15s %-15s %-25s%n",
                    "Run ID", "Player Name", "Outcome", "Dungeon Level", "Date"));

            for (RunHistoryEntity run : runs) {
                PlayerEntity player = playerRepository.findById(run.getPlayerId());
                message.append(String.format(
                        "%-10d %-20s %-15s %-15d %-25s%n",
                        run.getId(),
                        player.getName(),
                        run.getOutcome(),
                        player.getDungeonLevel(),
                        run.getRunDate()
                ));
            }

            ui.displayMessage(message.toString());
        }
    }
}