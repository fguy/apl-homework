package com.apple.petshop.model;

public class Dog extends AbstractPet implements Pet {

    public Dog() {
        super();
    }

    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        speak("bark");
    }
}
