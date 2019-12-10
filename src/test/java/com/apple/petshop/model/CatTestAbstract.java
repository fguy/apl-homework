package com.apple.petshop.model;

public class CatTestAbstract extends AbstractPetTest {
    Pet newPet() {
        return new Cat();
    }

    String getSound() {
        return "meow";
    }
}
