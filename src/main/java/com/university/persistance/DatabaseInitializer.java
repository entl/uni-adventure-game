package com.university.persistance;

import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Initializes the database schema.
 */
public class DatabaseInitializer {
    private static ILogger logger = LoggerFactory.getLogger(DatabaseInitializer.class);
    private static final String PLAYER_TABLE = """
        CREATE TABLE IF NOT EXISTS players (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            name TEXT NOT NULL,
            powerPoints INTEGER NOT NULL,
            currentRoom TEXT NOT NULL,
            dungeonLevel INTEGER NOT NULL,
            isTrapped BOOLEAN NOT NULL,
            isAsleep BOOLEAN NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        );
    """;

    private static final String RUN_HISTORY_TABLE = """
        CREATE TABLE IF NOT EXISTS run_history (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            player_id INTEGER NOT NULL,
            outcome TEXT NOT NULL,
            run_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (player_id) REFERENCES players (id) ON DELETE CASCADE
        );
    """;

    public static void initializeDatabase(String databaseUrl) {
        try (Connection connection = DatabaseConnection.getInstance(databaseUrl);
             Statement statement = connection.createStatement()) {
            statement.execute(PLAYER_TABLE);
            statement.execute(RUN_HISTORY_TABLE);

            logger.info("Database initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
