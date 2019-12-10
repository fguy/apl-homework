package com.apple.petshop.model;

public class Cat extends AbstractPet implements Pet {

    public Cat() {
        super();
    }

    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        speak("meow");
    }
}
