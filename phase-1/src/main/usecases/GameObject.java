package usecases;

import adaptors.IGameController;
import entities.Sprite;
import java.awt.image.BufferedImage;

/**
 * A "manager" class that represents a game object that the player can see and possibly
 * interact with.
 * @author Andy Wang, Juntae Park
 * @since 21 October 2021
 */
public abstract class GameObject extends AbstractObject {
    private Mover mover = null;
    private SpriteFacade sprite = null;
    protected IGameController controller = null;

    /**
     * Initializes a game object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param tag This object's tag.
     * @param sprite This object's SpriteFacade.
     * @param c The controller controlling this object.
     */
    public GameObject(double x, double y, String tag, SpriteFacade sprite, IGameController c) {
        super(x, y, tag);
        this.sprite = sprite;
        this.controller = c;
    }
    /**
     * Initializes a game object at the given coordinates,
     * but without a controller.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param tag This object's tag.
     * @param sprite This object's SpriteFacade.
     */

    public GameObject(double x, double y, String tag, SpriteFacade sprite) {
        super(x, y, tag);
        this.sprite = sprite;

    }

    /**
     * Sets this object's SpriteFacade to use.
     * @param sprite The SpriteFacade to set.
     */
    public void setSprite(SpriteFacade sprite) {
        this.sprite = sprite;
    }

    /**
     * Adds and runs an implementation of Mover to control the movement of this object.
     *
     * @param m The mover to add and run.
     */
    public void addMover(Mover m) {
        if (this.mover == null) {
            this.mover = m;
        }
        this.mover.run(this.getTransform());
    }

    /**
     * Gets the Sprite for this object.
     * @return The Sprite for this object.
     */
    public Sprite getSprite() {
        return this.sprite.getSprite();
    }

    /**
     * Gets the current sprite frame for this object.
     * @return The current sprite frame for his object.
     */
    public BufferedImage getCurrentFrame() {
        return this.getSprite().getCurrentFrame();
    }

    /**
     * Gets the width of this object's sprite.
     * @return This object's width.
     */
    public int getWidth() {
        return this.sprite.getSprite().getWidth();
    }

    /**
     * Gets the height of this object's sprite.
     * @return This object's height.
     */
    public int getHeight() {
        return this.sprite.getSprite().getHeight();
    }
}
