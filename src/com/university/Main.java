package com.university;

import com.university.game.Game;
import com.university.utils.UI.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            Game game = Game.initialize();
            game.startGame();
        }
        catch (Exception e) {
            UIManager.getInstance().displayError("Error: " + e.getMessage());
        }
    }
}