package usecases;

import adaptors.IGameController;
import entities.Transform;

/**
 * Represents any 'object' in the program that can be displayed on screen.
 * @author Juntae Park, Andy Wang
 * @since 29 October 2021
 */

public abstract class AbstractObject {
    protected Transform transform = null;
    protected SpriteFacade sprite = null;
    protected IGameController controller = null;

    private String tag = null;

    /**
     * Initializes an abstract object at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param tag The tag of this object.
     */
    public AbstractObject(double x, double y, String tag) {
        this.transform = new Transform(x, y);
        this.tag = tag;
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

    public String getTag() {
        return this.tag;
    }
}
