package com.apple.petshop.model;

public class DogTestAbstract extends AbstractPetTest {
    Pet newPet() {
        return new Dog();
    }

    String getSound() {
        return "bark";
    }
}
