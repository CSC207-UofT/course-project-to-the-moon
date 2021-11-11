package usecases;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents all text labels and collidable game objects on the screen.
 * @author Juntae Park, Andy Wang
 * @since 30 October 2021
*/

public class Stage {
    List<GameObject> gameObjects;
    List<TextLabel> textLabels;
    List<ShopButton> textButtons;
    private String name;

    /**
     * Initializes a new empty stage.
     * @param name The name to give this stage.
     */
    public Stage(String name) {
        this.gameObjects = new ArrayList<GameObject>();
        this.textLabels = new ArrayList<TextLabel>();
        this.name = name;
    }

    /**
     * Add a new GameObject to this stage.
     * @param go The object to add.
     */
    public void addGameObject(GameObject go) {
        this.gameObjects.add(go);
    }

    /**
     * Add a new TextLabel to this stage.
     * @param tl The label to add.
     */
    public void addTextLabel(TextLabel tl) {
        this.textLabels.add(tl);
    }

    // getters
    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    public List<TextLabel> getTextLabels() {
        return this.textLabels;
    }

    /**
     * Returns the first TextLabel with the given tag.
     * @param tag The tag to search for.
     * @return The TextLabel with the given tag.
     */
    public TextLabel getTextLabelWithTag(String tag) {
        for (TextLabel label : this.textLabels) {
            if (label.getTag().equals(tag)) {
                return label;
            }
        } return null;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Determines if the given Collidable will collide with any object with the given tag,
     * at the given coordinates. Returns true if object has collided with another object and has been resolved.
     * @param c The Collidable to check for.
     * @param x The x coordinate to check at.
     * @param y The y coordinate to check at.
     * @return
     */
    public boolean placeMeeting(Collidable c, double x, double y) {
        // TODO: Make this skip objects that are too far from the target coordinates?

        Rectangle hitBox = c.getHitBox();
        Rectangle collisionHitBox = new Rectangle((int) x, (int) y,
                (int) hitBox.getWidth(), (int) hitBox.getHeight());

        for (GameObject go : this.gameObjects) {
            if ((go instanceof Collidable) && (!((Collidable) go).equals(c))) {
                Rectangle otherBox = ((Collidable) go).getHitBox();
                if (collisionHitBox.intersects(otherBox)) {
                    double[] AABBdist = getAABBdist((Collidable) c, (Collidable) go, x, y);
                    ((GameObject) c).getTransform().setDx(AABBdist[0]);
                    ((GameObject) c).getTransform().setDy(AABBdist[1]);

                    return true;
                    //does not work if colliding with multiple objects at the same time, but that might not be necessary
                }
            }
        } return false;
    }

    //gets the corrected distance by which the object c should travel by in order to not collide with other
    private double[] getAABBdist(Collidable c, Collidable other, double x, double y) {
        Rectangle h1 = c.getHitBox();
        Rectangle h2 = other.getHitBox();

        double xCorr = 0;
        double yCorr = 0;

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
