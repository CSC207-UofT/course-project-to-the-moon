package java.usecases;

import java.entities.Dog;

public class CoinCalculator {
    /**
     Calculates coin earned from calling specific methods.
     */
    public int calculateCoins(Dog dog) {
        int coins = dog.getCoins(); //Finds the current number of coins in the dog's account
        return ++coins; //Adds 1 coin to the account and returns it to be displayed.
    }


}
