package usecases;

import entities.Collider;
import entities.Sprite;
import entities.Transform;

import java.awt.image.BufferedImage;

/**
 * A "manager" class that represents a physical game object that the player can see and possibly
 * interact with.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class GameObject {
    private Transform transform = null;
    private SpriteFacade sprite = null;
    private Collider collider = null;
    private Mover mover = null;

    /**
     * Initializes a game object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public GameObject(double x, double y) {
        this.transform = new Transform(x, y);
    }

    /**
     * Adds a SpriteFacade to this GameObject.
     * @param sprite The SpriteFacade to add.
     */
    public void addSprite(SpriteFacade sprite) {
        this.sprite = sprite;
    }

    /**
     * Adds and runs an implementation of Mover to control the movement of this object.
     *
     * Preconditions: this.mover == null
     * @param m The mover to add.
     */
    public void addMover(Mover m) {
        if (this.mover == null) {
            this.mover = m;
        }

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

    public Collider getCollider() {
        return this.collider;
    }
}
