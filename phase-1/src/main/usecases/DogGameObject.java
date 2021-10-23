package usecases;

import entities.Dog;
import entities.Transform;


import java.awt.image.BufferedImage;

/**
 * A class which manages a single dog object
 * Within a game.
 * @author Aria Paydari
 * @since 22 October 2021
 */
public class DogGameObject extends GameObject implements Clickable, Drawable{
    private final Dog myDog; // the dog that this manager handles
    private final CoinCalculator coinCalc; // the coin calculator for this dog
    private final ExpCalculator expCalc; // the exp calculator for this dog

    public DogGameObject(int x, int y){
        super(x, y);
        this.myDog = new Dog();

        // facade pattern!!!
        this.coinCalc = new CoinCalculator();
        this.expCalc = new ExpCalculator();
        DogMover dogMover = new DogMover(this.myDog, super.getSprite().getSprite(),
                300, 500);
        super.addMover(dogMover);
        dogMover.run(new Transform(x, y));
    }

    /**
     * Checks if the coordinates of the mouse click
     * are on the Dog's sprite.
     * @param mouseX the x coordinates of the mouse click.
     * @param mouseY mouseX the x coordinates of the mouse click.
     * @return whether the sprite was clicked or not.
     */
    @Override
    public boolean clicked(int mouseX, int mouseY) {
        int[] loc = this.getLocation();
        int x = loc[0];
        int y = loc[1];
        int width = super.getSprite().getSprite().getWidth();
        int height = super.getSprite().getSprite().getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    public int[] getDisplay() {
        return new int[] {myDog.getCoins(), myDog.getExp()};
    }

    /**
     * A getter method for the dog's coordinates.
     * @return the location of the dog.
     */
    @Override
    public int[] getLocation() {
        int castX = (int) super.getTransform().getX();
        int castY = (int) super.getTransform().getY();
        return new int[]{castX, castY};

    }

    /**
     * Pets the Dog, calculate exp and coins earned, update dog
     * when the dog's sprite is clicked.
     */
    @Override
    public void act() {
        int earnedCoin = coinCalc.calculateCoins(myDog);
        int earnedExp = expCalc.calculateExp(myDog);

        this.update(earnedCoin, earnedExp);

    }

    public int getCoins() {
        return this.myDog.getCoins();
    }

    /**
     * Return the x coordinate.
     * @return The x coordinate.
     */
    @Override
    public int getX() {
        return (int) super.getTransform().getX();
    }

    /**
     * Return the y coordinate.
     * @return The y coordinate.
     */
    @Override
    public int getY() {
        return (int) super.getTransform().getY();
    }

    /**
     * A getter method returning the dog's current sprite image
     * @return the BufferedImage representation of the dog sprite.
     */
    @Override
    public BufferedImage getImage() {
        return super.getSprite().getSprite().getCurrentFrame();
    }

    /**
     * Updates the dog's coin and experience
     * @param earnedCoin the number of coins earned.
     * @param earnedExp the amount of experience gained.
     */
    private void update(int earnedCoin, int earnedExp) {
        this.myDog.setCoins(this.myDog.getCoins() + earnedCoin);
        this.myDog.setExp(this.myDog.getExp() + earnedExp);
    }

}
