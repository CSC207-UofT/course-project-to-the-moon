package main.java.usecases;

import main.java.entities.Dog;

import java.io.IOException;
import java.util.List;

/**
 * This class represents all functionalities of the dog and runs them.
 * @author Juntae Park
 * @since 12 October 2021
 */
public class DogManager {
    
    private Dog myDog;
    private CoinCalculator coinCalc;
    private ExpCalculator expCalc;

    public DogManager() {
        this.myDog = new Dog();
        this.coinCalc = new CoinCalculator();
        this.expCalc = new ExpCalculator();
    }


    public int[][] getLocation() {
        return new int[][]{{myDog.x, myDog.x+myDog.d_width}, {myDog.y, myDog.y+myDog.d_height}};
    }

    public void pet() {
        /**
         Calculate exp and coins earned, update dog, return new coin and exp values.
         */
        int newCoin = coinCalc.coinPet(myDog);
        int newExp = expCalc.expPet(myDog);

        myDog.update(newCoin, newExp);

        System.out.println("coin earned: " + newCoin);
        System.out.println("exp earned: " + newExp);
    }

}
