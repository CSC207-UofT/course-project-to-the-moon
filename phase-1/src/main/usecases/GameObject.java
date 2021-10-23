package usecases;

import entities.Collider;
import entities.Transform;

import java.awt.image.BufferedImage;

/**
 * A "manager" class that represents a physical game object that the player can see and possibly
 * interact with.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class GameObject {
    private Transform transform;
    private SpriteFacade sprite;
    private Collider collider;
    private Mover mover;

    /**
     * Initializes a game object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public GameObject(double x, double y) {
        this.transform = new Transform(x, y);
    }

    /**
     * Adds a sprite given its frames.
     * @param frames The sprite's frames as a BufferedImage array.
     */
    public void addSpriteFromFrames(BufferedImage[] frames) {
        this.sprite = new SpriteFacade(frames);
    }

    /**
     * Adds a sprite given its frames and animation speed in frames per second.
     * @param frames The sprite's frames as a BufferedImage array.
     * @param fps The animation speed in frames per second.
     */
    public void addSpriteFromFrames(BufferedImage[] frames, int fps) {
        this.sprite = new SpriteFacade(frames, fps);
    }

    /**
     * Adds an implementation of Mover to control the movement of this object.
     *
     * Preconditions: this.mover == null
     * @param m The mover to add.
     */
    protected void addMover(Mover m) {
        this.mover = m;
        mover.run(this.transform);
    }

    /**
     * Adds a collider so that this object may collide with other objects.
     * @param c The collider to add.
     */
    public void addCollider(Collider c) {
        this.collider = c;
    }

    // getter methods
    public Transform getTransform() {
        return this.transform;
    }

    public SpriteFacade getSprite() {
        return this.sprite;
    }

    public Collider getCollider() {
        return this.collider;
    }
}
