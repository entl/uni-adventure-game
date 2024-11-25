package com.university.utils.UI;

public class UIManager {

    private static UI instance;

    // Prevent instantiation
    private UIManager() {}

    /**
     * Provides the current UI instance.
     *
     * @return The UI instance.
     */
    public static UI getInstance() {
        if (instance == null) {
            instance = new ConsoleUI();
        }
        return instance;
    }

    /**
     * Allows switching the UI implementation like GUI or Console.
     *
     * @param newUI The new UI implementation.
     */
    public static void setInstance(UI newUI) {
        instance = newUI;
    }
}