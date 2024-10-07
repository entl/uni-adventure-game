package com.university;

import com.university.game.Game;


public class Main {
    public static void main(String[] args) {
        try {
            Game game = Game.getInstance();
            game.startGame();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}