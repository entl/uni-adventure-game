package com.university;

import java.nio.file.Paths;

public class Config {
    private static final String dungeonPath1 = Paths.get("src", "resources", "dungeon1.csv").toString();
    private static final String dungeonPath2 = Paths.get("src", "resources", "dungeon2.csv").toString();
    private static final String dungeonPath3 = Paths.get("src", "resources", "dungeon3.csv").toString();

    public static final String[] dungeonPaths = {dungeonPath1, dungeonPath2, dungeonPath3};
}
