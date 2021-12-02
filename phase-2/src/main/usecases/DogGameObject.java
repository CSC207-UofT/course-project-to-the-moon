package usecases;

import adaptors.IGameGraphics;
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
    private final Bank bank ;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param sprite The sprite of the dog.
     * @param bank The bank that this object modifies.
     */
    public DogGameObject(int x, int y, SpriteFacade sprite, Bank bank){
        super(x, y, "DogGameObject", sprite, null);
        this.myDog = new Dog();
        this.bank = bank;

        DogMover dogMover = new DogMover(this.getSprite(), 180, 180);
        this.addMover(dogMover);
    }

    public Dog getDog() {
        return this.myDog;
    }

    /**
     * Checks if the coordinates of the mouse click
     * are on the Dog's sprite.
     * @param mouseX the x coordinates of the mouse click.
     * @param mouseY mouseX the x coordinates of the mouse click.
     * @return whether the sprite was clicked or not.
     */
    @Override
    public boolean isClicked(int mouseX, int mouseY) {
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
    public void onClick() {
        int earnedCoin = myDog.calculateCoinsEarned();
        int earnedExp = myDog.calculateExpEarned();

        this.updateDog(earnedCoin, earnedExp);
        this.bank.updateCoins(earnedCoin);
    }

    /**
     * Draw this dog on the screen.
     * @param g The implementation of IGameGraphics to use.
     * @param offsetX How much to offset the drawn image's x coordinate.
     * @param offsetY How much to offset the drawn image's y coordinate.
     */
    @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getSprite().getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        g.drawText("exp: " + this.myDog.getExp(),
                drawnX + 30, drawnY + 85, Color.WHITE);
    }

    /**
     * Updates the dog's coin and experience
     * @param earnedCoin the number of coins earned.
     * @param earnedExp the amount of experience gained.
     */
    private void updateDog(int earnedCoin, int earnedExp) {
        this.myDog.setCoins(this.myDog.getCoins() + earnedCoin);
        this.myDog.setExp(this.myDog.getExp() + earnedExp);
    }
    
}
