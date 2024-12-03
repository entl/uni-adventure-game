package com.university.persistance.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic repository for performing common database operations.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the entity's identifier
 */
public abstract class AbstractGenericRepository<T, ID> implements IGenericRepository<T, ID> {
    protected final Connection connection;

    /**
     * Creates a new instance with the given database connection.
     *
     * @param connection the database connection to use
     */
    public AbstractGenericRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Returns the name of the database table for the entity.
     *
     * @return the table name
     */
    protected abstract String getTableName();

    /**
     * Maps a database row to an entity object.
     *
     * @param resultSet the result set pointing to the current row
     * @return the mapped entity object
     * @throws SQLException if there is an error reading the result set
     */
    protected abstract T mapRowToEntity(ResultSet resultSet) throws SQLException;

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the entity, or null if not found
     */
    @Override
    public T findById(ID id) {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapRowToEntity(resultSet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Finds all entities in the table.
     *
     * @return a list of all entities
     */
    @Override
    public List<T> findAll() {
        String query = "SELECT * FROM " + getTableName();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            List<T> results = new ArrayList<>();
            while (resultSet.next()) {
                results.add(mapRowToEntity(resultSet));
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return List.of();
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to delete
     */
    @Override
    public void delete(ID id) {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}