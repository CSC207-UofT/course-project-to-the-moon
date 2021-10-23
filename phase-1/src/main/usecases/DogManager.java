package usecases;

import java.awt.image.BufferedImage;
import entities.Dog;
import entities.Sprite;

/**
 * This class manages the functionalities of a single dog.
 * @author Juntae Park and Andy Wang
 * @since 12 October 2021
 */
public class DogManager implements Clickable, Drawable, Displayable {
    private final Dog myDog; // the dog that this manager handles
    private final Sprite dogSprite;
    private final CoinCalculator coinCalc; // the coin calculator for this dog
    private final ExpCalculator expCalc; // the exp calculator for this dog

    /**
     * Initializes a dog manager, while also creating a dog and corresponding sprite.
     */
    public DogManager() {
        this.myDog = new Dog();
        this.dogSprite = new Sprite("phase-0/src/main/sprites/dog", 2);

        // facade pattern!!!
        this.coinCalc = new CoinCalculator();
        this.expCalc = new ExpCalculator();
        DogMover dogMover = new DogMover(this.myDog, this.dogSprite);
        dogMover.();
    }

    private void update(int earnedCoin, int earnedExp) {
        this.myDog.setCoins(this.myDog.getCoins() + earnedCoin);
        this.myDog.setExp(this.myDog.getExp() + earnedExp);
    }

    /**
     * Pets the Dog, calculate exp and coins earned, update dog.
     */
    @Override
    public void act() {
        int earnedCoin = coinCalc.calculateCoins(myDog);
        int earnedExp = expCalc.calculateExp(myDog);

        this.update(earnedCoin, earnedExp);

    }

    /**
     * Returns the x, y coordinates of the dog.
     * @return The x, y coordinates of the dog.
     */
    @Override
    public int[] getLocation() {
        return new int[]{(int) this.myDog.getX(), (int) this.myDog.getY()};
    }

    /**
     * Returns whether the given mouse coordinates are on the dog.
     * @param mouseX The x coordinate of the mouse.
     * @param mouseY The y coordinate of the mouse.
     * @return Whether the mouse clicked on the dog.
     */
    @Override
    public boolean clicked(int mouseX, int mouseY) {
        int[] loc = this.getLocation();
        int x = loc[0];
        int y = loc[1];
        int width = this.dogSprite.getWidth();
        int height = this.dogSprite.getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    /**
     * Returns values of DogManager to be displayed
     * @return the coin and exp of the dog, specifically in that order.
     */
    @Override
    public int[] getDisplay() {
        return new int[] {myDog.getCoins(), myDog.getExp()};
    }

    /**
     * Gets the x coordinate of the dog.
     * @return The x coordinate of the dog.
     */
    @Override
    public int getX() {
        return (int) this.myDog.getX();
    }

    /**
     * Gets the y coordinate of the dog.
     * @return The y coordinate of the dog.
     */
    @Override
    public int getY() {
        return (int) this.myDog.getY();
    }

    public int getCoins() {
        return this.myDog.getCoins();
    }



    /*
        public int getExp() {
        return this.myDog.getExp();
        }
       */


    /**
     * Gets the dog sprite's current frame.
     * @return The dog sprite's current frame.
     */
    @Override
    public BufferedImage getImage() {
        return this.dogSprite.getCurrentFrame();
    }
    
}
