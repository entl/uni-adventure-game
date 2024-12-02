package com.university.utils.ui;

public interface UI {
    void displayMessage(String message);
    void displayError(String errorMessage);
    void clearScreen();
    void displayInputPrompt(String message);
}
