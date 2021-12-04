package adaptors;

import usecases.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import entities.Transform;
import usecases.interfaces.Drawable;
import usecases.object.GameObject;
import usecases.object.TextLabel;

/**
 * A presenter class that acts like a camera/viewport; whatever objects are seen on it are captured.
 * @author Andy Wang, Juntae Park
 * @since 23 October 2021
 */
public class Camera implements ICamera {
    private Stage activeStage;
    private final Transform transform;
    private final int xBounds;
    private final int yBounds;

    /**
     * Initializes a new Camera that focuses on the given stage, at the given coordinates,
     * and captures using the given dimensions.
     */
    public Camera(Stage stage, Rectangle bounds) {
        this.activeStage = stage;
        this.xBounds = bounds.width;
        this.yBounds = bounds.height;
        this.transform = new Transform(bounds.getX(), bounds.getY());
    }

    /**
     * Returns all the Drawable GameObjects on the camera's current stage whose coordinates are within the camera's
     * bounds.
     * @return All the GameObjects on the camera's current stage whose coordinates are within the camera's bounds.
     */
    @Override
    public List<Drawable> getDrawableObjectsInBounds() {
        ArrayList<Drawable> drawablesSoFar = new ArrayList<>();
        List<GameObject> gameObjList = activeStage.getGameObjects();

        Rectangle bounds = new Rectangle((int) transform.getX(), (int) transform.getY(), xBounds, yBounds);

        for (GameObject go : gameObjList) {
            if (go instanceof Drawable) {
                Rectangle objectSpriteBounds = new Rectangle((int) go.getX(), (int) go.getY(),
                        go.getSprite().getWidth(), go.getSprite().getHeight());

                if (bounds.intersects(objectSpriteBounds)) {
                    drawablesSoFar.add((Drawable) go);
                }
            }
        }
        return drawablesSoFar;
    }

    /**
     * Returns all the TextLabels that are adorned to the Camera's active stage.
     * @return All the TextLabels to be drawn. These are not affected by the camera's position.
     */
    @Override
    public List<TextLabel> getTextLabels() {
        return this.activeStage.getTextLabels();
    }

    /**
     * Sets the Stage that this camera should be focusing on.
     * @param stage The Stage to focus on.
     */
    @Override
    public void setStage(Stage stage) {
        // reset the camera's coords
        this.transform.moveTo(0, 0);
        this.activeStage = stage;
    }

    /**
     * Returns this camera's Transform.
     * @return This camera's Transform.
     */
    @Override
    public Transform getTransform() {
        return this.transform;
    }
}
