package com.university.config;

import java.nio.file.Paths;

/**
 * The {@code Config} class stores the configuration details for the game,
 * including the paths to the dungeon CSV files. These paths are constructed
 * dynamically using the {@code Paths.get()} method to ensure cross-platform compatibility.
 */
public class Config {
    private static final String dungeonPath1 = Paths.get("src", "main", "resources", "dungeon1.csv").toString();
    private static final String dungeonPath2 = Paths.get("src", "main", "resources", "dungeon2.csv").toString();
    private static final String dungeonPath3 = Paths.get("src", "main", "resources", "dungeon3.csv").toString();

    /**
     * Array containing all dungeon paths.
     * This can be used to iterate through multiple dungeon levels.
     */
    public static final String[] dungeonPaths = {dungeonPath1, dungeonPath2, dungeonPath3};

    /**
     * Static nested class representing spawn rates for different difficulty levels.
     */
    public static class SpawnRates {

        public static final SpawnRates EASY = new SpawnRates(0.6, 0.3, 0.1, 0.1, 0.2);
        public static final SpawnRates MEDIUM = new SpawnRates(0.75, 0.2, 0.05, 0.1, 0.3);
        public static final SpawnRates HARD = new SpawnRates(0.8, 0.15, 0.05, 0.05, 0.4);

        public static final int PowerPointsEasy = 100;
        public static final int PowerPointsMedium = 70;
        public static final int PowerPointsHard = 50;

        // Item and trap spawn probabilities
        public final double zeroItemProbability;
        public final double oneItemProbability;
        public final double twoItemProbability;
        public final double chestProbability;
        public final double trapProbability;

        // Constructor
        public SpawnRates(double zeroItemProbability, double oneItemProbability, double twoItemProbability,
                          double chestProbability, double trapProbability) {
            this.zeroItemProbability = zeroItemProbability;
            this.oneItemProbability = oneItemProbability;
            this.twoItemProbability = twoItemProbability;
            this.chestProbability = chestProbability;
            this.trapProbability = trapProbability;
        }
    }
}