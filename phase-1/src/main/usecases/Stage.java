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

}
