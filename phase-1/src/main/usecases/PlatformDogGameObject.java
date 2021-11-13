package usecases;

import adaptors.IGameController;
import adaptors.IGameGraphics;
import entities.Bank;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class which manages a single dog object
 * Within the minigame.
 * @author Aria Paydari
 * @since 11 November 2021
 */
public class PlatformDogGameObject extends GameObject implements Drawable, Collidable {
    private IGameController controller = null;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param sprite The sprite of the dog.
     * @param bank The bank to update.
     * @param controller The controller controlling this DogGameObject.
     */
    public PlatformDogGameObject(int x, int y, SpriteFacade sprite, Bank bank,
                         IGameController controller){
        super(x, y, "PlatformerDogGameObject", sprite, controller);

        PlatformerDogMover dogMover = new PlatformerDogMover(this, bank, controller);
        this.addMover(dogMover);
        this.controller = controller;
    }

    @Override
    public void draw(IGameGraphics g, int offsetX, int offsetY) {
        BufferedImage frame = this.getCurrentFrame();
        int drawnX  = (int) this.getX() - offsetX;
        int drawnY = (int) this.getY() - offsetY;

        g.drawImage(frame, drawnX, drawnY);

        // draw the hitbox -- only for debugging
//        Rectangle hitbox = this.getHitBox();
//        g.fillRect(drawnX, drawnY + 5 * (this.getHeight() / 6), hitbox.width, hitbox.height, Color.GREEN);
    }

    /**
     * Returns the dog's hitbox at its current coordinates.
     * @return The hitbox at the current coordinates.
     */
    @Override
    public Rectangle getHitBox() {
        return new Rectangle((int) this.getX(), (int) this.getY() + 5 * (this.getHeight() / 6),
                this.getWidth(), this.getHeight() / 6);
        // for the platforming dog, we only care about the lower part of it
    }

    /**
     * Returns the dog's hitbox if the DOG were at the given coordinates.
     * @param x X-coordinate.
     * @param y Y-coordinate.
     * @return The dog's hitbox if the dog were at the given coordinates.
     */
    @Override
    public Rectangle getHitBoxAtCoords(int x, int y) {
        return new Rectangle(x, y + 5 * (this.getHeight() / 6),
                this.getWidth(), this.getHeight() / 6);
        // for the platforming dog, we only care about the lower part of it
    }

}
