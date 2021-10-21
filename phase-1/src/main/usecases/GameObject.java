package usecases;

import entities.Collider;
import entities.Transform;

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

}
