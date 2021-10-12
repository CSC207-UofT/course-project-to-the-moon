package main.java.usecases;

import main.java.entities.Dog;

public class CoinCalculator {
    /**
     Calculates coin earned from calling specific methods.
     */

    public int coinPet(Dog dog) {
        int coins = dog.getCoins(); //Finds the current number of coins in the dog's account
        return coins + 1; //Adds 1 coin to the account and returns it to be displayed.
    }


}
