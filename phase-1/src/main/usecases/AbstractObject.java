package usecases;

import entities.Sprite;
import entities.Transform;

import java.awt.image.BufferedImage;

/**
 * Represents any 'object' in the program that can be displayed on screen.
 * @author Juntae Park, Andy Wang
 * @since 29 October 2021
 */

public abstract class AbstractObject {
    protected Transform transform = null;
    protected SpriteFacade sprite = null;

    /**
     * Initializes an abstract object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public AbstractObject(double x, double y) {
        this.transform = new Transform(x, y);
    }

    /**
     * Adds a SpriteFacade to this GameObject.
     * @param sprite The SpriteFacade to add.
     */
    public void addSprite(SpriteFacade sprite) {
        this.sprite = sprite;
    }

    // getter methods
    public Transform getTransform() {
        return this.transform;
    }

    public double getX() {
        return this.transform.getX();
    }

    public double getY() {
        return this.transform.getY();
    }

    public Sprite getSprite() {
        return this.sprite.getSprite();
    }

    public BufferedImage getCurrentFrame() {
        return this.getSprite().getCurrentFrame();
    }

    public int getWidth() {
        return this.sprite.getSprite().getWidth();
    }

    public int getHeight() {
        return this.sprite.getSprite().getHeight();
    }

}
