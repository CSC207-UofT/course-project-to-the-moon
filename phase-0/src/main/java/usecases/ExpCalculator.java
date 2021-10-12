package main.java.usecases;

import main.java.entities.Dog;

public class ExpCalculator {
    /**
     *
     *
     */

    public int expPet(Dog dog) {
        int exp = dog.getLevel(); //Finds the current number of exp points in the dog's account
        return exp + 1; //Adds 1 exp point to the account and returns it to be displayed.
    }

}
