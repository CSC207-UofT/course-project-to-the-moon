package main.java.usecases;

import main.java.entities.Dog;

import java.io.IOException;
import java.util.List;

/**
 * This class represents all functionalities of the dog and runs them.
 * @author Juntae Park
 * @since 12 October 2021
 */
public class DogManager implements Clickable {
    
    private Dog myDog;
    private CoinCalculator coinCalc;
    private ExpCalculator expCalc;

    public DogManager() {
        this.myDog = new Dog();
        this.coinCalc = new CoinCalculator();
        this.expCalc = new ExpCalculator();
    }

    private void update(int EarnedCoin, int EarnedExp) {
        myDog.setCoins(EarnedCoin);
        myDog.setExp(EarnedExp);
    }

    @Override
    public void act() {
        /**
         Pet the Dog, calculate exp and coins earned, update dog.
         */
        int EarnedCoin = coinCalc.coinPet(myDog);
        int EarnedExp = expCalc.expPet(myDog);

        this.update(EarnedCoin, EarnedExp);

        System.out.println("coin earned: " + EarnedCoin);
        System.out.println("exp earned: " + EarnedExp);
    }

    @Override
    public int[][] getLocation() {
        return new int[][]{{myDog.x, myDog.x+myDog.d_width}, {myDog.y, myDog.y+myDog.d_height}};
    }

    @Override
    public boolean clicked(int mouseX, int mouseY) {
        int[][] loc = this.getLocation();
        return ( (loc[0][0]  <= mouseX && mouseX <= loc[0][1]) && (loc[1][0] <= mouseY && mouseY <= loc[1][1]));
    }

}
