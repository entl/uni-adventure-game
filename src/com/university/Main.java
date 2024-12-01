package com.university;

import com.university.core.Game;
import com.university.utils.ui.UIManager;

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