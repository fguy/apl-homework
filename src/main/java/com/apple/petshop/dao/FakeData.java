package com.apple.petshop.dao;

import com.apple.petshop.model.Pet;

public class FakeData implements Data {
    public FakeData(String database) {
        System.out.println("Connecting to database");
    }

    @Override
    public void beginTran() {
        System.out.println("Beginning a transaction");
    }

    @Override
    public void commit() {
        System.out.println("Committing transaction");
    }

    @Override
    public void rollback() {
        System.out.println("Rolling back transaction");
    }

    @Override
    public void insert(String table, Pet object) {
        System.out.println("Inserting " + object.getName() + " into table " + table);
    }
}
