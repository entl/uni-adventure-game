package com.university.game;

import com.university.dungeon.Dungeon;
import com.university.dungeon.room.Room;
import com.university.player.Player;

public class GameContext {
    private Player player;
    private boolean isRunning = true;
    private boolean isGameOver = false;
    private boolean isGameWon = false;
    private boolean isGameLost = false;
    private Dungeon currentDungeon;
    private Room currentRoom;


    public GameContext(Player player, Dungeon currentDungeon, Room currentRoom) {
        this.player = player;
        this.currentDungeon = currentDungeon;
        this.currentRoom = currentRoom;
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

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
