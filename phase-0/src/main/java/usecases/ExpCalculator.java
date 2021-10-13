package java.usecases;

import java.entities.Dog;

public class ExpCalculator {
    /**
     * Calculates the EXP a dog earns from a pet.
     */

    public int calculateExp(Dog dog) {
        int exp = dog.getLevel(); //Finds the current number of exp points in the dog's account
        return exp + 1; //Adds 1 exp point to the account and returns it to be displayed.
    }

}
