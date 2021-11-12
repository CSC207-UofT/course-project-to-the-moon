package usecases;

import adaptors.IGameController;
import adaptors.IGameGraphics;
import entities.Bank;
import entities.Dog;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.WildcardType;

/**
 * A class which represents a platform,
 * in a minigame.
 * @author Aria Paydari
 * @since 11 November 2021
 */
public class PlatformGameObject extends GameObject implements Drawable, Collidable{
        private final int WIDTH;
        private final int LENGTH;

    /**
     * Initializes a new DogGameObject at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public PlatformGameObject(int x, int y, int width, int length,SpriteFacade sprite) {
        super(x, y, "DogGameObject", sprite);
        WIDTH = width;
        LENGTH = length;


    }


    // What exactly is the purpose of offsetY?
    @Override public void draw(IGameGraphics g, int offsetX, int offsetY) {
        g.fillRect((int) super.getX() , (int) super.getY() , WIDTH, LENGTH, Color.WHITE);

    }

    @Override
    public Rectangle getHitBox() {
        return null;
    }
}
