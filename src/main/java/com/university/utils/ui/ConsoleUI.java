package com.university.utils.ui;

public class ConsoleUI implements UI {
    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayError(String errorMessage) {
        System.err.println(errorMessage);
    }

    @Override
    public void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    @Override
    public void displayInputPrompt(String message) {
        System.out.print(message);
    }
}
