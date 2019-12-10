package com.apple.petshop.dao;

import com.apple.petshop.model.Pet;
import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.UUID;

public class SqliteData implements Data {
    private Connection conn;

    private static final String INSERT_PET = "INSERT INTO pets(pet_id, species, age, favorite_food, name_change_count) VALUES(?, ?, ?, ?, ?)";
    private static final String INSERT_NAME = "INSERT INTO pet_name_changes(pet_id, name) VALUES(?, ?)";

    public SqliteData() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.setReadOnly(false);
        conn = DriverManager.getConnection("jdbc:sqlite:./sqlite.db", config.toProperties());
    }

    @Override
    public void beginTran() throws SQLException {
        conn.setAutoCommit(false);
    }

    @Override
    public void commit() throws SQLException {
        conn.commit();
    }

    @Override
    public void rollback() throws SQLException {
        conn.rollback();
    }

    @Override
    public void insert(String table, Pet object) throws SQLException {
        String id = UUID.randomUUID().toString();
        PreparedStatement stmt = null;
        try {
             stmt = conn.prepareStatement(INSERT_PET);
             stmt.setString(1, id);
             stmt.setString(2, table);
             stmt.setInt(3, object.getAge());
             stmt.setString(4, object.getFavoriteFood());
             stmt.setInt(5, object.getNames().size());
             stmt.executeUpdate();

             for (String name : object.getNames()) {
                 stmt = conn.prepareStatement(INSERT_NAME);
                 stmt.setString(1, id);
                 stmt.setString(2, name);
                 stmt.executeUpdate();
             }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
