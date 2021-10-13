package usecases;

import entities.Dog;

public class CoinCalculator {
    /**
     Calculates coin earned from calling specific methods.
     */
    public int calculateCoins(Dog dog) {
        return (dog.getExp() / 10) + 1;
    }


}
