package com.university.persistance.repositories;

import com.university.persistance.entities.RunHistoryEntity;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Repository for managing RunHistoryEntity objects in the database
 * Handles CRUD operations
 */
public class RunHistoryRepository extends AbstractGenericRepository<RunHistoryEntity, Integer> {

    /**
     * Constructs a RunHistoryRepositor
     *
     * @param connection the database connection to use
     */
    public RunHistoryRepository(Connection connection) {
        super(connection);
    }

    /**
     * Returns the name of the database table for RunHistoryEntity.
     *
     * @return the table name
     */
    @Override
    protected String getTableName() {
        return "run_history";
    }

    /**
     * Maps a row from the ResultSet to a RunHistoryEntity object.
     *
     * @param resultSet the result set pointing to the current row
     * @return a RunHistoryEntity object containing data from the current row
     * @throws SQLException if there is an error reading the result set
     */
    @Override
    protected RunHistoryEntity mapRowToEntity(ResultSet resultSet) throws SQLException {
        return new RunHistoryEntity(
                resultSet.getInt("id"),
                resultSet.getInt("player_id"), // Foreign key mapping
                resultSet.getString("outcome"),
                resultSet.getTimestamp("run_date").toLocalDateTime(),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at").toLocalDateTime()
        );
    }

    /**
     * Updates the data of an existing run history record in the database.
     *
     * @param entity the RunHistoryEntity containing updated data
     */
    @Override
    public void update(RunHistoryEntity entity) {
        String query = "UPDATE run_history SET player_id = ?, outcome = ?, run_date = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getPlayerId());
            statement.setString(2, entity.getOutcome());
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(entity.getRunDate()));
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a run history record from the database by ID.
     *
     * @param id the ID of the run history record to delete
     */
    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM run_history WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a new RunHistoryEntity to the database and returns its generated ID.
     *
     * @param entity the RunHistoryEntity to save
     * @return the ID of the saved run history record
     * @throws RuntimeException if there is an error during the save operation
     */
    @Override
    public int save(RunHistoryEntity entity) {
        String query = "INSERT INTO run_history (player_id, outcome, run_date, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // Set parameters for the query
            statement.setInt(1, entity.getPlayerId()); // Foreign key
            statement.setString(2, entity.getOutcome());
            statement.setTimestamp(3, java.sql.Timestamp.valueOf(entity.getRunDate()));
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(entity.getCreatedAt()));
            statement.setTimestamp(5, java.sql.Timestamp.valueOf(LocalDateTime.now())); // Updated timestamp

            // Execute the INSERT statement
            statement.executeUpdate();

            // Retrieve the generated keys
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                } else {
                    throw new SQLException("Failed to retrieve generated ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error saving run history entity", e);
        }
    }
}