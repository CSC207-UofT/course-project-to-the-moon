package usecases;

import adaptors.GameGraphics;
import entities.Dog;


import java.awt.Color;
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
    private int coinsEarnedFromLastPet;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param sprite The sprite of the dog.
     */
    public DogGameObject(int x, int y, SpriteFacade sprite){
        super(x, y);
        this.myDog = new Dog();
        this.addSprite(sprite);

        // Facade pattern; delegate calculations to other classes
        // TODO: decide if this is really necessary. Dog doesn't have enough responsibilities, so it's
        // probably a code smell. Perhaps it can calculate its own stuff.
        this.coinCalc = new CoinCalculator();
        this.expCalc = new ExpCalculator();

        DogMover dogMover = new DogMover(this.getSprite(), 180, 180);
        this.addMover(dogMover); // automatically runs
    }

    /**
     * Returns the amount of coins earned from the last pet (click)
     * @return The amount of coins earned from the last pet.
     */
    public int getCoinsEarnedFromLastPet() {
        return this.coinsEarnedFromLastPet;
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
        double x = this.getX();
        double y = this.getY();
        int width = super.getSprite().getWidth();
        int height = super.getSprite().getHeight();

        return ((x < mouseX) && (mouseX < x + width) && (y < mouseY) && (mouseY < y + height));
    }

    /**
     * Pets the Dog, calculate exp and coins earned, update dog
     * when the dog's sprite is clicked.
     */
    @Override
    public void act() {
        int earnedCoin = coinCalc.calculateCoins(myDog);
        int earnedExp = expCalc.calculateExp(myDog);

        this.updateDog(earnedCoin, earnedExp);
    }

    /**
     * Draw this dog on the screen.
     * @param g The implementation of GameGraphics to use.
     * @param offsetX How much to offset the drawn image's x coordinate.
     * @param offsetY How much to offset the drawn image's y coordinate.
     */
    @Override public void draw(GameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getSprite().getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        g.setColor(Color.WHITE);
        g.drawText("exp: " + Integer.toString(this.myDog.getExp()), drawnX + 30, drawnY + 95);
    }

    /**
     * Updates the dog's coin and experience
     * @param earnedCoin the number of coins earned.
     * @param earnedExp the amount of experience gained.
     */
    private void updateDog(int earnedCoin, int earnedExp) {
        this.myDog.setCoins(this.myDog.getCoins() + earnedCoin);
        this.myDog.setExp(this.myDog.getExp() + earnedExp);
        this.coinsEarnedFromLastPet = earnedCoin;
    }
}
