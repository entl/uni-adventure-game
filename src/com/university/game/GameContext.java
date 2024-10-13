package com.university.game;

import com.university.dungeon.Dungeon;
import com.university.player.Player;

public class GameContext {
    private static GameContext instance;

    private Player player;
    private boolean isRunning = true;
    private boolean isGameOver = false;
    private boolean isGameWon = false;
    private boolean isGameLost = false;
    private Dungeon currentDungeon;

    private GameContext(Player player, Dungeon currentDungeon) {
        this.player = player;
        this.currentDungeon = currentDungeon;
    }

    public static  GameContext getInstance(Player player, Dungeon currentDungeon) {
        if (instance == null) {
            instance = new GameContext(player, currentDungeon);
        }
        return instance;
    }

    // overloaded method for getting the instance without parameters if it has already been initialized
    public static GameContext getInstance() {
        if (instance == null) {
            throw new IllegalStateException("GameContext has not been initialized. Please initialize it first.");
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }

    public boolean isGameLost() {
        return isGameLost;
    }

    public void setGameLost(boolean gameLost) {
        isGameLost = gameLost;
    }

    public Dungeon getCurrentDungeon() {
        return currentDungeon;
    }

    public void setCurrentDungeon(Dungeon currentDungeon) {
        this.currentDungeon = currentDungeon;
    }
}
