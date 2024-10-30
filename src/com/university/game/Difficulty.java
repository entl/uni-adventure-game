package com.university.game;

import com.university.Config.SpawnRates;

/**
 * Enum representing different difficulty levels in the game.
 * Each difficulty level retrieves its spawn rates from the {@code Config} class.
 */
public enum Difficulty {
    EASY {
        @Override
        public SpawnRates getSpawnRates() {
            return SpawnRates.EASY;
        }

        @Override
        public int getPowerPoints() {
            return SpawnRates.PowerPointsEasy;
        }
    },
    MEDIUM {
        @Override
        public SpawnRates getSpawnRates() {
            return SpawnRates.MEDIUM;
        }

        @Override
        public int getPowerPoints() {
            return SpawnRates.PowerPointsMedium;
        }
    },
    HARD {
        @Override
        public SpawnRates getSpawnRates() {
            return SpawnRates.HARD;
        }

        @Override
        public int getPowerPoints() {
            return SpawnRates.PowerPointsHard;
        }
    };

    public abstract SpawnRates getSpawnRates();
    public int getPowerPoints() {
        return 0;
    }
}
