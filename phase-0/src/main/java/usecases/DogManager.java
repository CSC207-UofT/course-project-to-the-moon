package main.java.usecases;

import main.java.entities.Dog;

import java.io.IOException;
import java.util.List;

public class DogManager {
    /**

     */

    private Dog myDog;
    private CoinCalculator coinCalc;
    private ExpCalculator expCalc;

    public DogManager() {}

    public int[] pet() {
        /**
         Calculate exp and coins earned, update dog, return new coin and exp values.
         */
        int newCoin = coinCalc.coinPet(myDog);
        int newExp = expCalc.expPet(myDog);

        myDog.update(newCoin, newExp);

        return new int[]{newCoin, newExp};
    }

}
