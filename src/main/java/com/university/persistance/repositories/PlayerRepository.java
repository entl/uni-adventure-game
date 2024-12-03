package com.university.persistance.repositories;

import com.university.persistance.entities.PlayerEntity;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Repository for managing PlayerEntity objects in the database.
 * Handles CRUD operations for the playerstable.
 */
public class PlayerRepository extends AbstractGenericRepository<PlayerEntity, Integer> {

    /**
     * Constructs a PlayerRepository
     *
     * @param connection the database connection to use
     */
    public PlayerRepository(Connection connection) {
        super(connection);
    }

    /**
     * Returns the name of the database table for PlayerEntity
     *
     * @return the table name
     */
    @Override
    protected String getTableName() {
        return "players";
    }

    /**
     * Maps a row from the ResultSet to a PlayerEntity object.
     *
     * @param resultSet the result set pointing to the current row
     * @return a PlayerEntity object containing data from the current row
     * @throws SQLException if there is an error reading the result set
     */
    @Override
    protected PlayerEntity mapRowToEntity(ResultSet resultSet) throws SQLException {
        return new PlayerEntity(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("powerPoints"),
                resultSet.getString("currentRoom"),
                resultSet.getInt("dungeonLevel"),
                resultSet.getBoolean("isTrapped"),
                resultSet.getBoolean("isAsleep"),
                resultSet.getTimestamp("created_at").toLocalDateTime(),
                resultSet.getTimestamp("updated_at").toLocalDateTime()
        );
    }

    /**
     * Updates the data of an existing player in the database.
     *
     * @param entity the PlayerEntity containing updated data
     */
    @Override
    public void update(PlayerEntity entity) {
        String query = "UPDATE players SET name = ?, powerPoints = ?, currentRoom = ?, dungeonLevel = ?, isTrapped = ?, isAsleep = ?, updated_at = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPowerPoints());
            statement.setString(3, entity.getRoom());
            statement.setInt(4, entity.getDungeonLevel());
            statement.setBoolean(5, entity.isTrapped());
            statement.setBoolean(6, entity.isAsleep());
            statement.setTimestamp(7, java.sql.Timestamp.valueOf(LocalDateTime.now()));
            statement.setInt(8, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a player record from the database by ID.
     *
     * @param id the ID of the player to delete
     */
    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM players WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves a new PlayerEntity to the database and returns its generated ID.
     *
     * @param entity the PlayerEntity to save
     * @return the ID of the saved player
     * @throws RuntimeException if there is an error during the save operation
     */
    @Override
    public int save(PlayerEntity entity) {
        String query = "INSERT INTO players (name, powerPoints, currentRoom, dungeonLevel, isTrapped, isAsleep, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPowerPoints());
            statement.setString(3, entity.getRoom());
            statement.setInt(4, entity.getDungeonLevel());
            statement.setBoolean(5, entity.isTrapped());
            statement.setBoolean(6, entity.isAsleep());
            statement.setTimestamp(7, java.sql.Timestamp.valueOf(entity.getCreatedAt()));
            statement.setTimestamp(8, java.sql.Timestamp.valueOf(LocalDateTime.now())); // Updated timestamp

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
            throw new RuntimeException("Error saving player entity", e); // Rethrow as runtime exception or handle appropriately
        }
    }
}