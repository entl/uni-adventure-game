package com.university.persistance;

import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Handles the database connection lifecycle for the application.
 * This class ensures a single connection instance (singleton pattern)
 * and provides methods to initialize and close the connection.
 */
public class DatabaseConnection {
    private static Connection connection;
    private static ILogger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    /**
     * Initializes the database connection if not already established.
     *
     * @param URL the database connection URL.
     * @return a singleton instance of the database connection.
     * @throws SQLException if a connection cannot be established.
     */
    public static Connection getInstance(String URL) throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL);
                logger.info("Database connection established.");
            } catch (SQLException e) {
                logger.error("Failed to connect to the database: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    /**
     * Closes the database connection if it is open.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Error while closing the connection: " + e.getMessage());
            }
        }
    }
}