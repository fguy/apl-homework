package com.apple.petshop;

import com.apple.petshop.model.Cat;
import com.apple.petshop.dao.Data;
import com.apple.petshop.dao.FakeData;

public class Main {

    public static void main(String[] args) throws Exception {
        Cat cat = new Cat();
        System.out.println("Name is currently " + cat.getName());

        cat.setName("Garfield");
        System.out.println("Name has been changed to " + cat.getName());

        Data data = new FakeData("database");

        data.insert("Cat", cat);
    }
}
