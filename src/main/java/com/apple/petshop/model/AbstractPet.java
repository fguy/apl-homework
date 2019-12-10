package com.apple.petshop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractPet implements Pet {
    protected String name;
    protected int age;
    protected String favoriteFood;

    private List<String> nameChanges = new ArrayList<>();
    private double averageNameLength = 0;
    private int spokeCount = 0;

    public AbstractPet() {
        this.age = 5 + new Random().nextInt(5);
    }

    public AbstractPet(String name) {
        this();
        setName(name);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAge() {
        return age + (int) Math.floor(spokeCount / 5);
    }

    @Override
    public String getFavoriteFood() {
        return favoriteFood;
    }

    @Override
    public void setName(String name) {
        int nameChangeAttempts = nameChanges.size();

        this.name = name;
        nameChanges.add(name);

        double totalNameLength = (nameChanges.isEmpty() ? 0 : averageNameLength * nameChangeAttempts) + name.length();
        averageNameLength = totalNameLength / ++nameChangeAttempts;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void speak(String sound) {
        System.out.print(sound);
        spokeCount++;
    }

    @Override
    public List<String> getNames() {
        return nameChanges;
    }

    @Override
    public int getAverageNameLength() {
        return (int) averageNameLength;
    }
}
