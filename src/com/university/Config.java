package com.university;

import java.nio.file.Paths;

/**
 * The {@code Config} class stores the configuration details for the game,
 * including the paths to the dungeon CSV files. These paths are constructed
 * dynamically using the {@code Paths.get()} method to ensure cross-platform compatibility.
 */
public class Config {
    private static final String dungeonPath1 = Paths.get("src", "resources", "dungeon1.csv").toString();
    private static final String dungeonPath2 = Paths.get("src", "resources", "dungeon2.csv").toString();
    private static final String dungeonPath3 = Paths.get("src", "resources", "dungeon3.csv").toString();

    /**
     * Array containing all dungeon paths.
     * This can be used to iterate through multiple dungeon levels.
     */
    public static final String[] dungeonPaths = {dungeonPath1, dungeonPath2, dungeonPath3};
}