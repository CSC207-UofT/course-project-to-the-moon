package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * This class represents something that has a hitbox
 * and can be collided with.
 * @author Aria Paydari
 * @since 21 October 2021
 */
public class Collider {

    Rectangle r;

    public Collider(int x, int y, int height, int length){
        r = new Rectangle();
        r.setRect(x, y, length, height);
    }

    boolean intersects (double x, double y, Collider other){
        Rectangle2D intersectRectangle = r.createIntersection(other.getHitBox());
        return intersectRectangle.contains(x, y);
//        return r.intersects(other.getHitBox());

    }

    public Rectangle getHitBox(){return this.r;}
}
