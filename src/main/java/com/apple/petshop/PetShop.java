package com.apple.petshop;

import com.apple.petshop.dao.Data;
import com.apple.petshop.dao.SqliteData;
import com.apple.petshop.model.Cat;
import com.apple.petshop.model.Dog;

import javax.accessibility.AccessibleStateSet;
import java.sql.SQLException;

public class PetShop {
    static class Stats {
        int catInserted;
        int dogInserted;
        int namedCatInserted;
        int namedDogInserted;
    }

    Data data; // I'd use DI container to get an instance, lifecycle of it would be managed by the container.
    Stats stats = new Stats();

    public PetShop() {
        try {
            data = new SqliteData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTest() {
        try {
            // Create a cat with a name and insert it into the database.
            data.insert("Cat", new Cat("Garfield"));
            stats.namedCatInserted++;
            // Create a dog with a name and insert it into the database.
            data.insert("Dog", new Dog("Bingo"));
            stats.namedDogInserted++;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void savePetShop() {
        // Create three nameless cats.
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

        // Create three nameless dogs.
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();
        try {
            data.beginTran();

            // Insert all six pets into the database.
            data.insert("Cat", cat1 );
            stats.catInserted++;
            data.insert("Cat", cat2);
            stats.catInserted++;
            data.insert("Cat", cat3);
            stats.catInserted++;

            data.insert("Dog", dog1);
            stats.dogInserted++;
            data.insert("Dog", dog2);
            stats.dogInserted++;
            data.insert("Dog", dog3);
            stats.dogInserted++;

            // Important: guarantee that all six pets (or zero pets) are persisted.
            data.commit();
        } catch (Exception e) {
            try {
                data.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Print interesting information about your script’s execution results to STDOUT
    public void logStats() {
        System.out.println(String.format("%d cat inserted", stats.catInserted));
        System.out.println(String.format("%d dog inserted", stats.dogInserted));
        System.out.println(String.format("%d named cat inserted", stats.namedCatInserted));
        System.out.println(String.format("%d named dog inserted", stats.namedDogInserted));
    }

    public static void main(String args[]) {
        PetShop petShop = new PetShop(); // I'd use DI container to get an instance, lifecycle of it would be managed by the container.
        petShop.saveTest();
        petShop.savePetShop();
        petShop.logStats();
    }
}
