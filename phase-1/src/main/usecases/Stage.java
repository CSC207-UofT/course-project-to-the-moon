package usecases;

import org.w3c.dom.Text;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents all text labels and collidable game objects on the screen.
 * @author Juntae Park
 * @since 30 October 2021
*/

public class Stage {
    List<GameObject> gameObjects;
    List<TextLabel> textLabels;
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
     * Determines if the given Collidable will collide with any object with the given tag,
     * at the given coordinates.
     * @param c The Collidable to check for.
     * @param x The x coordinate to check at.
     * @param y The y coordinate to check at.
     * @param otherTag The tag to check collision for.
     * @return
     */
    public boolean placeMeeting(Collidable c, double x, double y, String otherTag) {
        // TODO: Make this skip objects that are too far from the target coordinates?

        Rectangle hitBox = c.getHitBox();
        Rectangle collisionHitBox = new Rectangle((int) x, (int) y,
                (int) hitBox.getWidth(), (int) hitBox.getHeight());

        // loop through the GameObjects
        for (GameObject go : this.gameObjects) {
            if ((go instanceof Collidable) && (go.getTag().equals(otherTag))) {
                Rectangle otherBox = ((Collidable) go).getHitBox();

                if (collisionHitBox.intersects(otherBox)) {
                    return true;
                }
            }
        } return false;
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

}