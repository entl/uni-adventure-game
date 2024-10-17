package com.university.utils;

// since java doesn't have a built-in way to clear the console screen, we can use this class to simulate
// it will print 50 new lines to clear the screen
public class ClearScreen {
    public static void clear() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
