package com.apple.petshop.dao;

import com.apple.petshop.model.Pet;

import java.sql.SQLException;

public interface Data {
    void beginTran() throws SQLException;
    void commit() throws SQLException;
    void rollback() throws SQLException;
    void insert(String table, Pet object) throws SQLException;
}
