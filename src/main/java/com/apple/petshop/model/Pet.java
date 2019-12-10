package com.apple.petshop.model;

import java.util.List;

public interface Pet {
    String getName();
    int getAge();
    String getFavoriteFood();
    void setName(String name);
    void setAge(int age);
    void setFavoriteFood(String favoriteFood);
    void speak();
    void speak(String sound);
    List<String> getNames();
    int getAverageNameLength();
}
