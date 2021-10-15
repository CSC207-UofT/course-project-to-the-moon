package usecases;

import entities.Dog;

public class CoinCalculator {
    /**
     * Calculates coin earned from calling specific methods.
     * @author Praket Kanaujia
     * @since 10 October 2021
     */
    public int calculateCoins(Dog dog) {
        return (dog.getExp() / 10) + 1;
    }


}
