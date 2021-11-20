package usecases;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents all text labels and collidable game objects on the screen.
 * @author Juntae Park, Andy Wang
 * @since 30 October 2021
*/

public class Stage implements Serializable {
    private final List<GameObject> gameObjects;
    private final List<TextLabel> textLabels;

    /**
     * Initializes a new empty stage.
     * @param name The name to give this stage.
     */
    public Stage(String name) {
        this.gameObjects = new ArrayList<GameObject>();
        this.textLabels = new ArrayList<TextLabel>();
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
     * Determines if the given Collidable will collide with any object with the given tag,
     * at the given coordinates.
     * @param c The Collidable to check for.
     * @param x The x coordinate to check at.
     * @param y The y coordinate to check at.
     * @param tag The tag to search for.
     * @return Whether it collided with the desired object or not.
     */
    public boolean placeMeeting(Collidable c, double x, double y, String tag) {
        Rectangle hitBox = c.getHitBoxAtCoords((int) x, (int) y);

        synchronized(this.gameObjects) {
            for (GameObject go : this.gameObjects) {
                if ((go instanceof Collidable) && (go.getTag().equals(tag))) {
                    Rectangle otherBox = ((Collidable) go).getHitBox();

                    if (hitBox.intersects(otherBox)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

   /* public String getName() {
        return this.name;
    }
    */
}
