package usecases;

import entities.Dog;

public class ExpCalculator {
    /**
     * Calculates the EXP a dog earns from a pet.
     */

    public int calculateExp(Dog dog) {
        return (dog.getCoins() / 20) + 1;
    }

}
