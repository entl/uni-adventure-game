package com.university.utils.input;

public interface IInputHandler {
    /**
     * Reads a line of input from the user.
     *
     * @return The input string.
     */
    String getInput();

    void close();
}
