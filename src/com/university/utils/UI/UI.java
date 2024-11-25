package com.university.utils.UI;

public interface UI {
    void displayMessage(String message);
    void displayError(String errorMessage);
    void clearScreen();

    /**
     * Displays a message without a newline by default.
     * This can be overridden in implementing classes if needed.
     */
    default void displayInline(String message) {
        System.out.print(message);
    }
}
