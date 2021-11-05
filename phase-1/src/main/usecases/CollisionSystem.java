package usecases;

import java.awt.Rectangle;
import java.util.List;

import usecases.Collidable;
import usecases.GameObject;

/**
 * Responsible for detecting and resolving collision between any two objects on camera.
 * @author Juntae Park, Andy Wang
 * @since 21 October 2021
 */
public class CollisionSystem {

    /**
     * Determines if the given Collidable will collide with any object with the given tag,
     * at the given coordinates.
     * @param gameObjects List of GameObjects to check thorugh.
     * @param c The Collidable to check for.
     * @param x The x coordinate to check at.
     * @param y The y coordinate to check at.
     * @return
     */
    public static boolean placeMeeting(List<GameObject> gameObjects, Collidable c, double x, double y) {
        // TODO: Make this skip objects that are too far from the target coordinates?

        Rectangle hitBox = c.getHitBox();
        Rectangle collisionHitBox = new Rectangle((int) x, (int) y,
                (int) hitBox.getWidth(), (int) hitBox.getHeight());

        // loop through the GameObjects
        for (GameObject go : gameObjects) {
            if ((go instanceof Collidable) && (!((Collidable) go).equals(c))) {
                Rectangle otherBox = ((Collidable) go).getHitBox();

                if (collisionHitBox.intersects(otherBox)) {
                    double[] AABBdist = getAABBdist((Collidable) c, (Collidable) go, x, y);
                    ((GameObject) c).getTransform().setDx(AABBdist[0]);
                    ((GameObject) c).getTransform().setDy(AABBdist[1]);

                    return true;
                }
            }
        } return false;
    }

    public static double[] getAABBdist(Collidable c, Collidable other, double x, double y) {
        Rectangle h1 = c.getHitBox();
        Rectangle h2 = other.getHitBox();

        double xCorr = 0;
        double yCorr = 0;

        double dx = ((GameObject) c).getTransform().getDx();
        double dy = ((GameObject) c).getTransform().getDy();

        if (x < h2.getX()){
            xCorr = -1*((x + h1.getWidth()) - h2.getX());
        } 
        else {
            xCorr = (h2.getX() + h2.getWidth()) - x;
        }

        if (y < h2.getY()) {
            yCorr = -1*((y + h1.getHeight()) - h2.getY());
        }
        else {
            yCorr = (h2.getY() + h2.getHeight()) - y;
        }

        if (Math.abs(xCorr) < Math.abs(yCorr)) {
            yCorr = 0;
        } else if (Math.abs(xCorr) > Math.abs(yCorr)) {
            xCorr = 0;
        }
        
        return new double[]{xCorr, yCorr};
    }
}
