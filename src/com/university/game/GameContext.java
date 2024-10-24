package com.university.game;

import com.university.dungeon.Dungeon;
import com.university.player.Player;

/**
 * The GameContext class manages the current state of the game.
 * It holds information about the player, the current dungeon, and the overall game status (e.g., if the game is running, won, or lost).
 * The class follows the Singleton pattern, allowing only one instance of the GameContext to be active during a game session.
 */
public class GameContext {
    private static GameContext instance;

    private Player player;
    private boolean isRunning = true;
    private boolean isGameOver = false;
    private boolean isGameWon = false;
    private boolean isGameLost = false;
    private Dungeon currentDungeon;

    /**
     * Private constructor to prevent direct instantiation of GameContext.
     * This constructor initializes the game context with a player and the current dungeon.
     *
     * @param player         The player object representing the player in the game.
     * @param currentDungeon The dungeon object representing the current dungeon the player is in.
     */
    private GameContext(Player player, Dungeon currentDungeon) {
        this.player = player;
        this.currentDungeon = currentDungeon;
    }

    /**
     * Retrieves the singleton instance of the GameContext.
     * If the instance has not been created, it initializes the instance with the player and dungeon provided.
     *
     * @param player         The player object.
     * @param currentDungeon The current dungeon object.
     * @return The singleton instance of the GameContext.
     */
    public static GameContext getInstance(Player player, Dungeon currentDungeon) {
        if (instance == null) {
            instance = new GameContext(player, currentDungeon);
        }
        return instance;
    }

    /**
     * Retrieves the existing singleton instance of GameContext without parameters.
     * This method is used when the GameContext has already been initialized.
     *
     * @return The singleton instance of the GameContext.
     * @throws IllegalStateException if the GameContext has not been initialized before calling this method.
     */
    public static GameContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GameContext has not been initialized. Please initialize it first.");
        }
        return instance;
    }

    /**
     * Gets the player object from the game context.
     *
     * @return The player object.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Checks if the game is currently running.
     *
     * @return True if the game is running, false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Sets the running status of the game.
     *
     * @param running The running status to set.
     */
    public void setRunning(boolean running) {
        isRunning = running;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * Sets the game over status.
     *
     * @param gameOver True if the game is over, false otherwise.
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * Checks if the game has been won.
     *
     * @return True if the game is won, false otherwise.
     */
    public boolean isGameWon() {
        return isGameWon;
    }

    /**
     * Sets the game won status.
     *
     * @param gameWon True if the game is won, false otherwise.
     */
    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }

    /**
     * Checks if the game has been lost.
     *
     * @return True if the game is lost, false otherwise.
     */
    public boolean isGameLost() {
        return isGameLost;
    }

    /**
     * Sets the game lost status.
     *
     * @param gameLost True if the game is lost, false otherwise.
     */
    public void setGameLost(boolean gameLost) {
        isGameLost = gameLost;
    }

    /**
     * Gets the current dungeon that the player is in.
     *
     * @return The current dungeon object.
     */
    public Dungeon getCurrentDungeon() {
        return currentDungeon;
    }

    /**
     * Sets the current dungeon the player is in.
     *
     * @param currentDungeon The dungeon to set as the current dungeon.
     */
    public void setCurrentDungeon(Dungeon currentDungeon) {
        this.currentDungeon = currentDungeon;
    }
}
