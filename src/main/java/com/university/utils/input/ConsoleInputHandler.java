package com.university.utils.input;

import java.util.Scanner;

/**
 * Handles console input from the user using a {@link Scanner}.
 * Implements the {@link IInputHandler} interface for user input handling.
 */
public class ConsoleInputHandler implements IInputHandler {
    private final Scanner scanner;

    /**
     * Constructs a ConsoleInputHandler with a new {@link Scanner} for reading console input.
     */
    public ConsoleInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the console.
     *
     * @return the input entered by the user as a {@link String}
     */
    @Override
    public String getInput() {
        return scanner.nextLine();
    }

    /**
     * Closes the  {@link Scanner} to release system resources
     */
    @Override
    public void close() {
        scanner.close();
    }
}