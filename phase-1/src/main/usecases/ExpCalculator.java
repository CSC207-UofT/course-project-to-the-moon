package usecases;

import entities.Dog;

public class ExpCalculator {
    /**
     * Calculates the EXP a dog earns from a pet.
     * @author Praket Kanaujia
     * @since 10 October 2021
     */

    public int calculateExp(Dog dog) {
        return (dog.getCoins() / 200) + 1;
    }

}
