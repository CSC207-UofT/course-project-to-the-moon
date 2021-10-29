package usecases;

import entities.Collider;

/**
 * A "manager" class that represents a physical game object that the player can see and possibly
 * interact with.
 * @author Andy Wang
 * @since 21 October 2021
 */
public class GameObject extends AbstractObject {
    private Collider collider = null;
    private Mover mover = null;

    /**
     * Initializes a game object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public GameObject(double x, double y) {
        super(x,y);
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

    public Collider getCollider() {
        return this.collider;
    }
}
