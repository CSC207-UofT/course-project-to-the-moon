package adaptors;

import entities.Transform;
import usecases.Drawable;
import usecases.GameObject;
import usecases.Mover;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A presenter class that acts like a camera/viewport; whatever objects are seen on it are captured.
 * @author Andy Wang
 * @since 23 October 2021
 */
public class Camera {
    private List<GameObject> activeStage = null;
    private Rectangle bounds = null;
    private Transform transform = null;
    private Mover mover = null;
    private GameObject subject = null;
    // ignore these warnings for now

    /**
     * Initializes a new Camera that focuses on the given stage, at the given coordinates,
     * and captures using the given dimensions.
     */
    public Camera(List<GameObject> stage, Rectangle bounds) {
        this.activeStage = stage;
        this.bounds = bounds;
        this.transform = new Transform(bounds.getX(), bounds.getY());
    }

    /**
     * Returns all the Drawable GameObjects on the camera's current stage whose coordinates are within the camera's
     * bounds.
     * @return All the GameObjects on the camera's current stage whose coordinates are within the camera's bounds.
     */
    public List<Drawable> getDrawablesInBounds() {
        ArrayList<Drawable> drawablesSoFar = new ArrayList<>();

        for (GameObject go : activeStage) {
            if (go instanceof Drawable) {
                Rectangle objectSpriteBounds = new Rectangle((int) go.getX(), (int) go.getY(),
                        go.getSprite().getWidth(), go.getSprite().getHeight());

                if (this.bounds.intersects(objectSpriteBounds)) {
                    drawablesSoFar.add((Drawable) go);
                }
            }
        }
        return drawablesSoFar;
    }

    /**
     * Sets the GameObjects that this camera should be focusing on.
     * @param stage The GameObjects to focus on.
     */
    public void setStage(List<GameObject> stage) {
        this.activeStage = stage;
    }

    /**
     * Sets the subject of this GameObject - this will make the camera automatically follow the subject,
     * meaning that the subject will be at the centre of the camera's view.
     * @param subject The GameObject to set the subject as.
     */
    public void setSubject(GameObject subject) {
        this.mover = null; // remove the old mover
        // TODO: maybe add a cancel method? idk if java's garbage collection will handle it already
        // TODO: finish implementing this, idk if we'll use it the future so remove it if we don't
    }

    // getters
    public int getX() {
        return (int) this.transform.getX();
    }

    public int getY() {
        return (int) this.transform.getY();
    }
}