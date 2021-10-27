package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * This class represents a hitbox that can be collided with.
 * @author Aria Paydari
 * @since 21 October 2021
 */
public class Collider {
    private Rectangle r;

    /**
     * Initializes a new Collider given the dimensions of the hitbox.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param width The width of the hitbox.
     * @param height The height of the hitbox.
     */
    public Collider(int x, int y, int width, int height){
        r = new Rectangle(x, y, width, height);
    }

    /**
     * Checks if the current Collider intersects another collider at the given coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param other The other collider.
     * @return Whether this Collider intersects the other collider at the given coordinates.
     */
    public boolean intersects (double x, double y, Collider other){
        Rectangle temp = new Rectangle((int) x, (int) y,
                (int) this.r.getWidth(), (int) this.r.getHeight());

        return temp.intersects(other.getHitBox());
    }

    /**
     * Returns the hitbox that this Collider represents.
     * @return The hitbox of this Collider as a Rectangle object.
     */
    public Rectangle getHitBox(){
        return this.r;
    }
}
