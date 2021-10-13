package java.usecases;

import java.entities.Dog;
import java.entities.Sprite;
import java.usecases.DogMover;

import java.io.IOException;
import java.util.List;

/**
 * This class manages the functionalities of a single dog.
 * @author Juntae Park and Andy Wang
 * @since 12 October 2021
 */
public class DogManager implements Clickable {
    private Dog myDog; // the dog that this manager handles
    private Sprite dogSprite;
    private CoinCalculator coinCalc; // the coin calculator for this dog
    private ExpCalculator expCalc; // the exp calculator for this dog
    private DogMover dogMover;

    /**
     * Initializes a dog manager, while also creating a dog and corresponding sprite.
     */
    public DogManager() {
        this.myDog = new Dog();
        this.dogSprite = new Sprite("sprites/dog", 2);

        // facade pattern!!!
        this.coinCalc = new CoinCalculator();
        this.expCalc = new ExpCalculator();
        this.dogMover = new DogMover(this.myDog);
    }

    private void update(int earnedCoin, int earnedExp) {
        this.myDog.setCoins(earnedCoin);
        this.myDog.setExp(earnedExp);
    }

    /**
     * Pets the Dog, calculate exp and coins earned, update dog.
     */
    @Override
    public void act() {
        int earnedCoin = coinCalc.calculateCoins(myDog);
        int earnedExp = expCalc.calculateExp(myDog);

        this.update(earnedCoin, earnedExp);

        System.out.println("coin earned: " + earnedCoin);
        System.out.println("exp earned: " + earnedExp);
    }

    /**
     * Returns the x, y coordinates of the dog.
     * @return The x, y coordinates of the dog.
     */
    @Override
    public int[] getLocation() {
        return new int[]{this.myDog.getX(), this.myDog.getY()};
    }

    @Override
    public boolean clicked(int mouseX, int mouseY) {
        int[] loc = this.getLocation();
        int x = loc[0];
        int y = loc[1];
        int width = this.dogSprite.getWidth();
        int height = this.dogSprite.getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

}
