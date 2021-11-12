package usecases;

import adaptors.IGameController;
import adaptors.IGameGraphics;
import entities.Bank;
import entities.Dog;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A class which manages a single dog object
 * Within the minigame.
 * @author Aria Paydari
 * @since 11 November 2021
 */
public class PlatformDogGameObject extends GameObject implements Drawable{
    private final Dog myDog; // the dog that this manager handles
    private IGameController controller = null;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param sprite The sprite of the dog.
     * @param controller The controller controlling this DogGameObject.
     * @param bank The bank that this object modifies.
     */
    public PlatformDogGameObject(int x, int y, SpriteFacade sprite,
                         IGameController controller, Bank bank){
        super(x, y, "DogGameObject", sprite, controller);
        this.myDog = new Dog();

        DogMover dogMover = new DogMover(this.getSprite(),180, 180);
        this.addMover(dogMover);
        this.controller = controller;
    }




    @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getSprite().getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        g.drawText("exp: " + Integer.toString(this.myDog.getExp()),
                drawnX + 30, drawnY + 95, Color.WHITE);
    }


}
