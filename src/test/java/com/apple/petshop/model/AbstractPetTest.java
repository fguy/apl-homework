package com.apple.petshop.model;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public abstract class AbstractPetTest {
    /**
     * Creates a pet
     * @return a new instance of the concrete pet class
     */
    abstract Pet newPet();

    /**
     * Gets the sound of pet
     * @return the sound
     */
    abstract String getSound();

    @Test
    public void initialAge() {
        Pet pet = newPet();
        Assert.assertTrue(
                "Assert that a cat’s initial age is a random number between 5 and 10",
                pet.getAge() >= 5 && pet.getAge() <= 10
        );
    }

    private OutputStream getStdOut() {
        OutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }

    @Test
    public void speak() {
        Pet pet = newPet();

        OutputStream out = getStdOut();
        pet.speak();
        String spoken = out.toString();

        Assert.assertEquals("Assert the speak() method is properly functioning", getSound(), spoken);
    }

    @Test
    public void speakOther() {
        Pet pet = newPet();

        OutputStream out = getStdOut();
        pet.speak("other");
        String spoken = out.toString();

        Assert.assertEquals(
                "Assert the speak() method is properly functioning when an optional argument is given",
                "other",
                spoken
        );
    }

    @Test
    public void gettingOld() {
        Pet pet = newPet();
        pet.setAge(10);

        for(int i = 0; i < 5; i++) {
            Assert.assertEquals(10, pet.getAge());
            pet.speak();
        }
        Assert.assertEquals(
                "Assert that the pet’s age increases by 1 every five times it speaks",
                11,
                pet.getAge()
        );

        for(int i = 0; i < 5; i++) {
            Assert.assertEquals(11, pet.getAge());
            pet.speak();
        }
        Assert.assertEquals(
                "Assert that the pet’s age increases by 2 every ten times it speaks",
                12,
                pet.getAge()
        );
    }

    @Test
    public void nameChanges() {
        Pet pet = newPet();
        pet.setName("Steve");
        pet.setName("Jobs");
        pet.setName("Tim");
        pet.setName("Cook");
        Assert.assertArrayEquals(
                "Assert the getNames() method returns a list of all the names the pet has ever had",
                new String[]{"Steve", "Jobs", "Tim", "Cook"},
                pet.getNames().toArray()
        );
        Assert.assertEquals(
                "Assert the getAverageNameLength() method returns the average length of all the names the pet has ever had",
                4,
                pet.getAverageNameLength()
        );
    }

    @Test
    public void favoriteFood() {
        String food = "sausage";

        Pet pet = newPet();

        Assert.assertNull(pet.getFavoriteFood());

        pet.setFavoriteFood(food);

        Assert.assertEquals(food, pet.getFavoriteFood());
    }
}
